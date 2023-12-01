package com.isv10k.onlineappointmentbot.bot.services;

import com.isv10k.onlineappointmentbot.bot.identities.UserIdentity;
import com.isv10k.onlineappointmentbot.models.Role;
import com.isv10k.onlineappointmentbot.models.User;
import com.isv10k.onlineappointmentbot.services.UserService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class IdentityService {

    private final UserService userService;

    private final Map<Role, UserIdentity> roleUserIdentityMap = new HashMap<>();

    public IdentityService(List<UserIdentity> identities, UserService userService) {
        this.userService = userService;
        identities.forEach(identity -> roleUserIdentityMap.put(identity.getRole(), identity));
    }

    private UserIdentity findIdentityByRole(Role role) {
        return roleUserIdentityMap.get(role);
    }

    public UserIdentity identifyUserByUpdate(Update update) {
        long userId;
        String userName;
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            userName = message.getFrom().getFirstName(); // getUserName can be null TODO
            userId = message.getFrom().getId();
        } else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            userName = callbackQuery.getFrom().getFirstName();
            userId = callbackQuery.getFrom().getId();
        } else {
            return null; // shouldn't shoot for now TODO
        }
        User user = userService.createIfNotExistAndGetUser(userId, userName);

        return findIdentityByRole(user.getRole());
    }
}
