package ee.tbot.apartmentbot.factory.action;

import ee.tbot.apartmentbot.api.ApiEndpointUtils;
import ee.tbot.apartmentbot.bot.District;
import ee.tbot.apartmentbot.factory.MessageBuilder;
import ee.tbot.apartmentbot.service.ApartmentService;
import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@AllArgsConstructor
public class DistrictAction implements Action {

  private final ApartmentService apartmentService;
  private final District district;

  @Override
  public SendMessage getMessage(Long chatId) {
    Long timestamp = ApiEndpointUtils.getTimestampMinusTwoWeeks();
    String message = apartmentService.fetchAndFormatApartmentInfo(timestamp, district);
    return MessageBuilder.generateMessage(chatId, message);
  }
}
