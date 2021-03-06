package com.university.models.messanger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Messaging {
    @JsonProperty("messaging_type")
    private String messagingType;
    private Sender sender;
    private Recipient recipient;
    private Long timestamp;
    private Message message;
    private PostBack postback;
    private String tag;
    public Messaging() {
    }

    public Messaging(Sender sender, Recipient recipient) {
        this.messagingType = "RESPONSE";
        this.sender = sender;
        this.recipient = recipient;
    }

    public Messaging(Message message,Recipient recipient) {
        this.messagingType = "RESPONSE";
        this.message = message;
        this.recipient = recipient;

    }


}
