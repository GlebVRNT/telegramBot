//package ee.tbot.apartmentbot.service;
//
//import lombok.AllArgsConstructor;
//import org.telegram.telegrambots.bots.DefaultAbsSender;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//
//@AllArgsConstructor
//public class TelegramService {
//
//    private DefaultAbsSender sender;
//    private long chatId = 1419026128;
//
//    public void sendTelegramMessage(long chatId, String message) {
//        SendMessage request = new SendMessage(String.valueOf(chatId), message);
//        try {
//            sender.execute(request);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//}
