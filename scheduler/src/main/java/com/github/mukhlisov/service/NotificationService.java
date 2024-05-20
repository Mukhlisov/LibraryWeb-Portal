package com.github.mukhlisov.service;

import com.github.mukhlisov.Book;
import com.github.mukhlisov.Order;
import com.github.mukhlisov.OrderService;
import com.github.mukhlisov.TelegramNotificationBot;
import com.github.mukhlisov.User;
import com.github.mukhlisov.dto.ReminderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NotificationService {

    private static final String MESSAGE_TODAY = "сегодня";
    private static final String MESSAGE_TOMORROW = "завтра";

    private final EmailService emailService;
    private final OrderService orderService;
    private final TelegramNotificationBot telegramNotificationBot;

    public void sendNotifications() {
        LocalDate today = LocalDate.now();
        LocalDate next_day = today.plusDays(1);

        List<ReminderDto> usersToRemindToday = findUsersToRemind(today);
        List<ReminderDto> usersToRemindNextDay = findUsersToRemind(next_day);

        sendNotifications(usersToRemindToday, MESSAGE_TODAY);
        sendNotifications(usersToRemindNextDay, MESSAGE_TOMORROW);
    }

    private List<ReminderDto> findUsersToRemind(LocalDate date) {
        List<Order> orders = orderService.findByRentEndDate(date);
        List<ReminderDto> usersToRemind = new ArrayList<>();
        for (Order order : orders) {
            User user = order.getUser();
            Book book = order.getBook();
            ReminderDto remind = new ReminderDto(
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

    private void sendNotifications(List<ReminderDto> reminds, String content){
        for (ReminderDto remind : reminds) {
            String email = remind.getEmail();
            Long chatId = remind.getChatId();
            String subject = "Напоминание: возврат книги %s".formatted(remind.getReminderDate());
            String text = "%s %s,\n\nНапоминаем, что %s срок возврата книги: %s.\n"
                    .formatted(remind.getFirstName(), remind.getLastName(), content, remind.getBookName())
                    +"Пожалуйста, не забудьте вернуть её вовремя, чтобы избежать штрафов за просрочку.\n\n"
                    +"С наилучшими пожеланиями,\nLibHub";
            if (chatId == null){
                emailService.sendEmail(email, subject, text);
            } else{
                telegramNotificationBot.sendMessage(chatId, text);
            }
        }
    }
}
