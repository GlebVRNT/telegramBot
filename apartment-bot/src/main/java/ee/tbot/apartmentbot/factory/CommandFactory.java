package ee.tbot.apartmentbot.factory;

import ee.tbot.apartmentbot.bot.ApartmentBot;
import ee.tbot.apartmentbot.service.ApartmentService;
import ee.tbot.apartmentbot.service.UserInputHandler;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class CommandFactory {
    private final Map<String,  StartAction> actions = new HashMap<>();

    public CommandFactory(ApartmentBot apartmentBot) {
        actions.put("/start", new StartAction(apartmentBot));
    }

    public StartAction getStartAction(String command) {
        return actions.get(command);
    }
//    private final ApartmentService apartmentService;
//    private final UserInputHandler userInputHandler;
//    private final ApartmentBot apartmentBot;
    //private final MessageService messageService;

//    public Action getAction(String command) {
//        switch (command) {
//            case "/start":
//                return new StartAction(messageService);
//            default:
//                return new StartAction(messageService);
//        }
//    }
}
