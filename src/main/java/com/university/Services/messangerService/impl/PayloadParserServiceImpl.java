package com.university.Services.messangerService.impl;


import com.university.Services.EventServices.UniversityEventService;
import com.university.Services.messangerService.MessageSenderService;
import com.university.Services.messangerService.PayloadParserService;
import com.university.Services.repositoryServices.UserRepositoryService;
import com.university.Services.supportServices.SupportService;
import com.university.Services.universityService.UniService;
import com.university.entities.Register.User;
import com.university.enums.payloads.PostBackPayloads;
import com.university.models.messanger.Button;
import com.university.models.messanger.Messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.university.enums.UserStatuses.HEAD_START;
import static com.university.enums.payloads.PostBackPayloads.HEAD_OF_THE_DEPARTMENT;


@Service
public class PayloadParserServiceImpl implements PayloadParserService {
    @Autowired
    private UserRepositoryService userRepositoryService;
    @Autowired
    private UniversityEventService universityEventService;
    @Autowired
    private SupportService supportService;
    @Autowired
    private MessageSenderService messageSenderService;
    @Autowired
    private UniService uniService;
    @Override
    public void parsePayload(Messaging messaging) {
        String payload = messaging.getPostback().getPayload();
        switch (PostBackPayloads.valueOf(payload)){
            case GET_STARTED_PAYLOAD:
                parseGetStartedPayload(messaging);
                break;
            case HEAD_OF_THE_DEPARTMENT:
                parseHeadOfheDepartment(messaging);
                break;
            default:
                messageSenderService.errorMessage(messaging.getSender().getId());
            break;
        }
    }

    private void parseHeadOfheDepartment(Messaging messaging) {
        supportService.changeUserStatus(messaging.getSender().getId(),HEAD_START.name());
        universityEventService.headOfTheDepartment(messaging);
    }

    private void parseGetStartedPayload(Messaging messaging) {
        if(userRepositoryService.findById(messaging.getSender().getId())==null){
            User user = new User();
            user.setRId(messaging.getSender().getId());
            userRepositoryService.saveAndFlush(user);
        }
        messageSenderService.sendUniActions(messaging);
    }
}
