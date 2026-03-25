from __future__ import annotations

import asyncio
from typing import Any

import httpx


async def fetch_url(client: httpx.AsyncClient, url: str) -> dict[str, Any]:
    """
    Fetch a single URL and return a structured result.

    The function never raises request errors to the caller.
    Instead, it returns an error description inside the result.
    """
    try:
        response = await client.get(url)

        return {
            "url": url,
            "ok": True,
            "status_code": response.status_code,
            "content_length": len(response.text),
            "error": None,
        }
    except Exception as exc:
        return {
            "url": url,
            "ok": False,
            "status_code": None,
            "content_length": None,
            "error": str(exc),
        }


async def fetch_all_sequential(
        urls: list[str],
        timeout: float = 5.0,
) -> list[dict[str, Any]]:
    """
    Fetch URLs one by one.
    """
    async with httpx.AsyncClient(timeout=timeout) as client:
        results: list[dict[str, Any]] = []

        for url in urls:
            result = await fetch_url(client, url)
            results.append(result)

        return results


async def fetch_all_concurrent(
        urls: list[str],
        timeout: float = 5.0,
) -> list[dict[str, Any]]:
    """
    Fetch all URLs concurrently with asyncio.gather().
    """
    async with httpx.AsyncClient(timeout=timeout) as client:
        tasks = [fetch_url(client, url) for url in urls]
        return await asyncio.gather(*tasks)


async def compare_execution_times(
        urls: list[str],
        timeout: float = 5.0,
) -> dict[str, Any]:
    """
    Run both sequential and concurrent versions and return timing info.
    """
    import time

    start = time.monotonic()
    sequential_results = await fetch_all_sequential(urls, timeout=timeout)
    sequential_elapsed = time.monotonic() - start

    start = time.monotonic()
    concurrent_results = await fetch_all_concurrent(urls, timeout=timeout)
    concurrent_elapsed = time.monotonic() - start

    return {
        "sequential_elapsed": sequential_elapsed,
        "concurrent_elapsed": concurrent_elapsed,
        "sequential_results": sequential_results,
        "concurrent_results": concurrent_results,
    }


async def main() -> None:
    urls = [
        "https://example.com",
        "https://httpbin.org/get",
        "https://httpbin.org/status/404",
        "https://httpbin.org/delay/1",
    ]

    comparison = await compare_execution_times(urls)

    print("Sequential time:", f"{comparison['sequential_elapsed']:.3f}s")
    print("Concurrent time:", f"{comparison['concurrent_elapsed']:.3f}s")
    print()

    print("Sequential results:")
    for result in comparison["sequential_results"]:
        print(result)

    print()
    print("Concurrent results:")
    for result in comparison["concurrent_results"]:
        print(result)


if __name__ == "__main__":
    asyncio.run(main())