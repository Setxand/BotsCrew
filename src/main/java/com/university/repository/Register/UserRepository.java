package com.university.repository.Register;

import com.university.entities.Register.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{
    public User findByRId(Long id);
}
