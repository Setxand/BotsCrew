package com.university.repository.register;

import com.university.entities.register.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{
    public User findByRId(Long id);
}
