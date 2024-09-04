package ee.tbot.apartmentbot.bot;

import ee.tbot.apartmentbot.factory.CommandFactory;
import ee.tbot.apartmentbot.factory.MessageBuilder;
import ee.tbot.apartmentbot.factory.action.StartAction;
import ee.tbot.apartmentbot.service.UserInputHandler;
import lombok.AllArgsConstructor;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@AllArgsConstructor
public class ApartmentBot extends TelegramLongPollingBot {

    private final String botToken;
    private final CommandFactory commandFactory;
    private final UserInputHandler userInputHandler;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            final String text = update.getMessage().getText();
            final Long chatId = update.getMessage().getChatId();
            final SendMessage message;

            if (text.startsWith("/")) {
                message = commandFactory.getAction(text).getMessage(chatId);
                sendMessage(message);
            } else {
                SendMessage responseMessage = userInputHandler.handleUserInput(chatId, text);
                sendMessage(responseMessage);

                if (responseMessage.getText().equals("Filters saved")) {
                    SendMessage menuMessage = new StartAction(userInputHandler).finalizeSetup(chatId);
                    sendMessage(menuMessage);
                }
            }

        } else if (update.hasCallbackQuery()) {
            String callBackData = update.getCallbackQuery().getData();
            Long chatId = update.getCallbackQuery().getMessage().getChatId();

            if (shouldShowCommandList(update)) {
                sendCommandList(chatId);
                //sendCommandList(update.getCallbackQuery().getMessage().getChatId());
            } else {
                SendMessage message = commandFactory.getAction(callBackData).getMessage(chatId);
                sendMessage(message);
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