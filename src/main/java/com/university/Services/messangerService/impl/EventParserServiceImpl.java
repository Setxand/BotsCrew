package com.university.Services.messangerService.impl;


import com.university.Services.messangerService.*;
import com.university.Services.repositoryServices.UserRepositoryService;
import com.university.Services.supportServices.SupportService;
import com.university.models.messanger.Entry;
import com.university.models.messanger.Event;
import com.university.models.messanger.Messaging;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EventParserServiceImpl implements EventParserService {
    @Autowired
    private MessageSenderService messageSenderService;
    @Autowired
    private MessageParserService messageParserService;
    @Autowired
    private SupportService supportService;
    @Autowired
    private UserRepositoryService userRepositoryService;
    @Autowired
    private PayloadParserService payloadParserService;


    private static final Logger logger = Logger.getLogger(EventParserServiceImpl.class);

    @Override
    public boolean parseEvent(Event event) {
        for (Entry e : event.getEntry()) {


            for (Messaging messaging : e.getMessaging()) {
                try {
                    if (messaging.getPostback() != null) {

                        payloadParserService.parsePayload(messaging);
                        return true;
                    } else if (messaging.getMessage() != null) {
                        messageParserService.parseMessage(messaging);

                        return true;


                    }
                } catch (Exception ex) {


                    ex.printStackTrace();
                    logger.warn(ex);
                    if(userRepositoryService.findById(messaging.getSender().getId() )!=null) {
                        supportService.changeUserStatus(messaging.getSender().getId(), null);
                        messageSenderService.sendUniActions(messaging);
                    }
                    return true;
                }
            }
        }
        return false;

    }
}
