package com.example.apartment_for_sale_bot.bot;
import com.example.apartment_for_sale_bot.api.ApiEndpointUtils;
import com.example.apartment_for_sale_bot.service.ApartmentService;
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
public class ApartmentBot extends TelegramLongPollingBot {
    private static final Logger logger = LoggerFactory.getLogger(ApartmentBot.class);

    @Value("${bot.token}")
    private String botToken;

    private final ApartmentService apartmentService;

    public ApartmentBot(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            long chatId = update.getMessage().getChatId();
            String command = update.getMessage().getText();
            Long timestamp = ApiEndpointUtils.getTimestampMinusTwoWeeks();

            if (command.equals("/start")) {
                sendMenu(chatId, "Welcome! Press the button below to continue");
            } else if (command.equals("/apartments")) {
                String message = apartmentService.fetchAndFormatApartmentInfo(chatId, timestamp, null, command);
                sendMessageWithHtml(chatId, message);
            } else {
                String districtId = DistrictMapper.getDistrictId(command);
                if (districtId != null) {
                    String message = apartmentService.fetchAndFormatApartmentInfo(chatId, timestamp, districtId, command);
                    sendMessageWithHtml(chatId, message);
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
                    "/kristiine\n";
    }
    private String getHtmlLink(String url, String text) {
        return "<a href='" + url + "'>" + text + "</a>";
    }

    private void sendMessageWithHtml(long chatId, String messageText) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(messageText);
        message.setParseMode(ParseMode.HTML);
        message.enableHtml(true);

        try {
            execute(message);
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

    private void sendMessage(long chatId, String messageText) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(messageText);

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
