package ee.tbot.apartmentbot.factory;

public interface Action {
    void execute(Long chatId, String command);
}
