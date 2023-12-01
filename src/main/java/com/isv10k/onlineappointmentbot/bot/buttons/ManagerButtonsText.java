package com.isv10k.onlineappointmentbot.bot.buttons;

public enum ManagerButtonsText {
    MANAGER_EDIT_SCHEDULE("Edit schedule");

    private final String text;

    ManagerButtonsText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
