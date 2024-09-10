package ee.tbot.apartmentbot.factory;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CommandKeyboardBuilder {

    public static InlineKeyboardMarkup buildCommandKeyboard() {
        List<List<InlineKeyboardButton>> rowInLine = new ArrayList<>();

        rowInLine.add(Arrays.asList(
                createButton("Start", "/start"),
                createButton("Set Filters", "/setfilters")
        ));

        rowInLine.add(Arrays.asList(
                createButton("Newest Apartments", "/apartments")

        ));

        rowInLine.add(Arrays.asList(
                createButton("Mustamae", "/mustamae"),
                createButton("Haabersti", "/haabersti")
        ));

        rowInLine.add(Arrays.asList(
                createButton("Lasnamae", "/lasnamae"),
                createButton("Kopli", "/kopli")
        ));

        rowInLine.add(Arrays.asList(
                createButton("Kesklinn", "/kesklinn"),
                createButton("Nomme", "/nomme"),
                createButton("Kristiine", "/kristiine")

        ));

        InlineKeyboardMarkup buttons = new InlineKeyboardMarkup();
        buttons.setKeyboard(rowInLine);
        return buttons;
    }

    private static InlineKeyboardButton createButton(String text, String command) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(command);
        return button;
    }
}
