package ee.tbot.apartmentbot.factory;

import ee.tbot.apartmentbot.bot.District;
import ee.tbot.apartmentbot.factory.action.*;
import ee.tbot.apartmentbot.service.ApartmentService;
import ee.tbot.apartmentbot.service.UserInputHandler;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class CommandFactory {
    private final Map<String, Action> actions = new HashMap<>();

    public CommandFactory( ApartmentService apartmentService, UserInputHandler userInputHandler) {
        //Handler actions
        actions.put("/start", new StartAction(userInputHandler));
        actions.put("/setfilters", new SetFiltersActions(userInputHandler));

        //Search actions
        actions.put("/apartments", new ApartmentsAction(apartmentService));

        //District actions
        actions.put(District.MUSTAMAE.getCommand(), new DistrictAction(apartmentService, District.MUSTAMAE));
        actions.put(District.LASNAMAE.getCommand(), new DistrictAction(apartmentService, District.LASNAMAE));
        actions.put(District.KOPLI.getCommand(), new DistrictAction(apartmentService, District.KOPLI));
        actions.put(District.HAABERSTI.getCommand(), new DistrictAction(apartmentService, District.HAABERSTI));
        actions.put(District.KESKLINN.getCommand(), new DistrictAction(apartmentService, District.KESKLINN));
        actions.put(District.NOMME.getCommand(), new DistrictAction(apartmentService, District.NOMME));
        actions.put(District.KRISTIINE.getCommand(), new DistrictAction(apartmentService, District.KRISTIINE));
    }

    public Action getAction(String command) {
        return actions.getOrDefault(command, new NoAction());
    }

}
