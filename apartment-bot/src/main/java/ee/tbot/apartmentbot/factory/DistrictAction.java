package ee.tbot.apartmentbot.factory;

import ee.tbot.apartmentbot.api.ApiEndpointUtils;
import ee.tbot.apartmentbot.bot.ApartmentBot;
import ee.tbot.apartmentbot.bot.DistrictMapper;
import ee.tbot.apartmentbot.service.ApartmentService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DistrictAction implements Action {
    private final ApartmentService apartmentService;
    private final ApartmentBot apartmentBot;

    @Override
    public void execute(Long chatId, String command) {
        Long timestamp = ApiEndpointUtils.getTimestampMinusTwoWeeks();
        String districtId = DistrictMapper.getDistrictId(command);
        if (districtId != null) {
            String message = apartmentService.fetchAndFormatApartmentInfo(chatId, timestamp, districtId, command);
            apartmentBot.sendMessage(chatId, message);
        } else {
            apartmentBot.sendMessage(chatId, "Unknown Command. Use one of provided above");
        }
    }
}
