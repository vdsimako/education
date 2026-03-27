from src.models import ExternalServiceError


class AsyncProfileClient:
    async def fetch_profile(self, user_id: int) -> dict:
        raise NotImplementedError


class AsyncUserService:
    def __init__(self, profile_client: AsyncProfileClient) -> None:
        self.profile_client = profile_client

    async def get_profile(self, user_id: int) -> dict:
        profile = await self.profile_client.fetch_profile(user_id)
        if not profile:
            raise ExternalServiceError("Profile response was empty")
        return profile
