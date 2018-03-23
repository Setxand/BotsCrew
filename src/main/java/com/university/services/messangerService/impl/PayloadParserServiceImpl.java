package com.university.services.messangerService.impl;


import com.university.services.eventServices.UniversityEventService;
import com.university.services.messangerService.MessageSenderService;
import com.university.services.messangerService.PayloadParserService;
import com.university.services.repositoryServices.UserRepositoryService;
import com.university.services.supportServices.SupportService;
import com.university.entities.register.User;
import com.university.enums.payloads.PostBackPayloads;
import com.university.models.messanger.Messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.university.enums.UserStatuses.*;


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
    @Override
    public void parsePayload(Messaging messaging) {
        String payload = messaging.getPostback().getPayload();
        switch (PostBackPayloads.valueOf(payload)){
            case GET_STARTED_PAYLOAD:
                parseGetStartedPayload(messaging);
                break;
            case HEAD_OF_THE_DEPARTMENT_PAYLOAD:
                parseHeadOfЕheDepartment(messaging);
                break;
            case DEPARTMENT_STATISTIC_PAYLOAD:
                parseDepartmentStatistic(messaging);
                break;
            case AVG_SALARY_PAYLOAD:
                parseAvgPayload(messaging);
                break;
            case COUNT_OF_EMPLOYEE_PAYLOAD:
                parseCountOfEmployee(messaging);
                break;
            case GLOBAL_SEARCH_PAYLOAD:
                parseGlobalSearchPayload(messaging);
                break;
            default:
                messageSenderService.errorMessage(messaging.getSender().getId());
                messageSenderService.sendUniActions(messaging);
            break;
        }
    }

    private void parseGlobalSearchPayload(Messaging messaging) {
        supportService.changeUserStatus(messaging.getSender().getId(), GLOBAL_SEARCH_START.name());
        universityEventService.parseUniversityEvent(messaging);
    }

    private void parseCountOfEmployee(Messaging messaging) {
        supportService.changeUserStatus(messaging.getSender().getId(), COUNT_OF_EMPLOYEE_START.name());
        universityEventService.parseUniversityEvent(messaging);
    }

    private void parseAvgPayload(Messaging messaging) {
        supportService.changeUserStatus(messaging.getSender().getId(), AVG_SALARY_START.name());
        universityEventService.parseUniversityEvent(messaging);
    }

    private void parseDepartmentStatistic(Messaging messaging) {
        supportService.changeUserStatus(messaging.getSender().getId(), STATISTIC_START.name());
        universityEventService.parseUniversityEvent(messaging);
    }

    private void parseHeadOfЕheDepartment(Messaging messaging) {
        supportService.changeUserStatus(messaging.getSender().getId(),HEAD_START.name());
        universityEventService.parseUniversityEvent(messaging);
    }

    private void parseGetStartedPayload(Messaging messaging) {
        messageSenderService.sendSimpleMessage("Heey! im`m Artem`s Boyko bot!! He created me in order to show you his abilities)",messaging.getSender().getId());
        if(userRepositoryService.findById(messaging.getSender().getId())==null){
            User user = new User();
            user.setRId(messaging.getSender().getId());
            userRepositoryService.saveAndFlush(user);
        }
        messageSenderService.sendUniActions(messaging);
    }
}
