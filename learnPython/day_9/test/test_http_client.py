import time

import pytest
from httpx import AsyncClient

from src.http_client import fetch_url, fetch_all_sequential, fetch_all_concurrent


@pytest.mark.asyncio
async def test_fetch_url_returns_success_result_for_valid_response():
    async with AsyncClient() as client:
        result = await fetch_url(client, "https://httpbin.org/get")

        assert result == {'content_length': 305,
                          'error': None,
                          'ok': True,
                          'status_code': 200,
                          'url': 'https://httpbin.org/get'}


@pytest.mark.asyncio
async def test_fetch_url_returns_error_result_when_request_fails():
    async with AsyncClient() as client:
        result = await fetch_url(client, "https://httpbin.org/status/404")
        assert result == {'content_length': 0,
                          'error': None,
                          'ok': True,
                          'status_code': 404,
                          'url': 'https://httpbin.org/status/404'}

@pytest.mark.asyncio
async def test_fetch_all_sequential_returns_result_for_each_url():
    async with AsyncClient() as client:
        urls = ["https://httpbin.org/get", "https://httpbin.org/status/404"]
        results = await fetch_all_sequential(urls)
        assert len(results) == 2

@pytest.mark.asyncio
async def test_fetch_all_concurrent_returns_result_for_each_url():
    async with AsyncClient() as client:
        urls = ["https://httpbin.org/get", "https://httpbin.org/status/404"]
        results = await fetch_all_concurrent(urls)
        assert len(results) == 2

@pytest.mark.asyncio
async def test_fetch_all_concurrent_is_faster_than_sequential_for_slow_urls():
    async with AsyncClient() as client:
        time_from = time.monotonic()
        urls = ["https://httpbin.org/get", "https://httpbin.org/status/404"]
        results = await fetch_all_sequential(urls)
        sequential_elapsed_time = time.monotonic() - time_from

        time_from = time.monotonic()
        results = await fetch_all_concurrent(urls)
        concurrent_elapsed_time = time.monotonic() - time_from

        assert concurrent_elapsed_time < sequential_elapsed_time
