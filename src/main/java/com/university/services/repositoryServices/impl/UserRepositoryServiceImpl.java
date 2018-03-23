package com.university.services.repositoryServices.impl;

import com.university.services.repositoryServices.UserRepositoryService;
import com.university.entities.register.User;
import com.university.repository.register.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryServiceImpl implements UserRepositoryService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Long id) {
        return userRepository.findByRId(id);
    }

    @Override
    public User saveAndFlush(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }
}
