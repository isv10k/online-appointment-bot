package com.isv10k.onlineappointmentbot.services;

import com.isv10k.onlineappointmentbot.bot.MessageCreator;
import com.isv10k.onlineappointmentbot.bot.buttons.BotText;
import com.isv10k.onlineappointmentbot.bot.keyboards.TimeSlotsKeyboard;
import com.isv10k.onlineappointmentbot.models.TimeSlot;
import com.isv10k.onlineappointmentbot.repositories.TimeSlotRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Service
public class TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;
    private final TimeSlotsKeyboard timeSlotsKeyboard;
    private final MessageCreator messageCreator;

    public TimeSlotService(TimeSlotRepository timeSlotRepository, TimeSlotsKeyboard timeSlotsKeyboard,
        MessageCreator messageCreator) {
        this.timeSlotRepository = timeSlotRepository;
        this.timeSlotsKeyboard = timeSlotsKeyboard;
        this.messageCreator = messageCreator;
    }

    public SendMessage getAvailableDates(Long id) {
        List<TimeSlot> timeSlots = timeSlotRepository.findTimeSlotByIsAvailableIsTrue();
        InlineKeyboardMarkup keyboardMarkup = timeSlotsKeyboard.createTimeSlotsKeyboardOf(timeSlots);
        SendMessage response = messageCreator.createMessage(id, BotText.CHOOSE_AVAILABLE.getText());
        response.setReplyMarkup(keyboardMarkup);
        return response;
    }
}
