package com.isv10k.onlineappointmentbot.bot.identities;

import com.isv10k.onlineappointmentbot.models.Role;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class ManagerUserIdentity implements UserIdentity {

    @Override
    public Role getRole() {
        return Role.MANAGER;
    }

    @Override
    public SendMessage handleUpdate(Update update) {
        return null;
    }
}
