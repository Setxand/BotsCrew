package com.university.services.messangerService.impl;


import com.university.services.eventServices.UniversityEventService;
import com.university.services.messangerService.MessageParserService;
import com.university.services.messangerService.MessageSenderService;
import com.university.services.messangerService.QuickReplyParserService;
import com.university.services.repositoryServices.UserRepositoryService;
import com.university.entities.register.User;
import com.university.enums.MessageParserCommands;
import com.university.models.messanger.Messaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageParserServiceImpl implements MessageParserService {
    @Autowired
    private MessageSenderService messageSenderService;
    @Autowired
    private UserRepositoryService userRepositoryService;
    @Autowired
    private UniversityEventService universityEventService;
    @Autowired
    private QuickReplyParserService quickReplyParserService;

    @Override
    public void parseMessage(Messaging messaging) {

        if (messaging.getMessage().getQuickReply() != null) {
            quickReplyParserService.parseQuickReply(messaging);
            return;
        }

        User user = userRepositoryService.findById(messaging.getSender().getId());
        String command = messaging.getMessage().getText().toUpperCase();

        if (user.getUserStatus() != null) {
            command = user.getUserStatus();
        }

        switch (MessageParserCommands.valueOf(command)) {
            case HEY:
                sendHello(messaging);
                break;
            case HEAD_START:
                universityEventService.parseUniversityEvent(messaging);
                break;
            case STATISTIC_START:
                universityEventService.parseUniversityEvent(messaging);
                break;
            case AVG_SALARY_START:
                universityEventService.parseUniversityEvent(messaging);
                break;
            case COUNT_OF_EMPLOYEE_START:
                universityEventService.parseUniversityEvent(messaging);
                break;
            case DEPARTMENTS_SEARCH:
                universityEventService.parseUniversityEvent(messaging);
                break;
            case LECTURERS_SEARCH:
                universityEventService.parseUniversityEvent(messaging);
                break;

            default:
                messageSenderService.errorMessage(messaging.getSender().getId());
                messageSenderService.sendUniActions(messaging);
                break;
        }
    }

    private void sendHello(Messaging messaging) {
        messageSenderService.sendSimpleMessage("Hey, what`s upp?", messaging.getSender().getId());
    }

}



