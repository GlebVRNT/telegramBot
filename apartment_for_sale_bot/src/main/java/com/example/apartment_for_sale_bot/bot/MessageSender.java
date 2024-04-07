package com.example.apartment_for_sale_bot.bot;

public interface MessageSender {
    void sendMessage(long chatId, String message);
}
