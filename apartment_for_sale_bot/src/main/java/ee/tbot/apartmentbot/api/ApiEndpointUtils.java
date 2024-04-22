package ee.tbot.apartmentbot.api;

import java.time.LocalDateTime;

public class ApiEndpointUtils {
    public static Long getTimestampMinusTwoWeeks() {
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime dateTwoWeeksAgo = currentDate.minusWeeks(2);
        return dateTwoWeeksAgo.toEpochSecond(java.time.ZoneOffset.UTC);
    }
}
