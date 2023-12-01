package com.isv10k.onlineappointmentbot.bot.buttons;

public enum AdminButtonsText {
    ADMIN_ADD_MANAGER("add manager"),
    ADMIN_DELETE_MANAGER("delete manager"),
    ADMIN_MANAGER_LIST("manager list");

    private final String text;

    AdminButtonsText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
