package com.isv10k.onlineappointmentbot.bot.cache;

import com.isv10k.onlineappointmentbot.bot.states.BotState;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class UserInMemoryStateCache implements UserStateCache {

    private final Map<Long, BotState> usersBotStates = new ConcurrentHashMap<>();

    public void setBotState(long userId, BotState botState) {
        usersBotStates.put(userId, botState);
    }

    public Optional<BotState> getBotState(long userId) {
        return Optional.ofNullable(usersBotStates.get(userId));
    }
}
