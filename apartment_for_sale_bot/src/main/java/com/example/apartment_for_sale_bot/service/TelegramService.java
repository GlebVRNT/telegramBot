package com.example.apartment_for_sale_bot.service;

import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramBot;

public class TelegramService {

    private DefaultAbsSender sender;
    private long chatId = 1419026128;

    public TelegramService(DefaultAbsSender sender) {

        this.sender = sender;
    }

    public void sendTelegramMessage(long chatId, String message) {
        SendMessage request = new SendMessage(String.valueOf(chatId), message);
        try {
            sender.execute(request);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
