package com.example.apartment_for_sale_bot.configuration;

import com.example.apartment_for_sale_bot.ApartmentEntity.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApartmentApiService {
    @Value("${https://api.city24.ee/et_EE/search/realties?address[cc]=1&address[parish][]=181&tsType=sale&unitType=Apartment&price[lte]=119669&size[gte]=12&datePublished[gte]=1706055238&adReach=1&itemsPerPage=55&page=1}")
    private String apiUrl;
    private final RestTemplate restTemplate;

    public ApartmentApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ApiResponse fetchApartmentsFromApi() {
        return restTemplate.getForObject(apiUrl, ApiResponse.class);
    }
}
