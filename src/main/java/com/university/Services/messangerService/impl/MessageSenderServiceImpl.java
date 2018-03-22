package com.university.Services.messangerService.impl;

import com.university.Services.messangerService.MessageSenderService;
import com.university.models.messanger.*;
import com.university.models.messanger.Requests.RequestByMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.university.enums.payloads.PostBackPayloads.HEAD_OF_THE_DEPARTMENT;


@Service
public class MessageSenderServiceImpl implements MessageSenderService {



    @Value("${page.access.token}")
    private String PAGE_ACCESS_TOKEN;

    @Value("${send.api.uri}")
    private String FACEBOOK_SEND_URL;

    @Value("${facebook.user.data.url}")
    private String USER_DATA_URL;

    @Value("${facebook.user.data.uer.fields}")
    private String DATA_FIELDS;

    @Value("${server.url}")
    private String SERVER_URL;

    private static final Logger logger = Logger.getLogger(MessageSenderServiceImpl.class);


    @Override
    public void errorMessage(Long recipient) {
        Messaging messaging = new Messaging(new Message("Error"), new Recipient(recipient));
        sendMessage(messaging);
    }

    @Override
    public void sendSimpleMessage(String text, Long recipient) {
        Message message = new Message(text);
        Messaging messaging = new Messaging(message, new Recipient(recipient));

        sendMessage(messaging);
    }











    @Override
    public void sendButtons(List<Button> buttons, String text, Long recipient) {
        Attachment attachment = new Attachment();
        attachment.setType("template");
        Payload payload = new Payload();
        payload.setTemplateType("button");
        payload.setButtons(buttons);
        payload.setText(text);
        attachment.setPayload(payload);
        Message message = new Message();
        message.setAttachment(attachment);
        sendMessage(new Messaging(message, new Recipient(recipient)));
    }

    @Override
    public void sendQuickReplies(List<QuickReply> quickReplies, String text, Long recipient) {
        Message message = new Message();
        message.setQuickReplies(quickReplies);
        message.setText(text);
        sendMessage(new Messaging(message, new Recipient(recipient)));
    }

    @Override
    public void sendUniActions(Messaging messaging) {
        List<Button> buttons = new ArrayList<>();
        Button button = new Button("postback","head of department",HEAD_OF_THE_DEPARTMENT.name());
        buttons.add(button);
        sendButtons(buttons,"Hey, it`s your university!!!",messaging.getSender().getId());
    }


    @Override
    public @ResponseBody
    void sendMessage(Messaging messaging) {
        ResponseEntity<?> response = new RestTemplate().postForEntity(FACEBOOK_SEND_URL + PAGE_ACCESS_TOKEN, messaging, RequestByMessage.class);
        logger.info("Message has been sent... Message info: " + response.getBody());
    }


}
