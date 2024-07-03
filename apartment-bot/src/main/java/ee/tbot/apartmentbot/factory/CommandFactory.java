package ee.tbot.apartmentbot.factory;

import ee.tbot.apartmentbot.bot.ApartmentBot;
import ee.tbot.apartmentbot.bot.District;
import ee.tbot.apartmentbot.factory.action.Action;
import ee.tbot.apartmentbot.factory.action.ApartmentsAction;
import ee.tbot.apartmentbot.factory.action.DistrictAction;
import ee.tbot.apartmentbot.factory.action.NoAction;
import ee.tbot.apartmentbot.factory.action.StartAction;
import ee.tbot.apartmentbot.service.ApartmentService;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class CommandFactory {
    private final Map<String, Action> actions = new HashMap<>();

    public CommandFactory( ApartmentService apartmentService) {
        //Handler actions
        actions.put("/start", new StartAction());

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
