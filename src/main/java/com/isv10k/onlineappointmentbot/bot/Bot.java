package com.isv10k.onlineappointmentbot.bot;

import com.isv10k.onlineappointmentbot.bot.controllers.UpdateController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class Bot extends TelegramLongPollingBot {

    private final UpdateController updateController;
//    private final UserInMemoryStateCache

    @Value("${bot.name}")
    private String botName;

    public Bot(@Value("${bot.token}") final String botToken, UpdateController updateController) {
        super(botToken);
        this.updateController = updateController;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public void onUpdateReceived(Update update) {

        SendMessage sendMessage = updateController.handleUpdate(update);
        sendReplyMessage(sendMessage);
    }

    private void sendReplyMessage(SendMessage message) {
        if (message != null) {
            try {
                execute(message);
            } catch (TelegramApiException e) {
                // TODO
            }
        }
    }
}
