package com.example.apartment_for_sale_bot.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ApiEndpointUtils {
    public static Long getTimestampMinusTwoWeeks() {
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime dateTwoWeeksAgo = currentDate.minusWeeks(2);
        return dateTwoWeeksAgo.toEpochSecond(java.time.ZoneOffset.UTC);
    }
}
