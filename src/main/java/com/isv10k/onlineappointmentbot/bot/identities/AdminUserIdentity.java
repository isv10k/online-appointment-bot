package com.isv10k.onlineappointmentbot.bot.identities;

import com.isv10k.onlineappointmentbot.bot.cache.UserInMemoryStateCache;
import com.isv10k.onlineappointmentbot.bot.handlers.common.BotStateHandler;
import com.isv10k.onlineappointmentbot.bot.states.BotAdminState;
import com.isv10k.onlineappointmentbot.bot.states.BotState;
import com.isv10k.onlineappointmentbot.models.Role;
import java.util.Optional;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class AdminUserIdentity implements UserIdentity {

    private final UserInMemoryStateCache userInMemoryStateCache;
    private final BotStateHandler botStateHandler;

    public AdminUserIdentity(UserInMemoryStateCache userInMemoryStateCache, BotStateHandler botStateHandler) {
        this.userInMemoryStateCache = userInMemoryStateCache;
        this.botStateHandler = botStateHandler;
    }

    @Override
    public Role getRole() {
        return Role.ADMIN;
    }

    @Override
    public SendMessage handleUpdate(Update update) {
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

        Optional<BotState> optionalBotState = userInMemoryStateCache.getBotState(userId);
        BotState botState = BotAdminState.START;
        if (optionalBotState.isPresent()) {
            botState = optionalBotState.get();
        }

        return botStateHandler.getMessageByBotState(botState, update);

//        SendMessage message = new SendMessage();
//        message.setChatId(userId);
//        message.setText("I AM ADMIN");
//        return message;
    }

}
