package com.isv10k.onlineappointmentbot.bot.handlers.specific.admin;

import com.isv10k.onlineappointmentbot.bot.MessageCreator;
import com.isv10k.onlineappointmentbot.bot.buttons.BotText;
import com.isv10k.onlineappointmentbot.bot.cache.UserInMemoryStateCache;
import com.isv10k.onlineappointmentbot.bot.handlers.specific.UserTypeStateMessageHandler;
import com.isv10k.onlineappointmentbot.bot.keyboards.StartKeyboards;
import com.isv10k.onlineappointmentbot.bot.states.BotAdminState;
import com.isv10k.onlineappointmentbot.bot.states.BotState;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class AdminStartStateHandler implements UserTypeStateMessageHandler {

    private final MessageCreator messageCreator;
    private final StartKeyboards startKeyboards;

    private final UserInMemoryStateCache userInMemoryStateCache;

    public AdminStartStateHandler(MessageCreator messageCreator, StartKeyboards startKeyboards,
        UserInMemoryStateCache userInMemoryStateCache) {
        this.messageCreator = messageCreator;
        this.startKeyboards = startKeyboards;
        this.userInMemoryStateCache = userInMemoryStateCache;
    }

    @Override
    public SendMessage handle(Update update) {
        long userId;
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            userId = message.getFrom().getId();
        } else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            userId = callbackQuery.getFrom().getId();
        } else {
            return null; // shouldn't shoot for now TODO
        }

        SendMessage reply = messageCreator.createMessage(userId, BotText.ADMIN_MESSAGE.getText());
        reply.setReplyMarkup(startKeyboards.getAdminKeyboard());

        userInMemoryStateCache.setBotState(userId, BotAdminState.MAIN_MENU);

        return reply;
    }

    @Override
    public BotState getBotStateHandlerName() {
        return BotAdminState.START;
    }
}
