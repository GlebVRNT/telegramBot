package ee.tbot.apartmentbot.bot;

import java.util.HashMap;
import java.util.Map;

public class DistrictMapper {

    private static final Map<String, String> districtIdMap = new HashMap<>();

    static {
        districtIdMap.put("/mustamae", "2413"); //mustamae
        districtIdMap.put("/lasnamae", "1897"); //lasnamae
        districtIdMap.put("/kopli", "3166"); //kopli
        districtIdMap.put("/haabersti", "540");  //haabersti
        districtIdMap.put("/kristiine", "1535"); //kristiine
        districtIdMap.put("/nomme", "2670"); //nomme
        districtIdMap.put("/kesklinn", "1240"); //kesklinn
    }

    public static String getDistrictId(String command) {
        return districtIdMap.get(command);
    }
}
