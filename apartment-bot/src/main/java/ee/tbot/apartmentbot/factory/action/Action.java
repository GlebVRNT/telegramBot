package ee.tbot.apartmentbot.factory.action;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface Action {
    SendMessage getMessage(Long chatId);
}
