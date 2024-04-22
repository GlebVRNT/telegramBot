package ee.tbot.apartmentbot.bot;

public interface MessageSender {
    void sendMessage(long chatId, String message);
}
