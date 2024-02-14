package com.example.apartment_for_sale_bot.api;

import com.example.apartment_for_sale_bot.ApartmentEntity.Address;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {

    public Address parseJson(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, Address.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
