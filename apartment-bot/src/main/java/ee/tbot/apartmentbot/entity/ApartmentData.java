package ee.tbot.apartmentbot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.Data;
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
