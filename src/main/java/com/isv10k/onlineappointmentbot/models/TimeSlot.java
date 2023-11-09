package com.isv10k.onlineappointmentbot.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "time_slots")
public class TimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "slot_id")
    private Long id;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "is_available")
    private boolean isAvailable;

    public TimeSlot(Long id, LocalDateTime startTime, LocalDateTime endTime, boolean isAvailable) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isAvailable = isAvailable;
    }

    public TimeSlot() {
    }

    public Long getSlotId() {
        return id;
    }

    public void setSlotId(Long id) {
        this.id = TimeSlot.this.id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeSlot timeSlot = (TimeSlot) o;
        return isAvailable == timeSlot.isAvailable && Objects.equals(id, timeSlot.id)
            && Objects.equals(startTime, timeSlot.startTime) && Objects.equals(endTime,
            timeSlot.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime, endTime, isAvailable);
    }
}
