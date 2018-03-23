package com.university.Services.messangerService.impl;

import com.university.Services.EventServices.UniversityEventService;
import com.university.Services.messangerService.MessageSenderService;
import com.university.Services.messangerService.QuickReplyParserService;
import com.university.Services.supportServices.SupportService;
import com.university.enums.payloads.QuickReplyPayloads;
import com.university.models.messanger.Messaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.university.enums.UserStatuses.DEPARTMENTS_SEARCH;
import static com.university.enums.UserStatuses.LECTURERS_SEARCH;
@Service
public class QuickReplyParserServiceImpl implements QuickReplyParserService {
    @Autowired
    private SupportService supportService;
    @Autowired
    private MessageSenderService messageSenderService;
    @Autowired
    private UniversityEventService universityEventService;
    @Override
    public void parseQuickReply(Messaging messaging) {
        String payload = messaging.getMessage().getQuickReply().getPayload();
        switch (QuickReplyPayloads.valueOf(payload)){
            case LECTURERS_SEARCH_PAYLOAD:
                parseLecturersSearckPayload(messaging);
                break;
            case DEPARTMENTS_SEARCH_PAYLOAD:
                parseDepartmentsSearchPayload(messaging);
                break;
            default:
                messageSenderService.errorMessage(messaging.getSender().getId());
                messageSenderService.sendUniActions(messaging);
        }
    }

    private void parseDepartmentsSearchPayload(Messaging messaging) {
        supportService.changeUserStatus(messaging.getSender().getId(),DEPARTMENTS_SEARCH.name());
        universityEventService.parseUniversityEvent(messaging);
    }

    private void parseLecturersSearckPayload(Messaging messaging) {
        supportService.changeUserStatus(messaging.getSender().getId(),LECTURERS_SEARCH.name());
        universityEventService.parseUniversityEvent(messaging);
    }
}
