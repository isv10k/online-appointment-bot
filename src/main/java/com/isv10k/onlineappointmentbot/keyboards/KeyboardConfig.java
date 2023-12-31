package com.isv10k.onlineappointmentbot.keyboards;

import com.isv10k.onlineappointmentbot.models.Appointment;
import com.isv10k.onlineappointmentbot.models.TimeSlot;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Component
public class KeyboardConfig {

    public InlineKeyboardMarkup createTimeSlotsKeyboardOf(List<TimeSlot> timeSlots) {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (TimeSlot timeSlot : timeSlots) {
            List<InlineKeyboardButton> row = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(timeSlot.getStartTime().toString());
            button.setCallbackData(timeSlot.getSlotId().toString());
            row.add(button);
            keyboard.add(row);
        }

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }

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
