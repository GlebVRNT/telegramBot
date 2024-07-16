package ee.tbot.apartmentbot.bot;

import ee.tbot.apartmentbot.factory.CommandFactory;
import ee.tbot.apartmentbot.factory.MessageBuilder;
import ee.tbot.apartmentbot.service.UserInputHandler;
import lombok.AllArgsConstructor;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@AllArgsConstructor
public class ApartmentBot extends TelegramLongPollingBot {

    private final String botToken;
    private final UserInputHandler userInputHandler;
    private final CommandFactory commandFactory;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
          final String text = update.getMessage().getText();
          final Long chatId = update.getMessage().getChatId();
          final SendMessage message = commandFactory.getAction(text).getMessage(chatId);
          sendMessage(message);
        } else if (update.hasCallbackQuery()) {
            if (shouldShowCommandList(update)) {
                sendCommandList(update.getCallbackQuery().getMessage().getChatId());
            }
        }
    }

    private void sendCommandList(Long chatId) {
        final SendMessage message = MessageBuilder.generateShowCommandMessage(chatId);
        sendMessage(message);
    }

    private static boolean shouldShowCommandList(Update update) {
        return update.getCallbackQuery().getData().equals("show_commands");
    }

//    private void startFilterSetup(long chatId) {
//        userInputHandler.handleStartCommand(chatId);
//    }

    private void sendMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "apartment_for_sale_bot";
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}