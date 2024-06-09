package ee.tbot.apartmentbot.factory;

//import ee.tbot.apartmentbot.bot.ApartmentBot;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//
//
//import java.util.ArrayList;
//import java.util.List;
//@AllArgsConstructor
//@Service
//public class MessageService {
//
//
//    public void sendMenu(long chatId, String messageText) {
//        SendMessage message = new SendMessage();
//        message.setChatId(String.valueOf(chatId));
//        message.setText(messageText);
//
//        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
//        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
//        List<InlineKeyboardButton> rowInline = new ArrayList<>();
//
//        InlineKeyboardButton button = new InlineKeyboardButton();
//        button.setText("Show commands");
//        button.setCallbackData("show_commands");
//
//        rowInline.add(button);
//
//        rowsInline.add(rowInline);
//        markupInline.setKeyboard(rowsInline);
//        message.setReplyMarkup(markupInline);
//
//        try {
//            apartmentBot.execute(message);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//}
