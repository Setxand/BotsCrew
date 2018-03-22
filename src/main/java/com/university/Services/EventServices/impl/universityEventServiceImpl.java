package com.university.Services.EventServices.impl;

import com.university.Services.EventServices.UniversityEventService;
import com.university.Services.messangerService.MessageSenderService;
import com.university.Services.repositoryServices.UserRepositoryService;
import com.university.Services.supportServices.SupportService;
import com.university.Services.universityService.UniService;
import com.university.entities.Register.User;
import com.university.enums.UserStatuses;
import com.university.models.messanger.Messaging;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class universityEventServiceImpl implements UniversityEventService {
    @Autowired
    private MessageSenderService messageSenderService;
    @Autowired
    private UserRepositoryService userRepositoryService;
    @Autowired
    private UniService uniService;
    @Autowired
    private SupportService supportService;

    private static final Logger logger = Logger.getLogger(universityEventServiceImpl.class);
    @Override
    public void headOfTheDepartment(Messaging messaging) {
        if (messaging.getPostback() != null) {
            messageSenderService.sendSimpleMessage("Enter name of department:",messaging.getSender().getId());
        } else {
            User user = userRepositoryService.findById(messaging.getSender().getId());

            switch (UserStatuses.valueOf(user.getUserStatus())) {
                case HEAD_START:
                    headStart(messaging);
                    break;
                default:
                    messageSenderService.errorMessage(messaging.getSender().getId());
                    break;
            }

        }
    }

    private void headStart(Messaging messaging) {
        try {
            uniService.headOfDepartment(messaging.getMessage().getText(),messaging);

        }
        catch (Exception ex){
            logger.warn(ex);
            messageSenderService.errorMessage(messaging.getSender().getId());
        }
        supportService.changeUserStatus(messaging.getSender().getId(),null);
        messageSenderService.sendUniActions(messaging);
    }
}
