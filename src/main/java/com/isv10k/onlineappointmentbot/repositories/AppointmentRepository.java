package com.isv10k.onlineappointmentbot.repositories;

import com.isv10k.onlineappointmentbot.models.Appointment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByUserId(Long id);
}
