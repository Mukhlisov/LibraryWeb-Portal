package com.github.mukhlisov.service;

import com.github.mukhlisov.model.EmailNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageReceiveService {

    private final EmailService emailService;

    @KafkaListener(topics = "email-notifications", groupId = "email-group-1")
    public void listenMessage(EmailNotification notification){
        emailService.sendEmail(notification.getReceiver(), notification.getSubject(), notification.getBody());
    }
}
