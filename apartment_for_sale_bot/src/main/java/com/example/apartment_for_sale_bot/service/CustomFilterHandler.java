package com.example.apartment_for_sale_bot.service;

public class CustomFilterHandler {
    private Long chatId;
    private Integer minPrice;
    private Integer maxPrice;

    public CustomFilterHandler(Long chatId, Integer minPrice, Integer maxPrice) {
        this.chatId = chatId;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }
    public CustomFilterHandler() {

    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }
}
