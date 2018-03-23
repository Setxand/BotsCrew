package com.university.services.messangerService;

import com.university.models.messanger.Button;
import com.university.models.messanger.Messaging;
import com.university.models.messanger.QuickReply;

import java.util.List;

public interface MessageSenderService {
    public void sendMessage(Messaging messaging);
    public void errorMessage(Long recipient);
    public void sendSimpleMessage(String text, Long recipient);

    public  void sendButtons(List<Button> buttons, String text, Long recipient);
    public void sendQuickReplies(List<QuickReply> quickReplies, String text, Long recipient);
    public void sendUniActions(Messaging messaging);
}
