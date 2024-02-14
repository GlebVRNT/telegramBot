package com.example.apartment_for_sale_bot.ApartmentEntity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApartmentData {
    @JsonProperty("date_published")
    private String datePublished;

    @JsonProperty("friendly_id")
    private String friendlyId;

    @JsonProperty("id")
    //ид района
    private Address address;

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public String getFriendlyId() {
        return friendlyId;
    }

    public void setFriendlyId(String friendlyId) {
        this.friendlyId = friendlyId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
