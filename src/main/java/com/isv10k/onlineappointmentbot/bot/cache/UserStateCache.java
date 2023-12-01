package com.isv10k.onlineappointmentbot.bot.cache;

import com.isv10k.onlineappointmentbot.bot.states.BotState;
import java.util.Optional;

public interface UserStateCache {

    void setBotState(long userId, BotState botState);

    Optional<BotState> getBotState(long userId);
}
