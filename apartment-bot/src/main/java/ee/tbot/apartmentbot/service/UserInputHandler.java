package ee.tbot.apartmentbot.service;

import ee.tbot.apartmentbot.entity.UserFilters;
import lombok.AllArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.HashMap;
import java.util.Map;
@AllArgsConstructor
@Component
public class UserInputHandler {
    private final Map<Long, UserFilters> userFiltersMap = new HashMap<>();
    private final Map<Long, String> userStateMap = new HashMap<>();

    private SendMessage createSendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        return message;
    }

    public SendMessage startFilterSetup(long chatId) {
        userStateMap.put(chatId, "ASK_MIN_AREA");
        return createSendMessage(chatId, "Enter the min area of the apartment");
    }

    public SendMessage handleUserInput(long chatId, String input) {
        String state = userStateMap.get(chatId);
        UserFilters filters = userFiltersMap.computeIfAbsent(chatId, k -> new UserFilters());
        SendMessage responseMessage = createSendMessage(chatId, "Filters Updated");

        try {
            switch (state) {
                case "ASK_MIN_AREA":
                    filters.setMinArea(Integer.parseInt(input));
                    userStateMap.put(chatId, "ASK_MAX_AREA");
                    responseMessage = createSendMessage(chatId, "Enter the max area of the apartment");
                    break;
                case "ASK_MAX_AREA":
                    int maxArea = Integer.parseInt(input);
                    if (maxArea <= filters.getMinArea()) {
                        responseMessage = createSendMessage(chatId, "Max area must be greater than min area. Enter the max area of the apartment:");
                    } else {
                        filters.setMaxArea(maxArea);
                        userStateMap.put(chatId, "ASK_MIN_PRICE");
                        responseMessage = createSendMessage(chatId, "Enter the min price of the apartment");
                    }
                    break;
                case "ASK_MIN_PRICE":
                    filters.setMinPrice(Integer.parseInt(input));
                    userStateMap.put(chatId, "ASK_MAX_PRICE");
                    responseMessage = createSendMessage(chatId, "Enter the max price of the apartment");
                    break;
                case "ASK_MAX_PRICE":
                    int maxPrice = Integer.parseInt(input);
                    if (maxPrice <= filters.getMinPrice()) {
                        responseMessage = createSendMessage(chatId, "Max price must be greater than min price. Enter the max price of the apartment:");
                    }
                    break;
                default:
                    responseMessage = createSendMessage(chatId, "Invalid input");
                    break;
            }
        } catch (NumberFormatException e) {
            responseMessage = createSendMessage(chatId, "Please enter a valid number");
        }
        return responseMessage;
    }

    public SendMessage resetFilters(long chatId) {
        userFiltersMap.remove(chatId);
        return startFilterSetup(chatId);
    }

    public UserFilters getUserFilters(long chatId) {
        return userFiltersMap.getOrDefault(chatId, new UserFilters());
    }

    public String getUserState(long chatId) {
        return userStateMap.get(chatId);
    }
}
