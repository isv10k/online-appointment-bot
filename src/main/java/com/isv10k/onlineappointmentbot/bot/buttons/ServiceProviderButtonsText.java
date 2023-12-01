package com.isv10k.onlineappointmentbot.bot.buttons;

public enum ServiceProviderButtonsText {

    SP_MY_SCHEDULE("My schedule");

    private final String text;

    ServiceProviderButtonsText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
