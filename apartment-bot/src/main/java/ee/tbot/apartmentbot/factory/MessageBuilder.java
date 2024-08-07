package ee.tbot.apartmentbot.factory;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class MessageBuilder {

  public static SendMessage generateMessage(long chatId, String message) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(String.valueOf(chatId));
    sendMessage.setText(message);
    return sendMessage;
  }

  public static SendMessage generateShowCommandMessage(long chatId) {
    return generateMessage(chatId, commandText());
  }

  private static String commandText() {
    return "Command list:\n" +
            "/start\n" +
            "/setfilters\n" +
            "/apartments - List of 10 newest apartments\n" +
            "/mustamae\n" +
            "/lasnamae\n" +
            "/kopli\n" +
            "/haabersti\n" +
            "/kesklinn\n" +
            "/nomme\n" +
            "/kristiine\n";
  }
}
