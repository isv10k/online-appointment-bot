package com.isv10k.onlineappointmentbot.bot.buttons;

public enum UserButtonsText {

    USER_SELECT_APPOINTMENT_DATE("Select a date for the appointment"),
    USER_LIST_APPOINTMENTS("List my appointments"),
    USER_CANCEL_APPOINTMENT("Cancel appointment");


    private final String text;

    UserButtonsText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
