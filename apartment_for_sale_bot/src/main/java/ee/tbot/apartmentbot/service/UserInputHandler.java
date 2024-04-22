package ee.tbot.apartmentbot.service;

import ee.tbot.apartmentbot.bot.MessageSender;
import ee.tbot.apartmentbot.bot.UserState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
@Lazy
@Component
public class UserInputHandler {
    private static final Logger logger = LoggerFactory.getLogger(UserInputHandler.class);
    private final MessageSender messageSender;
    private final ApartmentService apartmentService;
    private final Map<Long, UserState> userStates = new HashMap<>();
    private final Map<Long, Integer> userMinPrices = new HashMap<>();
    private final Map<Long, Integer> userMaxPrices = new HashMap<>();

    public UserInputHandler(MessageSender messageSender, ApartmentService apartmentService) {
        this.messageSender = messageSender;
        this.apartmentService = apartmentService;
        logger.info("UserHandler bean created");
    }

    public void handleStartCommand(long chatId) {
        sendMessage(chatId, "Enter the min price of apartment:");
        setUserState(chatId, UserState.AWAITING_MIN_PRICE);
    }

    public void handleUserInput(long chatId, String input) {
        UserState state = getUserState(chatId);
        switch (state) {
            case AWAITING_MIN_PRICE:
                handleMinPriceInput(chatId, input);
                break;
            case AWAITING_MAX_PRICE:
                handleMaxPriceInput(chatId, input);
                break;
            default:
                sendMessage(chatId, "I didn't understand that. Please use one of the commands.");
        }
    }

    private void handleMinPriceInput(long chatId, String input) {
        try {
            int minPrice = Integer.parseInt(input);
            userMinPrices.put(chatId, minPrice);
            sendMessage(chatId, "Enter the max price:");
            setUserState(chatId, UserState.AWAITING_MAX_PRICE);
        } catch (NumberFormatException e) {
            sendMessage(chatId, "Invalid input. Please enter a valid input");
        }
    }

    public void handleMaxPriceInput(long chatId, String input) {
        try {
            int maxPrice = Integer.parseInt(input);
            int minPrice = userMinPrices.get(chatId);
            if (maxPrice < minPrice) {
                sendMessage(chatId, "Maximum price cannot be less than minimum price");
                return;
            }
            userMaxPrices.put(chatId, maxPrice);
            String apiUrl = apartmentService.getApiUrl()
                    .replace("{minPrice}", String.valueOf(userMinPrices.get(chatId)))
                    .replace("{maxPrice}", String.valueOf(maxPrice));
            //String message = apartmentService.fetchAndFormatApartmentInfo(chatId, apiUrl);
            //sendMessage(chatId, message);
            setUserState(chatId, UserState.IDLE);
        } catch (NumberFormatException e) {
            sendMessage(chatId, "Invalid input. Please enter a valid number.");
        }
    }

    private void sendMessage(long chatId, String message) {
        messageSender.sendMessage(chatId, message);
    }

    private void setUserState(long chatId, UserState state) {
        userStates.put(chatId, state);
    }

    private UserState getUserState(long chatId) {
        return userStates.getOrDefault(chatId, UserState.IDLE);
    }
}