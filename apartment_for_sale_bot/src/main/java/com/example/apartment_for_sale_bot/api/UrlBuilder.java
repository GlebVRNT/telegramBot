package com.example.apartment_for_sale_bot.api;

import com.example.apartment_for_sale_bot.ApartmentEntity.Apartment;
import org.springframework.stereotype.Component;

@Component
public class UrlBuilder {

    public String buildApartmentUrl(Apartment apartment) {
        Long apartmentId = apartment.getId();
        String city = apartment.getCity().getName();
        return "https://www.city24.ee/real-estate/" + city + "/" + apartmentId;
    }
}
