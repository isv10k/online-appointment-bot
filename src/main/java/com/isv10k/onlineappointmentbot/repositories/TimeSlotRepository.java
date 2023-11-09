package com.isv10k.onlineappointmentbot.repositories;

import com.isv10k.onlineappointmentbot.models.TimeSlot;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {

    List<TimeSlot> findTimeSlotByIsAvailableIsTrue();
}
