package com.example.apartment_for_sale_bot.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.io.IOException;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class ApartmentBot extends TelegramLongPollingBot {
    @Value("${bot.token}")
    private String botToken;

    private final List<String> endpoints = List.of(
            "https://api.city24.ee/et_EE/search/realties?address[cc]=1&address[parish][]=181&tsType=sale&unitType=Apartment&price[gte]=80000&price[lte]=110000&size[gte]=32&adReach=1&itemsPerPage=55&page=1",
            "https://api.city24.ee/et_EE/search/realties?address[cc]=1&address[city][]=540&tsType=sale&unitType=Apartment&price[gte]=80000&price[lte]=110000&size[gte]=32&size[lte]=50&adReach=1&itemsPerPage=55&page=1&orderBy=lastAdded"
    );

    public ApartmentBot() {

        super();
    }
    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            long chatId = update.getMessage().getChatId();
            String command = update.getMessage().getText();

            String apartmentsLink = "https://www.city24.ee/real-estate-search/apartments-for-sale/tallinn/price=eur-80000-110000/size=32-na/id=181-parish";


            switch (command) {
                case "/start":
                    sendMenu(chatId, "Welcome! Press the button below to continue");
                    break;
                case "/apartments":
                    sendMessageWithHtml(chatId, getHtmlLink(apartmentsLink, "Link to the all apartments"));
                    break;
            }

        } else if (update.hasCallbackQuery()) {
            String callData = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            if (callData.equals("show_commands")) {
                String commands = "Вот список команд:\n" +
                        "/start - показать это сообщение\n" +
                        "/apartments - получить список квартир\n" +
                        "/mustamae\n" +
                        "/lasnamae\n" +
                        "/kopli\n" +
                        "/haabersti\n" +
                        "/kesklinn\n" +
                        "/nomme\n" +
                        "/kristiine\n" +
                        "/pirita\n";
                sendMessage(chatId, commands);
            }
        }
    }

    private String getHtmlLink(String url, String text) {
        return "<a href='" + url + "'>" + text + "</a>";
    }

    private void sendMessageWithHtml(long chatId, String messageText) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(messageText);
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
        button.setText("Показать команды");
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
            System.out.println("Error while sending HTML message: " + e.getMessage());
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
