package com.isv10k.onlineappointmentbot.repositories;

import com.isv10k.onlineappointmentbot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
