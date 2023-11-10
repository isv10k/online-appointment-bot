package com.isv10k.onlineappointmentbot.services;

import com.isv10k.onlineappointmentbot.keyboards.KeyboardConfig;
import com.isv10k.onlineappointmentbot.models.Appointment;
import com.isv10k.onlineappointmentbot.models.TimeSlot;
import com.isv10k.onlineappointmentbot.models.User;
import com.isv10k.onlineappointmentbot.repositories.AppointmentRepository;
import com.isv10k.onlineappointmentbot.repositories.TimeSlotRepository;
import com.isv10k.onlineappointmentbot.repositories.UserRepository;
import com.isv10k.onlineappointmentbot.util.BotText;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Service
public class UserService { // TODO SRP

    private final AppointmentRepository appointmentRepository;
    private final TimeSlotRepository timeSlotRepository;
    private final UserRepository userRepository;
    private final KeyboardConfig keyboardConfig;

    public UserService(AppointmentRepository appointmentRepository, TimeSlotRepository timeSlotRepository,
        UserRepository userRepository, KeyboardConfig keyboardConfig) {
        this.appointmentRepository = appointmentRepository;
        this.timeSlotRepository = timeSlotRepository;
        this.userRepository = userRepository;
        this.keyboardConfig = keyboardConfig;
    }


    public SendMessage getAvailableDates(Long id) {
        List<TimeSlot> timeSlots = timeSlotRepository.findTimeSlotByIsAvailableIsTrue();
        InlineKeyboardMarkup keyboardMarkup = keyboardConfig.createTimeSlotsKeyboardOf(timeSlots);

        return createResponse(id, BotText.CHOOSE_AVAILABLE.text, keyboardMarkup);
    }

    public SendMessage getUserAppointments(Long id) {
        List<Appointment> appointments = appointmentRepository.findAllByUserId(id);
//        InlineKeyboardMarkup keyboardMarkup = keyboardConfig.createKeyboardOf(appointments);
        StringBuilder sb = new StringBuilder();
        for (var appointment : appointments) {
            sb.append(appointment.getAppointmentTime());
            sb.append("\n");
        }

        return createResponse(id, sb.toString(), null); // TODO fix null
    }

    // TODO transactional?
    public SendMessage pickDate(Long slotId, Long userId) {
        Optional<TimeSlot> optionalTimeSlot = timeSlotRepository.findById(slotId);
        TimeSlot timeSlot = optionalTimeSlot.get(); // TODO refactor
        timeSlot.setAvailable(false);
        TimeSlot saved = timeSlotRepository.save(timeSlot);

        Optional<User> optionalUser = userRepository.findById(userId);
        User user;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            user = new User();
            user.setUserId(userId);
            // TODO
            user.setEmail("tmp@tmp.tmp");
            user.setUsername("tmp");
            userRepository.save(user);
        }
        Appointment appointment = new Appointment();
        appointment.setUser(user);
        appointment.setAppointmentTime(saved.getStartTime());
        appointment.setTimeSlot(saved);

        appointmentRepository.save(appointment);
        return createResponse(
            userId,
            BotText.APPOINTMENT_CONFIRM.text + " " + saved.getStartTime(),
            null);
    }

    private SendMessage createResponse(Long id, String text, InlineKeyboardMarkup keyboardMarkup) {
        SendMessage message = new SendMessage();
        message.setChatId(id);
        message.setText(text);
        if (keyboardMarkup != null) {
            message.setReplyMarkup(keyboardMarkup);
        }
        return message;
    }


    public SendMessage getListOfUserAppointmentsToDelete(Long id) {
        List<Appointment> appointments = appointmentRepository.findAllByUserId(id);
        InlineKeyboardMarkup keyboardMarkup = keyboardConfig.createDeleteAppointmentKeyboardOf(appointments);

        return createResponse(id, BotText.CHOOSE_TO_DELETE.text, keyboardMarkup);
    }

    public SendMessage pickAppointmentToDelete(Long id, Long userId) {
        Optional<Appointment> optionalAppointmentToDelete = appointmentRepository.findById(id);
        Appointment appointmentToDelete = optionalAppointmentToDelete.get();
        appointmentRepository.deleteById(id);

        Optional<TimeSlot> optionalTimeSlotToFree = timeSlotRepository.findById(appointmentToDelete.getTimeSlot().getSlotId());
        TimeSlot timeSlotToFree = optionalTimeSlotToFree.get();
        timeSlotToFree.setAvailable(true);
        timeSlotRepository.save(timeSlotToFree);

        return createResponse(userId, BotText.APPOINTMENT_DELETE.text + " " +appointmentToDelete.getAppointmentTime(), null);
    }
}
