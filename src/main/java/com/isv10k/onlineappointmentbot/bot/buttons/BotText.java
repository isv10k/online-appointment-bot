package com.isv10k.onlineappointmentbot.bot.buttons;

public enum BotText {

    USER_MESSAGE("Welcome!"),
    ADMIN_MESSAGE("admin actions"),
    MANAGER_MESSAGE("manager actions"),
    SERVICE_PROVIDER_MESSAGE("your actions"),
    CHOOSE_AVAILABLE("Select one of the available time slots"),
    CHOOSE_TO_CANCEL("Select one of the appointments to cancel"),
    APPOINTMENT_CONFIRM("The date for your appointment"),
    APPOINTMENT_CANCEL("The appointment has been canceled"),
    ERROR("Invalid message");

    private final String text;

    BotText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
