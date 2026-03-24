import pytest

from src.protocol_vs_abc import EmailSender, SenderService, SmsSender, TelegramSender, TestSender, \
    SenderProtocolService, WhatsAppSender, NotASender, Sender


def test_sender_service_works_with_email_sender(capsys):
    sender = EmailSender()
    service = SenderService(sender)
    service.send("Hello")
    captured = capsys.readouterr()
    assert "Sending email: Hello" in captured.out


def test_sender_service_works_with_sms_sender(capsys):
    sender = SmsSender()
    service = SenderService(sender)
    service.send("Hello")
    captured = capsys.readouterr()
    assert "Sending SMS: Hello" in captured.out


def test_sender_service_works_with_test_sender(capsys):
    sender = TestSender()
    service = SenderService(sender)
    service.send("Hello")
    captured = capsys.readouterr()
    assert "Sending test: Hello" in captured.out


def test_sender_protocol_service_works_with_telegram_sender(capsys):
    sender = TelegramSender()
    service = SenderProtocolService(sender)
    service.send("Hello")
    captured = capsys.readouterr()
    assert "Sending Telegram: Hello" in captured.out


def test_sender_protocol_service_works_with_whatsapp_sender(capsys):
    sender = WhatsAppSender()
    service = SenderProtocolService(sender)
    service.send("Hello")
    captured = capsys.readouterr()
    assert "Sending WhatsApp: Hello" in captured.out


def test_sender_protocol_service_accepts_not_a_sender(capsys):
    sender = NotASender()
    service = SenderProtocolService(sender)
    service.send("Hello")
    captured = capsys.readouterr()
    assert "Not sending: Hello" in captured.out


def test_sender_abstract_class_cannot_be_instantiated():
    with pytest.raises(TypeError):
        Sender()
