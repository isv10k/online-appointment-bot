package com.isv10k.onlineappointmentbot.bot.buttons;

import java.util.HashMap;
import java.util.Map;

public enum CallbackText {

    SELECT_APPOINTMENT_DATE("Select a date for the appointment"),
    ADMIN_MESSAGE("admin actions"),
    MANAGER_MESSAGE("manager actions"),
    SERVICE_PROVIDER_MESSAGE("your actions"),
    CHOOSE_AVAILABLE("Select one of the available time slots"),
    CHOOSE_TO_CANCEL("Select one of the appointments to cancel"),
    APPOINTMENT_CONFIRM("The date for your appointment"),
    APPOINTMENT_CANCEL("The appointment has been canceled");

    private static final Map<String, CallbackText> BY_TEXT = new HashMap<>();

    static {
        for (CallbackText t : values()) {
            BY_TEXT.put(t.text, t);
        }
    }

    public final String text;

    CallbackText(String text) {
        this.text = text;
    }

    public static CallbackText valueOfText(String text) {
        return BY_TEXT.get(text);
    }
}
