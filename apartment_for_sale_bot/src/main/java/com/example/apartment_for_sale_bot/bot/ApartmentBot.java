package com.example.apartment_for_sale_bot.bot;

import com.example.apartment_for_sale_bot.ApartmentEntity.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.objects.ApiResponse;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApartmentBot extends TelegramLongPollingBot {
    @Value("${bot.token}")
    private String botToken;

    @Value("${api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public ApartmentBot(RestTemplate restTemplate) {

        this.restTemplate = restTemplate;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            long chatId = update.getMessage().getChatId();
            String command = update.getMessage().getText();
            
            String mustamaeLink = "https://www.city24.ee/real-estate-search/apartments-for-sale/tallinn/price=eur-80000-110000/size=32-na/id=2413-city";
            String lasnamaeLink = "https://www.city24.ee/real-estate-search/apartments-for-sale/tallinn/price=eur-80000-110000/size=32-na/id=1897-city";
            String kopliLink = "https://www.city24.ee/real-estate-search/apartments-for-sale/tallinn/price=eur-80000-110000/size=32-na/id=3166-city";
            String haaberstiLink = "https://www.city24.ee/real-estate-search/apartments-for-sale/tallinn/price=eur-80000-110000/size=32-na/id=540-city";
            String kesklinnLink = "https://www.city24.ee/real-estate-search/apartments-for-sale/tallinn/price=eur-80000-110000/size=32-na/id=1240-city";
            String nommeLink = "https://www.city24.ee/real-estate-search/apartments-for-sale/tallinn/price=eur-80000-110000/size=32-na/id=2670-city";
            String kristiineLink = "https://www.city24.ee/real-estate-search/apartments-for-sale/tallinn/price=eur-80000-110000/size=32-na/id=1535-city";

            switch (command) {
                case "/start":
                    sendMenu(chatId, "Welcome! Press the button below to continue");
                    break;
                case "/apartments":
                    sendApartmentsInfo(chatId);
                    break;
                case "/musta":
                    sendMessageWithHtml(chatId, getHtmlLink(mustamaeLink, "Link to the Mustamäe apartments"));
                    break;
                case "/lasna":
                    sendMessageWithHtml(chatId, getHtmlLink(lasnamaeLink, "Link to the Lasnamäe apartments"));
                    break;
                case "/kopli":
                    sendMessageWithHtml(chatId, getHtmlLink(kopliLink, "Link to the Kopli apartments"));
                    break;
                case "/haabersti":
                    sendMessageWithHtml(chatId, getHtmlLink(haaberstiLink, "Link to the Haabersti apartments"));
                    break;
                case "/kesklinn":
                    sendMessageWithHtml(chatId, getHtmlLink(kesklinnLink, "Link to the Kesklinn apartments"));
                    break;
                case "/nomme":
                    sendMessageWithHtml(chatId, getHtmlLink(nommeLink, "Link to the Nõmme apartments"));
                    break;
                case "/kristiine":
                    sendMessageWithHtml(chatId, getHtmlLink(kristiineLink, "Link to the Kristiine apartments"));
                    break;
                default:
                    sendMessageWithHtml(chatId, "Unknown Command. Use one of provided above");
                break;
            }

        } else if (update.hasCallbackQuery()) {
            String callData = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            if (callData.equals("show_commands")) {
                String commands = "Вот список команд:\n" +
                        "/start - показать это сообщение\n" +
                        "/apartments - получить список квартир\n" +
                        "/musta\n" +
                        "/lasna\n" +
                        "/kopli\n" +
                        "/haabersti\n" +
                        "/kesklinn\n" +
                        "/nomme\n" +
                        "/kristiine\n";

                sendMessage(chatId, commands);
            }
        }
    }
    private void sendApartmentsInfo(Long chatId) {
        ResponseEntity<ApiResponse[]> responseEntity = restTemplate.getForEntity(apiUrl, ApiResponse[].class);
        ApiResponse[] responses = responseEntity.getBody();

        if (responses != null && responses.length > 0) {
            List<ApiResponse> responseList = new ArrayList<>(Arrays.asList(responses));
            responseList.removeIf(response -> response.getFriendlyId() == null);

            responseList.sort(Comparator.comparing(ApiResponse::getDatePublished, Comparator.nullsLast(Comparator.reverseOrder())));

            if (!responseList.isEmpty()) {
                StringBuilder messageText = new StringBuilder();

                for (ApiResponse response : responseList) {
                    String  datePublished = response.getDatePublished();

                    System.out.println("Response: " + response);

                        String apartmentLink = "https://www.city24.ee/real-estate/apartments-for-sale/tallinn/" + response.getFriendlyId();
                        String apartmentInfo = "Get Published: " + datePublished;
                        messageText.append("<a href='").append(apartmentLink).append("'>").append(apartmentInfo).append("</a>\n");
                }
                sendMessageWithHtml(chatId, messageText.toString());
                System.out.println("Response: " + messageText);
            } else {
                sendMessage(chatId, "К сожалению, данных нет.");
            }
        } else {
            sendMessage(chatId, "К сожалению, данные не были получены.");
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
