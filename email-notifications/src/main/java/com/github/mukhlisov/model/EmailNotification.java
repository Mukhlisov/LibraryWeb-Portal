package com.github.mukhlisov.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailNotification {
    private String receiver;
    private String subject;
    private String body;

    @JsonCreator
    public EmailNotification(@JsonProperty("receiver") String receiver,
                             @JsonProperty("subject") String subject,
                             @JsonProperty("body") String body) {
        this.receiver = receiver;
        this.subject = subject;
        this.body = body;
    }
}
