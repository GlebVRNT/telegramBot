package com.example.apartment_for_sale_bot.bot;

import com.example.apartment_for_sale_bot.service.TelegramService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

@Component
public class ApartmentBot extends TelegramLongPollingBot {
    @Value("${bot.token}")
    private String botToken;

    public ApartmentBot() {
        super();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            long chatId = update.getMessage().getChatId();
            System.out.println(chatId);

            TelegramService telegramService = new TelegramService(this);
            String messageText = "Thank you!";
            telegramService.sendTelegramMessage(chatId, messageText);
        }

    }
    @Override
    public String getBotUsername() {
        return "apartment_for_sale_bot";
    }

    @Override
    public String getBotToken() {
        return "6626966342:AAEMpil7M2pCXb-nZWer_g8fE7z-9t-8gBo";
    }
}
