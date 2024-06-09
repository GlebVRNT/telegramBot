package ee.tbot.apartmentbot.factory;

import ee.tbot.apartmentbot.bot.ApartmentBot;
import ee.tbot.apartmentbot.service.ApartmentService;
import ee.tbot.apartmentbot.service.UserInputHandler; //TODO!
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class CommandFactory {
    private final Map<String, Action> actions = new HashMap<>();

    public CommandFactory(ApartmentBot apartmentBot, ApartmentService apartmentService) {
        actions.put("/start", new StartAction(apartmentBot));
        actions.put("/apartments", new ApartmentsAction(apartmentService, apartmentBot));

        actions.put("/mustamae", new DistrictAction(apartmentService, apartmentBot));
        actions.put("/lasnamae", new DistrictAction(apartmentService, apartmentBot));
        actions.put("/kopli", new DistrictAction(apartmentService, apartmentBot));
        actions.put("/haabersti", new DistrictAction(apartmentService, apartmentBot));
        actions.put("/kesklinn", new DistrictAction(apartmentService, apartmentBot));
        actions.put("/nomme", new DistrictAction(apartmentService, apartmentBot));
        actions.put("/kristiine", new DistrictAction(apartmentService, apartmentBot));
    }

    public Action getAction(String command) {
        return actions.get(command);
    }

}
