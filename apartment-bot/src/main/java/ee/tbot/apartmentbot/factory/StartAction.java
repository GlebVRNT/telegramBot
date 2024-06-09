package ee.tbot.apartmentbot.factory;

import ee.tbot.apartmentbot.bot.ApartmentBot;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StartAction implements Action {
    private final ApartmentBot apartmentBot;

    @Override
    public void execute(Long chatId, String command) {
        apartmentBot.sendMenu(chatId, "Welcome");
    }
}
