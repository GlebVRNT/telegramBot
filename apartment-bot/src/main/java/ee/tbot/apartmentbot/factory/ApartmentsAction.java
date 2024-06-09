package ee.tbot.apartmentbot.factory;

import ee.tbot.apartmentbot.api.ApiEndpointUtils;
import ee.tbot.apartmentbot.bot.ApartmentBot;
import ee.tbot.apartmentbot.service.ApartmentService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApartmentsAction implements Action {
    private final ApartmentService apartmentService;
    private final ApartmentBot apartmentBot;

    @Override
    public void execute(Long chatId, String command) {
        Long timestqmp = ApiEndpointUtils.getTimestampMinusTwoWeeks();
        String message = apartmentService.fetchAndFormatApartmentInfo(chatId, timestqmp, null, command);
        apartmentBot.sendMessage(chatId, message);
    }
}
