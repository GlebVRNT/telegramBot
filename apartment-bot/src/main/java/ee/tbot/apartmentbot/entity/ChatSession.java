package ee.tbot.apartmentbot.entity;

import ee.tbot.apartmentbot.bot.UserState;
import lombok.Data;

@Data
public class ChatSession {
    private long chatId;
    private UserState state;
    private Integer minPrice;
    private Integer maxPrice;

}
