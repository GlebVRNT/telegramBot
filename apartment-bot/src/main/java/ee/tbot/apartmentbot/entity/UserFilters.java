package ee.tbot.apartmentbot.entity;

import lombok.Data;

@Data
public class UserFilters {
    private int minArea;
    private int maxArea;
    private int minPrice;
    private int maxPrice;
}
