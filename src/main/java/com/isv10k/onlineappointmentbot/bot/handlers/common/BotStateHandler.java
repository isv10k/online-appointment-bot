package com.isv10k.onlineappointmentbot.bot.handlers.common;

import com.isv10k.onlineappointmentbot.bot.handlers.specific.UserTypeStateMessageHandler;
import com.isv10k.onlineappointmentbot.bot.states.BotState;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class BotStateHandler {

    private final Map<BotState, UserTypeStateMessageHandler> botStateMessages = new HashMap<>();

    public BotStateHandler(List<UserTypeStateMessageHandler> messageHandlers) {
        messageHandlers.forEach(handler -> botStateMessages.put(handler.getBotStateHandlerName(), handler));
    }

    public SendMessage getMessageByBotState(BotState botState, Update update) {
        UserTypeStateMessageHandler handler = findMessageHandlerByBotState(botState);
        return handler.handle(update);
    }

    private UserTypeStateMessageHandler findMessageHandlerByBotState(BotState botState) {
        return botStateMessages.get(botState);
    }
}
