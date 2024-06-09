package ee.tbot.apartmentbot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApiResponse {
    @JsonProperty("date_published")
    private String datePublished;
    @JsonProperty("friendly_id")
    private String friendlyId;
    private Long id;

    @Override
    public String toString() {
        return "ApiResponse{" + "datePublished=" + datePublished + ", friendlyId='" + friendlyId + '\'' + ", id=" + id + '}';
    }
}
