.PHONY: dev test

dev:
	uv run fastapi dev

test:
	uv run --group test pytest

local_env:
	docker compose -f dc.local.env.yml up -d