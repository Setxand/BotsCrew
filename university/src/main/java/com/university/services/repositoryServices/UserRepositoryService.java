package com.university.services.repositoryServices;

import com.university.entities.register.User;

public interface UserRepositoryService {
    public User findById(Long id);
    public User saveAndFlush(User user);
    public void delete(User user);
}
