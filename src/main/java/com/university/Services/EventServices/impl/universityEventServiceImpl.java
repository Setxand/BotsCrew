package com.university.Services.EventServices.impl;

import com.university.Services.EventServices.UniversityEventService;
import com.university.Services.messangerService.MessageSenderService;
import com.university.Services.repositoryServices.UserRepositoryService;
import com.university.Services.supportServices.SupportService;
import com.university.Services.universityService.UniService;
import com.university.entities.Register.User;
import com.university.enums.UserStatuses;
import com.university.enums.payloads.PostBackPayloads;
import com.university.models.messanger.Messaging;
import com.university.models.messanger.QuickReply;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static com.university.enums.payloads.QuickReplyPayloads.DEPARTMENTS_SEARCH_PAYLOAD;
import static com.university.enums.payloads.QuickReplyPayloads.LECTURERS_SEARCH_PAYLOAD;
import static com.university.enums.types.ButtonTypes.text;

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
    public void parseUniversityEvent(Messaging messaging) {
        try {
            if(messaging.getMessage()!=null){
                if(messaging.getMessage().getQuickReply()!=null) {
                    messageSenderService.sendSimpleMessage("Enter searching string: ", messaging.getSender().getId());
                    return;
                }
            }
            if (messaging.getPostback() != null) {
                switch (PostBackPayloads.valueOf(messaging.getPostback().getPayload())) {
                    case HEAD_OF_THE_DEPARTMENT_PAYLOAD:
                        messageSenderService.sendSimpleMessage("Enter name of department:", messaging.getSender().getId());
                        break;
                    case DEPARTMENT_STATISTIC_PAYLOAD:
                        messageSenderService.sendSimpleMessage("Enter name of department:", messaging.getSender().getId());
                        break;
                    case AVG_SALARY_PAYLOAD:
                        messageSenderService.sendSimpleMessage("Enter name of department:", messaging.getSender().getId());
                        break;
                    case COUNT_OF_EMPLOYEE_PAYLOAD:
                        messageSenderService.sendSimpleMessage("Enter name of department:", messaging.getSender().getId());
                        break;

                    case GLOBAL_SEARCH_PAYLOAD:
                        parseGlobalSearchEvent(messaging);
                        break;
                    default:
                        messageSenderService.errorMessage(messaging.getSender().getId());
                        messageSenderService.sendUniActions(messaging);
                        break;
                }
            } else {
                User user = userRepositoryService.findById(messaging.getSender().getId());

                switch (UserStatuses.valueOf(user.getUserStatus())) {
                    case HEAD_START:
                        headStart(messaging);
                        break;
                    case STATISTIC_START:
                        statStart(messaging);
                        break;
                    case AVG_SALARY_START:
                        avgSalaryStart(messaging);
                        break;
                    case COUNT_OF_EMPLOYEE_START:
                        countOfEmployeeStart(messaging);
                        break;
                    case LECTURERS_SEARCH:
                        lecturersSearch(messaging);
                        break;
                    case DEPARTMENTS_SEARCH:
                        depSearch(messaging);
                        break;
                    default:
                        messageSenderService.errorMessage(messaging.getSender().getId());
                        messageSenderService.sendUniActions(messaging);
                        break;
                }

            }
        } catch (Exception ex) {
            logger.warn(ex);
            messageSenderService.errorMessage(messaging.getSender().getId());
            messageSenderService.sendUniActions(messaging);
        }
    }

    private void depSearch(Messaging messaging) {
        uniService.departmentsSearch(messaging);
        supportService.changeUserStatus(messaging.getSender().getId(),null);
        messageSenderService.sendUniActions(messaging);
    }

    private void lecturersSearch(Messaging messaging) {
        uniService.lecturersSearch(messaging);
        supportService.changeUserStatus(messaging.getSender().getId(), null);
        messageSenderService.sendUniActions(messaging);
    }

    private void parseGlobalSearchEvent(Messaging messaging) {
        List<QuickReply> quickReplies = Arrays.asList(new QuickReply(text.name(),"Lecturers",LECTURERS_SEARCH_PAYLOAD.name())
        ,new QuickReply(text.name(),"Departments",DEPARTMENTS_SEARCH_PAYLOAD.name()));
        messageSenderService.sendQuickReplies(quickReplies,"What would you like to search?",messaging.getSender().getId());

    }

    private void countOfEmployeeStart(Messaging messaging) {
        uniService.countOfEmployee(messaging);
        supportService.changeUserStatus(messaging.getSender().getId(), null);
        messageSenderService.sendUniActions(messaging);
    }

    private void avgSalaryStart(Messaging messaging) {
        uniService.avgSalary(messaging);
        supportService.changeUserStatus(messaging.getSender().getId(), null);
        messageSenderService.sendUniActions(messaging);
    }

    private void statStart(Messaging messaging) {
        uniService.depStatistic(messaging);
        supportService.changeUserStatus(messaging.getSender().getId(), null);
        messageSenderService.sendUniActions(messaging);
    }


    private void headStart(Messaging messaging) {
        uniService.headOfDepartment(messaging);
        supportService.changeUserStatus(messaging.getSender().getId(), null);
        messageSenderService.sendUniActions(messaging);
    }


}
