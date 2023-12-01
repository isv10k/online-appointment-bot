package com.isv10k.onlineappointmentbot.bot.handlers.specific;

import com.isv10k.onlineappointmentbot.bot.states.BotState;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface UserTypeStateMessageHandler {

    SendMessage handle(Update update);

    BotState getBotStateHandlerName();
}
