package com.university.Services.messangerService.impl;


import com.university.Services.EventServices.UniversityEventService;
import com.university.Services.messangerService.MessageParserService;
import com.university.Services.messangerService.MessageSenderService;
import com.university.Services.repositoryServices.UserRepositoryService;
import com.university.Services.supportServices.SupportService;
import com.university.entities.Register.User;
import com.university.enums.MessageParserCommands;
import com.university.enums.UserStatuses;
import com.university.models.messanger.Messaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.university.enums.UserStatuses.HEAD_START;

@Service
public class MessageParserServiceImpl implements MessageParserService {
    @Autowired
    private MessageSenderService messageSenderService;
    @Autowired
    private SupportService supportService;
    @Autowired
    private UserRepositoryService userRepositoryService;
    @Autowired
    private UniversityEventService universityEventService;
    @Override
    public void parseMessage(Messaging messaging) {
        User user = userRepositoryService.findById(messaging.getSender().getId());


        String command = messaging.getMessage().getText().toUpperCase();


        if(user.getUserStatus()!=null){
            command = user.getUserStatus();
        }

        switch (MessageParserCommands.valueOf(command)){
            case HEY:
                sendHello(messaging);
                break;
            case HEAD_START:
                universityEventService.headOfTheDepartment(messaging);
                break;
            default:
                messageSenderService.errorMessage(messaging.getSender().getId());
                break;
        }
    }

    private void sendHello(Messaging messaging) {
        messageSenderService.sendSimpleMessage("Hey, what`s upp?",messaging.getSender().getId());
    }

}



