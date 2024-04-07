package com.example.apartment_for_sale_bot.ApartmentEntity;

import com.example.apartment_for_sale_bot.bot.UserState;

public class ChatSession {
    private long chatId;
    private UserState state;
    private Integer minPrice;
    private Integer maxPrice;

    public ChatSession(long chatId) {
        this.chatId = chatId;
        this.state = UserState.IDLE;
    }

    public long getChatId() {
        return chatId;
    }

    public UserState getState() {
        return state;
    }

    public void setState(UserState state) {
        this.state = state;
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
