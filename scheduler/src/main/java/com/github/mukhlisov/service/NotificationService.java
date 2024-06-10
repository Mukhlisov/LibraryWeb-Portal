package com.github.mukhlisov.service;

import com.github.mukhlisov.Book;
import com.github.mukhlisov.Order;
import com.github.mukhlisov.OrderService;
import com.github.mukhlisov.model.EmailNotification;
import com.github.mukhlisov.model.TelegramNotification;
import com.github.mukhlisov.User;
import com.github.mukhlisov.model.Reminder;
import com.github.mukhlisov.model.enums.Days;
import com.github.mukhlisov.utils.TemplateEngine;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NotificationService {

    private final OrderService orderService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendNotifications() {
        LocalDate today = LocalDate.now();
        LocalDate next_day = today.plusDays(1);

        List<Reminder> usersToRemindToday = findUsersToRemind(today);
        List<Reminder> usersToRemindNextDay = findUsersToRemind(next_day);

        sendNotifications(usersToRemindToday, Days.TODAY.getTitle());
        sendNotifications(usersToRemindNextDay, Days.TOMORROW.getTitle());
    }

    private List<Reminder> findUsersToRemind(LocalDate date) {
        List<Order> orders = orderService.findByRentEndDate(date);
        List<Reminder> usersToRemind = new ArrayList<>();

        for (Order order : orders) {
            User user = order.getUser();
            Book book = order.getBook();

            Reminder remind = new Reminder(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getChatId(),
                    user.getEmail(),
                    book.getTitle(),
                    order.convertToNormalDate(order.getRent_end_date())
            );

            if (remind.getEmail() != null){
                usersToRemind.add(remind);
            }
        }
        return usersToRemind;
    }

    private void sendNotifications(List<Reminder> reminds, String title){
        for (Reminder remind : reminds) {
            String email = remind.getEmail();
            Long chatId = remind.getChatId();

            if (chatId != null || email != null){
                String subject = TemplateEngine.createSubject(remind.getReminderDate());
                String text = TemplateEngine
                        .createBody(remind.getFirstName(), remind.getLastName(), title, remind.getBookName());

                if (chatId == null){
                    kafkaTemplate.send("email-notifications", new EmailNotification(email, subject, text));
                } else{
                    kafkaTemplate.send("telegram-notifications", new TelegramNotification(chatId, text));
                }
            }
        }
    }
}
