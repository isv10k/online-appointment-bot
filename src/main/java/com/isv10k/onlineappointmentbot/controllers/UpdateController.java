package com.isv10k.onlineappointmentbot.controllers;

import com.isv10k.onlineappointmentbot.OnlineAppointmentBot;
import com.isv10k.onlineappointmentbot.services.UserService;
import com.isv10k.onlineappointmentbot.util.BotText;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class UpdateController {

    private final UserService userService;
    private OnlineAppointmentBot onlineAppointmentBot;

    public UpdateController(UserService userService) {
        this.userService = userService;
    }

    public void registerBot(OnlineAppointmentBot onlineAppointmentBot) {
        this.onlineAppointmentBot = onlineAppointmentBot;
    }

    public void processUpdate(Update update) {
        if (update == null) {
            // TODO
            return;
        }

//        if (update.hasMessage()) {
        distributeMessagesByType(update);
        // TODO
//        }
    }

    private void distributeMessagesByType(Update update) {

        if (update.hasMessage() && update.getMessage().isCommand()) {
            var msg = update.getMessage();
            var user = msg.getFrom();
            var id = user.getId();
            SendMessage response = switch (msg.getText()) {
                case "/choose_appointment_date" -> availableDates(id);
                case "/show_my_appointments" -> appointmentsForUser(id);
                case "/delete_appointment" -> listOfUserAppointmentsToDelete(id);
                default -> null; // TODO fix null
            };
            onlineAppointmentBot.sendAnswerMessage(response);
        }
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            SendMessage response = handleCallBackQuery(callbackQuery);
            Long dateId = Long.valueOf(callbackQuery.getData());
//            answerCallbackQuery(callbackQuery.getId(), "selected: " + data);
            // TODO
//            SendMessage response = userService.pickDate(dateId, callbackQuery.getFrom().getId());
//            SendMessage message = new SendMessage();
//            message.setChatId(callbackQuery.getFrom().getId());
//            message.setText(data);
            onlineAppointmentBot.sendAnswerMessage(response);
        }
    }

    private SendMessage handleCallBackQuery(CallbackQuery callbackQuery) {
        BotText botText = BotText.valueOfText(callbackQuery.getMessage().getText());
        Long id = Long.valueOf(callbackQuery.getData());
        return switch (botText) {
            case CHOOSE_AVAILABLE -> userService.pickDate(id, callbackQuery.getFrom().getId());
            case CHOOSE_TO_DELETE -> userService.pickAppointmentToDelete(id, callbackQuery.getFrom().getId());
            default -> null; // TODO fix
        };
    }

    private SendMessage listOfUserAppointmentsToDelete(Long id) {
        return userService.getListOfUserAppointmentsToDelete(id);
    }

    private SendMessage availableDates(Long id) {
        return userService.getAvailableDates(id);
//        onlineAppointmentBot.sendAnswerMessage(response);
    }

    private SendMessage appointmentsForUser(Long id) {
        return userService.getUserAppointments(id);
//        onlineAppointmentBot.sendAnswerMessage(response);
    }
}
