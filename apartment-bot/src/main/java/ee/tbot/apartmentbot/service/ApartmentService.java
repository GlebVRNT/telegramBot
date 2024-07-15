package ee.tbot.apartmentbot.service;

import ee.tbot.apartmentbot.bot.District;
import ee.tbot.apartmentbot.entity.ApiResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApartmentService {
    private final RestTemplate restTemplate;

    @Getter
    @Value("${api.url}")
    private String apiUrl;

    public ApartmentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String fetchAndFormatApartmentInfo(Long timestamp, District district) {
        String apiUrlWithTimestamp = apiUrl.replace("{timestamp}", timestamp.toString());
        String apiUrlWithDistrict;

        if (district != null) {
            apiUrlWithDistrict = apiUrlWithTimestamp.replace("address[parish][]=181", "address[city][]=" + district.getCode());
        } else {
            apiUrlWithDistrict = apiUrlWithTimestamp;
        }

        ResponseEntity<ApiResponse[]> responseEntity = restTemplate.getForEntity(apiUrlWithDistrict, ApiResponse[].class);
        ApiResponse[] responses = responseEntity.getBody();

        if (responses != null && responses.length > 0) {
            List<ApiResponse> responseList = new ArrayList<>(Arrays.asList(responses));
            responseList.removeIf(response -> response.getFriendlyId() == null);

            responseList.sort(Comparator.comparing(ApiResponse::getDatePublished, Comparator.nullsLast(Comparator.reverseOrder())));

            if (!responseList.isEmpty()) {
                StringBuilder messageText = new StringBuilder();
                for (ApiResponse response : responseList) {
                    String datePublished = response.getDatePublished();
                    String apartmentLink = "https://www.city24.ee/real-estate/apartments-for-sale/tallinn/" + response.getFriendlyId();
                    String apartmentInfo = "Date Published: " + datePublished;
                    messageText.append(apartmentLink + "\n").append(apartmentInfo).append("\n");
                }
                return messageText.toString();
            } else {
                return "No Data";
            }
        } else {
            return "No Data was provided";
        }
    }
}
