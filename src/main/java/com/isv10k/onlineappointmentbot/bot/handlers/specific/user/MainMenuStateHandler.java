package com.isv10k.onlineappointmentbot.bot.handlers.specific.user;

import com.isv10k.onlineappointmentbot.bot.MessageCreator;
import com.isv10k.onlineappointmentbot.bot.buttons.BotText;
import com.isv10k.onlineappointmentbot.bot.cache.UserInMemoryStateCache;
import com.isv10k.onlineappointmentbot.bot.handlers.specific.UserTypeStateMessageHandler;
import com.isv10k.onlineappointmentbot.bot.keyboards.StartKeyboards;
import com.isv10k.onlineappointmentbot.bot.states.BotState;
import com.isv10k.onlineappointmentbot.bot.states.BotUserState;
import com.isv10k.onlineappointmentbot.services.AppointmentService;
import com.isv10k.onlineappointmentbot.services.TimeSlotService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class MainMenuStateHandler implements UserTypeStateMessageHandler {

    private final UserInMemoryStateCache userInMemoryStateCache;
    private final MessageCreator messageCreator;
    private final StartKeyboards startKeyboards;
    private final TimeSlotService timeSlotService;
    private final AppointmentService appointmentService;

    public MainMenuStateHandler(UserInMemoryStateCache userInMemoryStateCache, MessageCreator messageCreator,
        StartKeyboards startKeyboards, TimeSlotService timeSlotService, AppointmentService appointmentService) {
        this.userInMemoryStateCache = userInMemoryStateCache;
        this.messageCreator = messageCreator;
        this.startKeyboards = startKeyboards;
        this.timeSlotService = timeSlotService;
        this.appointmentService = appointmentService;
    }

    @Override
    public SendMessage handle(Update update) {
        long userId;
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            userId = message.getFrom().getId();
        } else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            userId = callbackQuery.getFrom().getId();
            String userQuery = callbackQuery.getData();

            switch (userQuery) {
                case "Select a date for the appointment"-> {
                    userInMemoryStateCache.setBotState(userId, BotUserState.DATE_SELECTION);
                    return timeSlotService.getAvailableDates(userId);
                }
                case "List my appointments" -> {
                    userInMemoryStateCache.setBotState(userId, BotUserState.MAIN_MENU);
                    return appointmentService.getUserAppointments(userId);
                }
                default -> {
                    userInMemoryStateCache.setBotState(userId, BotUserState.MAIN_MENU);
                    return messageCreator.createMessage(userId, "not implemented yet");
                }
            }
        } else {
            return null; // shouldn't shoot for now TODO
        }



        SendMessage reply = messageCreator.createMessage(userId, BotText.ERROR.getText());
        reply.setReplyMarkup(startKeyboards.getUserKeyboard());

        userInMemoryStateCache.setBotState(userId, BotUserState.MAIN_MENU);

        return reply;
    }

    @Override
    public BotState getBotStateHandlerName() {
        return BotUserState.MAIN_MENU;
    }
}
