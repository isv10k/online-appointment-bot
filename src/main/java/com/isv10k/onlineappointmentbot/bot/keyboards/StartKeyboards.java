package com.isv10k.onlineappointmentbot.bot.keyboards;

import com.isv10k.onlineappointmentbot.bot.buttons.AdminButtonsText;
import com.isv10k.onlineappointmentbot.bot.buttons.ManagerButtonsText;
import com.isv10k.onlineappointmentbot.bot.buttons.ServiceProviderButtonsText;
import com.isv10k.onlineappointmentbot.bot.buttons.UserButtonsText;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Component
public class StartKeyboards {


    public InlineKeyboardMarkup getUserKeyboard() {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(UserButtonsText.USER_SELECT_APPOINTMENT_DATE.getText());
        button.setCallbackData(UserButtonsText.USER_SELECT_APPOINTMENT_DATE.getText());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(UserButtonsText.USER_LIST_APPOINTMENTS.getText());
        button.setCallbackData(UserButtonsText.USER_LIST_APPOINTMENTS.getText());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(UserButtonsText.USER_CANCEL_APPOINTMENT.getText());
        button.setCallbackData(UserButtonsText.USER_CANCEL_APPOINTMENT.getText());
        row.add(button);
        keyboard.add(row);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }

    public InlineKeyboardMarkup getAdminKeyboard() {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(AdminButtonsText.ADMIN_MANAGER_LIST.getText());
        button.setCallbackData(AdminButtonsText.ADMIN_MANAGER_LIST.getText());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(AdminButtonsText.ADMIN_ADD_MANAGER.getText());
        button.setCallbackData(AdminButtonsText.ADMIN_ADD_MANAGER.getText());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(AdminButtonsText.ADMIN_DELETE_MANAGER.getText());
        button.setCallbackData(AdminButtonsText.ADMIN_DELETE_MANAGER.getText());
        row.add(button);
        keyboard.add(row);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }

    public InlineKeyboardMarkup getManagerKeyboard() {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(ManagerButtonsText.MANAGER_EDIT_SCHEDULE.getText());
        button.setCallbackData(ManagerButtonsText.MANAGER_EDIT_SCHEDULE.getText());
        row.add(button);
        keyboard.add(row);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }

    public InlineKeyboardMarkup getServiceProviderKeyboard() {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(ServiceProviderButtonsText.SP_MY_SCHEDULE.getText());
        button.setCallbackData(ServiceProviderButtonsText.SP_MY_SCHEDULE.getText());
        row.add(button);
        keyboard.add(row);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
}
