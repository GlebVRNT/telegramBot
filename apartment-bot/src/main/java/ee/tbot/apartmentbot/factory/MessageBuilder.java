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
    SendMessage message = generateMessage(chatId, "Choose a Command:");
    message.setReplyMarkup(CommandKeyboardBuilder.buildCommandKeyboard());
    return message;
    //return generateMessage(chatId, commandText());
  }

  /*private static String commandText() {
    return "Command list:\n" +
           "/start\n" +
            "/setfilters\n" +
            "/apartments - List of 10 newest apartments\n" +
            "/lasnamae\n" +
            "/kopli\n" +
            "/mustamae\n" +
            "/haabersti\n" +
            "/kesklinn\n" +
            "/nomme\n" +
            "/kristiine\n";
  }*/
}
