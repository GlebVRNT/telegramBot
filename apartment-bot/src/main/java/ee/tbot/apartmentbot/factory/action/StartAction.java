package ee.tbot.apartmentbot.factory.action;

import java.util.ArrayList;
import java.util.List;

import ee.tbot.apartmentbot.factory.MessageBuilder;
import ee.tbot.apartmentbot.service.UserInputHandler;
import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@AllArgsConstructor
public class StartAction implements Action {

    private final UserInputHandler userInputHandler;

    @Override
    public SendMessage getMessage(Long chatId) {
        return  userInputHandler.startFilterSetup(chatId);
    }


    public SendMessage sendMenu(long chatId, String messageText) {
        SendMessage message = MessageBuilder.generateMessage(chatId, messageText);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Show commands");
        button.setCallbackData("show_commands");

        rowInline.add(button);

        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);
        return message;
    }

    public SendMessage finalizeSetup(long chatId) {
        return sendMenu(chatId, "Filters updated");
    }

    private SendMessage createSendMessage(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        return message;
    }

}
