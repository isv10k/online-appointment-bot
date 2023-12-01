package com.isv10k.onlineappointmentbot.bot.controllers;

import com.isv10k.onlineappointmentbot.bot.identities.UserIdentity;
import com.isv10k.onlineappointmentbot.bot.services.IdentityService;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Controller
public class UpdateController {

    private final IdentityService identityService;

    public UpdateController(IdentityService identityService) {
        this.identityService = identityService;
    }

    public SendMessage handleUpdate(Update update) {
        UserIdentity userIdentity = identityService.identifyUserByUpdate(update);
        SendMessage response = userIdentity.handleUpdate(update);
        return response;
    }
}
