package ee.tbot.apartmentbot.bot;

import ee.tbot.apartmentbot.api.ApiEndpointUtils;
import ee.tbot.apartmentbot.service.ApartmentService;
import ee.tbot.apartmentbot.service.UserInputHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ApartmentBot extends TelegramLongPollingBot implements MessageSender {
    private static final Logger logger = LoggerFactory.getLogger(ApartmentBot.class);

    @Value("${bot.token}")
    private String botToken;
    private final ApartmentService apartmentService;
    private final UserInputHandler userInputHandler;

    public ApartmentBot(ApartmentService apartmentService, UserInputHandler userInputHandler) {
        this.apartmentService = apartmentService;
        this.userInputHandler = userInputHandler;
        logger.info("ApartmentBot bean created");
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            long chatId = update.getMessage().getChatId();
            String command = update.getMessage().getText();
            Long timestamp = ApiEndpointUtils.getTimestampMinusTwoWeeks();

            if (command.equals("/start")) {
                sendMenu(chatId, "Welcome!");
            } else if (command.equals("/apartments")) {
                String message = apartmentService.fetchAndFormatApartmentInfo(chatId, timestamp, null, command);
                sendMessage(chatId, message);
            } else if (command.equals("/setfilters")) {
                userInputHandler.handleStartCommand(chatId);
            } else {
                String districtId = DistrictMapper.getDistrictId(command);
                if (districtId != null) {
                    String message = apartmentService.fetchAndFormatApartmentInfo(chatId, timestamp, districtId, command);
                    System.out.println(message);
                    sendMessage(chatId, message);
                } else {
                    sendMessageWithHtml(chatId, "Unknown Command. Use one of provided above");
                }
            }
        } else if (update.hasCallbackQuery()) {
            String callData = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            if (callData.equals("show_commands")) {
                sendMessage(chatId, generateCommandsMessage());
            }
        }
    }

    private String generateCommandsMessage() {
        return "Command list:\n" +
                "/start\n" +
                "/apartments - List of 10 newest apartments\n" +
                "/mustamae\n" +
                "/lasnamae\n" +
                "/kopli\n" +
                "/haabersti\n" +
                "/kesklinn\n" +
                "/nomme\n" +
                "/setfilters - set custom filters\n" +
                "/kristiine\n";
    }

    private void startFilterSetup(long chatId) {
        userInputHandler.handleStartCommand(chatId);
    }
    private void sendMessageWithHtml(long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(message);
        sendMessage.setParseMode(ParseMode.HTML);
        sendMessage.enableHtml(true);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMenu(long chatId, String messageText) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(messageText);

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

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void sendMessage(long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(message);
        try {
            execute(sendMessage);
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