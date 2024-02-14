package com.example.apartment_for_sale_bot.configuration;

import com.example.apartment_for_sale_bot.bot.ApartmentBot;

import com.example.apartment_for_sale_bot.service.TelegramService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class ApartmentBotConfig {
    @Bean
    public TelegramBotsApi telegramBotsApi(ApartmentBot apartmentBot) throws TelegramApiException {
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(apartmentBot);
        return api;
    }
    @Bean
    public TelegramService telegramService(ApartmentBot apartmentBot) {

        return new TelegramService(apartmentBot);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
