//package com.example.apartment_for_sale_bot.bot;
//
//import com.example.apartment_for_sale_bot.service.UserInputHandler;
//
//import java.util.HashMap;
//
//public class UserStateStorage {
//    private static final HashMap<Long, UserState> userStates = new HashMap<>();
//    private static final HashMap<Long, UserInputHandler> userFilters = new HashMap<>();
//
//    public static void setUserStates(Long chatId, UserState state) {
//        userStates.put(chatId, state);
//    }
//    public static  UserState getUserState(Long chatId) {
//        return userStates.getOrDefault(chatId, UserState.IDLE);
//    }
//    public static void  setUserFilter(Long chatId, UserInputHandler filter) {
//        userFilters.put(chatId, filter);
//    }
//    public static UserInputHandler getUserFilter(Long chatId) {
//        return userFilters.get(chatId);
//    }
//}
