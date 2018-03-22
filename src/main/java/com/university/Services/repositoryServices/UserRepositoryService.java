package com.university.Services.repositoryServices;

import com.university.entities.Register.User;

public interface UserRepositoryService {
    public User findById(Long id);
    public User saveAndFlush(User user);
    public void delete(User user);
}
