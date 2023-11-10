package com.isv10k.onlineappointmentbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class OnlineAppointmentBotApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(OnlineAppointmentBotApplication.class, args);
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(ctx.getBean("onlineAppointmentBot", OnlineAppointmentBot.class));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
