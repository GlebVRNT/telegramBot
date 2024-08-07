package ee.tbot.apartmentbot.factory.action;

import ee.tbot.apartmentbot.service.UserInputHandler;
import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@AllArgsConstructor
public class SetFiltersActions implements Action {
    private final UserInputHandler userInputHandler;

    @Override
    public SendMessage getMessage(Long chatId) {
        return userInputHandler.resetFilters(chatId);
    }
}
