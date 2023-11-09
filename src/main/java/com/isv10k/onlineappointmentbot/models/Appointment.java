package com.isv10k.onlineappointmentbot.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "slot_id")
    private TimeSlot timeSlot;

    @Column(name = "appointment_time")
    private LocalDateTime appointmentTime;

//    @Column(name = "created_at")
//    private LocalDateTime createdAt;
//
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;


    public Appointment(Long id, User user, TimeSlot timeSlot, LocalDateTime appointmentTime) {
        this.id = id;
        this.user = user;
        this.timeSlot = timeSlot;
        this.appointmentTime = appointmentTime;
    }

    public Appointment() {
    }

    public Long getAppointmentId() {
        return id;
    }

    public void setAppointmentId(Long id) {
        this.id = Appointment.this.id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Appointment that = (Appointment) o;
        return Objects.equals(id, that.id) && Objects.equals(user, that.user)
            && Objects.equals(timeSlot, that.timeSlot) && Objects.equals(appointmentTime,
            that.appointmentTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, timeSlot, appointmentTime);
    }
}