package ee.tbot.apartmentbot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
@Data
public class ApartmentData {
    @JsonProperty("date_published")
    private Date datePublished;

    @JsonProperty("friendly_id")
    private String friendlyId;

    @JsonProperty("id")
    //ид района
    private Address address;

}
