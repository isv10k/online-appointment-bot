package com.isv10k.onlineappointmentbot.bot.states;

public enum BotUserState  implements BotState {
    START,
    SELECT_APPOINTMENT_DATE,
    LIST_APPOINTMENTS,
    CANCEL_APPOINTMENT,
    MAIN_MENU,
    DATE_SELECTION
}
