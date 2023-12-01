package com.isv10k.onlineappointmentbot.services;

import com.isv10k.onlineappointmentbot.bot.MessageCreator;
import com.isv10k.onlineappointmentbot.models.Appointment;
import com.isv10k.onlineappointmentbot.repositories.AppointmentRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final MessageCreator messageCreator;

    public AppointmentService(AppointmentRepository appointmentRepository, MessageCreator messageCreator) {
        this.appointmentRepository = appointmentRepository;
        this.messageCreator = messageCreator;
    }

    public SendMessage getUserAppointments(Long id) {
        List<Appointment> appointments = appointmentRepository.findAllByUserId(id);
        StringBuilder sb = new StringBuilder();
        for (var appointment : appointments) {
            sb.append(appointment.getAppointmentTime());
            sb.append("\n");
        }

        return messageCreator.createMessage(id, sb.toString()); // TODO fix null
    }
}
