package ee.tbot.apartmentbot.config;

import ee.tbot.apartmentbot.bot.ApartmentBot;

import ee.tbot.apartmentbot.service.ApartmentService;
import ee.tbot.apartmentbot.service.TelegramService;
import ee.tbot.apartmentbot.service.UserInputHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Slf4j
@Configuration
public class ApartmentBotConfig {
    @Bean
    public TelegramBotsApi telegramBotsApi(ApartmentBot apartmentBot) throws TelegramApiException {
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(apartmentBot);
        return api;
    }

    @Bean
    public ApartmentBot apartmentBot(@Value("${bot.token}") String token, ApartmentService apartmentService, UserInputHandler userInputHandler) {
        final ApartmentBot apartmentBot = new ApartmentBot(token, apartmentService, userInputHandler);
        log.info("ApartmentBot bean is created");
        return apartmentBot;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
