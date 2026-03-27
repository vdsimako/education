import pytest
from unittest.mock import AsyncMock

from src.async_service import AsyncUserService, AsyncProfileClient
from src.models import ExternalServiceError


@pytest.fixture
def mock_profile_client():
    client = AsyncMock(spec=AsyncProfileClient)
    return client


@pytest.fixture
def async_service(mock_profile_client):
    return AsyncUserService(mock_profile_client)


@pytest.mark.asyncio
async def test_get_profile_returns_profile_data(async_service, mock_profile_client):
    expected_profile = {"user_id": 1, "name": "Alice", "email": "alice@example.com"}
    mock_profile_client.fetch_profile.return_value = expected_profile
    
    result = await async_service.get_profile(1)
    
    assert result == expected_profile
    mock_profile_client.fetch_profile.assert_called_once_with(1)


@pytest.mark.asyncio
async def test_get_profile_raises_error_when_profile_is_empty(async_service, mock_profile_client):
    mock_profile_client.fetch_profile.return_value = {}
    
    with pytest.raises(ExternalServiceError, match="Profile response was empty"):
        await async_service.get_profile(1)


@pytest.mark.asyncio
async def test_get_profile_propagates_client_exception(async_service, mock_profile_client):
    mock_profile_client.fetch_profile.side_effect = Exception("Network error")
    
    with pytest.raises(Exception, match="Network error"):
        await async_service.get_profile(1)
