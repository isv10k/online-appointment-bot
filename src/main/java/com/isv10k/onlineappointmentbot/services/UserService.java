package com.isv10k.onlineappointmentbot.services;

import com.isv10k.onlineappointmentbot.models.Appointment;
import com.isv10k.onlineappointmentbot.models.TimeSlot;
import com.isv10k.onlineappointmentbot.repositories.AppointmentRepository;
import com.isv10k.onlineappointmentbot.repositories.TimeSlotRepository;
import com.isv10k.onlineappointmentbot.repositories.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public class UserService {
    private final AppointmentRepository appointmentRepository;
    private final TimeSlotRepository timeSlotRepository;
    private final UserRepository userRepository;

    public UserService(AppointmentRepository appointmentRepository, TimeSlotRepository timeSlotRepository,
        UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.timeSlotRepository = timeSlotRepository;
        this.userRepository = userRepository;
    }


    public SendMessage getAvailableDates(Long id) {
        List<TimeSlot> timeSlots = timeSlotRepository.findTimeSlotByIsAvailableIsTrue();
        StringBuilder sb = new StringBuilder();
        for (var timeSlot : timeSlots) {
            sb.append(timeSlot.getStartTime());
            sb.append("\n");
        }

        return createResponse(id, sb.toString());
    }

    public SendMessage getUserAppointments(Long id) {
        List<Appointment> appointments = appointmentRepository.findAllByUserId(id);
        StringBuilder sb = new StringBuilder();
        for (var appointment : appointments) {
            sb.append(appointment.getAppointmentTime());
            sb.append("\n");
        }

        return createResponse(id, sb.toString());
    }

    private SendMessage createResponse(Long id, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(id);
        message.setText(text);
        return message;
    }
}
