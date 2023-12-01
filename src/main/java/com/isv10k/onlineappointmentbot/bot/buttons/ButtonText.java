package com.isv10k.onlineappointmentbot.bot.buttons;

import java.util.HashMap;
import java.util.Map;

public enum ButtonText {

    USER_SELECT_APPOINTMENT_DATE("Select a date for the appointment"),
    USER_LIST_APPOINTMENTS("List my appointments"),
    USER_CANCEL_APPOINTMENT("Cancel appointment"),
    ADMIN_ADD_MANAGER("add manager"),
    ADMIN_DELETE_MANAGER("delete manager"),
    ADMIN_MANAGER_LIST("Select one of the appointments to cancel"),
    MANAGER_EDIT_SCHEDULE("Edit schedule"),
    SP_MY_SCHEDULE("My schedule");

    private static final Map<String, ButtonText> BY_TEXT = new HashMap<>();

    static {
        for (ButtonText t : values()) {
            BY_TEXT.put(t.text, t);
        }
    }

    public final String text;

    ButtonText(String text) {
        this.text = text;
    }

    public static ButtonText valueOfText(String text) {
        return BY_TEXT.get(text);
    }
}
