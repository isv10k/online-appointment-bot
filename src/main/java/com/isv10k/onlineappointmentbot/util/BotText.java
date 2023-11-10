package com.isv10k.onlineappointmentbot.util;

import java.util.HashMap;
import java.util.Map;

public enum BotText {
    CHOOSE_AVAILABLE("Choose one of the available time slots"),
    CHOOSE_TO_DELETE("Choose one of the appointments to delete"),
    APPOINTMENT_CONFIRM("Your date of an appointment"),
    APPOINTMENT_DELETE("You deleted appointment");

    public final String text;

    private static final Map<String, BotText> BY_TEXT = new HashMap<>();

    static {
        for (BotText t : values()) {
            BY_TEXT.put(t.text, t);
        }
    }

    private BotText(String text) {
        this.text = text;
    }

    public static BotText valueOfText(String text) {
        return BY_TEXT.get(text);
    }

}
