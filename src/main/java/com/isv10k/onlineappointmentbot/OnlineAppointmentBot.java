package com.isv10k.onlineappointmentbot;

import com.isv10k.onlineappointmentbot.controllers.UpdateController;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class OnlineAppointmentBot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botName;

    private final UpdateController updateController;

    public OnlineAppointmentBot(@Value("${bot.token}") final String botToken, UpdateController updateController) {
        super(botToken);
        this.updateController = updateController;
    }

    @PostConstruct
    public void init() {
        updateController.registerBot(this);
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public void onUpdateReceived(Update update) {
//         We check if the update has a message and the message has text
//        if (update.hasMessage() && update.getMessage().hasText()) {
//            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
//            message.setChatId(update.getMessage().getChatId().toString());
//            message.setText(update.getMessage().getText());
//
//            try {
//                execute(message); // Call method to send the message
//                System.out.println(message);
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
        updateController.processUpdate(update);
    }

    public void sendAnswerMessage(SendMessage message) {
        if (message != null) {
            try {
                execute(message);
            } catch (TelegramApiException e) {
                // TODO
            }
        }
    }
}
