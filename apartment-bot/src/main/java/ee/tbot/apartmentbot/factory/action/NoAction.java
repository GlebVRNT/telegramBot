package ee.tbot.apartmentbot.factory.action;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class NoAction implements Action {

  @Override
  public SendMessage getMessage(Long chatId) {
    return sendMessageWithHtml(chatId, "Unknown Command. Use one of provided above");
  }

  private SendMessage sendMessageWithHtml(long chatId, String message) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(String.valueOf(chatId));
    sendMessage.setText(message);
    sendMessage.setParseMode(ParseMode.HTML);
    sendMessage.enableHtml(true);
    return sendMessage;
  }
}
