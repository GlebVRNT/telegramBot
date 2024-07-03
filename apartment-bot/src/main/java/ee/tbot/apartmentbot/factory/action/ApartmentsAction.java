package ee.tbot.apartmentbot.factory.action;

import ee.tbot.apartmentbot.api.ApiEndpointUtils;
import ee.tbot.apartmentbot.bot.ApartmentBot;
import ee.tbot.apartmentbot.factory.MessageBuilder;
import ee.tbot.apartmentbot.service.ApartmentService;
import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@AllArgsConstructor
public class ApartmentsAction implements Action {
    private final ApartmentService apartmentService;

    @Override
    public SendMessage getMessage(Long chatId) {
        Long timestamp = ApiEndpointUtils.getTimestampMinusTwoWeeks();
        String message = apartmentService.fetchAndFormatApartmentInfo(timestamp, null);
        return MessageBuilder.generateMessage(chatId, message);
    }


}
