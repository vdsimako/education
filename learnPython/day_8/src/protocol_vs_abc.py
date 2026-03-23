from abc import ABC, abstractmethod
from typing import Protocol


class Sender(ABC):

    @abstractmethod
    def send(self, message: str) -> None:
        ...


class EmailSender(Sender):

    def send(self, message: str) -> None:
        print(f"Sending email: {message}")


class SmsSender(Sender):
    def send(self, message: str) -> None:
        print(f"Sending SMS: {message}")


class TestSender(Sender):

    def send(self, message: str) -> None:
        print(f"Sending test: {message}")


class SenderProtocol(Protocol):

    def send(self, message: str) -> None:
        ...


class TelegramSender:

    def send(self, message: str) -> None:
        print(f"Sending Telegram: {message}")


class WhatsAppSender:

    def send(self, message: str) -> None:
        print(f"Sending WhatsApp: {message}")


class NotASender:
    def send(self, message: str) -> None:
        print(f"Not sending: {message}")


class SenderService:
    sender: Sender

    def __init__(self, sender: Sender) -> None:
        self.sender = sender

    def send(self, message: str) -> None:
        self.sender.send(message)


class SenderProtocolService:
    sender: SenderProtocol

    def __init__(self, sender: SenderProtocol) -> None:
        self.sender = sender

    def send(self, message: str) -> None:
        self.sender.send(message)


if __name__ == "__main__":
    not_a_sender = NotASender()

    sender_service = SenderProtocolService(not_a_sender)
    sender_service.send("Hello")
