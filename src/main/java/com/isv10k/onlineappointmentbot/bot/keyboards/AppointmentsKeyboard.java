package com.isv10k.onlineappointmentbot.bot.keyboards;

import com.isv10k.onlineappointmentbot.models.Appointment;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Component
public class AppointmentsKeyboard {

    public InlineKeyboardMarkup createDeleteAppointmentKeyboardOf(List<Appointment> appointments) {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (Appointment appointment : appointments) {
            List<InlineKeyboardButton> row = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(appointment.getAppointmentTime().toString());
            button.setCallbackData(appointment.getAppointmentId().toString());
            row.add(button);
            keyboard.add(row);
        }

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }

}
