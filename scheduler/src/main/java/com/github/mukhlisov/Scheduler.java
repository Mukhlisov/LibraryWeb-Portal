package com.github.mukhlisov;

import com.github.mukhlisov.service.NotificationService;
import com.github.mukhlisov.service.OrderCleanerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class Scheduler {

    private final NotificationService notificationService;
    private final OrderCleanerService orderCleanerService;

    @Scheduled(cron = "0 0 8 * * ?")
    public void doRoutine(){
        notificationService.sendNotifications();
        orderCleanerService.deleteExpiredOrders();
    }
}
