package com.isv10k.onlineappointmentbot.controllers;

import com.isv10k.onlineappointmentbot.OnlineAppointmentBot;
import com.isv10k.onlineappointmentbot.services.UserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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

        if (update.hasMessage()) {
            distributeMessagesByType(update);
            // TODO
        }
    }

    private void distributeMessagesByType(Update update) {
        var msg = update.getMessage();
        var user = msg.getFrom();
        var id = user.getId();
        if (msg.isCommand()) {
            switch (msg.getText()) {
                case "/choose_appointment_date" -> availableDates(id);
                case "/show_my_appointments" -> appointmentsForUser(id);
            }
        }
    }

    private void availableDates(Long id) {
        SendMessage response = userService.getAvailableDates(id);
        onlineAppointmentBot.sendAnswerMessage(response);
    }

    private void appointmentsForUser(Long id) {
        SendMessage response = userService.getUserAppointments(id);
        onlineAppointmentBot.sendAnswerMessage(response);
    }
}
