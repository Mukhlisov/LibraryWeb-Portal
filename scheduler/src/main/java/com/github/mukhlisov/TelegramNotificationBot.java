package com.github.mukhlisov;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;


@Getter
@Setter
@Slf4j
@Component
public class TelegramNotificationBot extends TelegramLongPollingBot {

    @Value("${telegram-bot.name}")
    private String name;

    public TelegramNotificationBot(@Value("${telegram-bot.token}") String token) {
        super(token);
    }

    @Override
    public void onUpdateReceived(Update update) {
        long chatId = update.getMessage().getChatId();
        String messageText =
                "Ваш chat id: `%d` \nДля получения уведомлений введите его в форму на странице профиля на сайте"
                        .formatted(chatId);

        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(chatId);
        message.setText(messageText);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Failed to send message \"{}\"", messageText);
            log.error("Exception:%s".formatted(e.getMessage()));
        }
    }

    private void failedToSendMessageErrorOutput(String messageText){
        log.error("Failed to send notification message \"{}\"", messageText);
    }

    public void sendMessage(Long chatId, String messageText) {
        try {
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText(messageText);
            execute(message);
        }catch (TelegramApiRequestException e){
            log.error("Unexpected error:%s".formatted(e.getMessage()));
            failedToSendMessageErrorOutput(messageText);
            log.error("ChatID:%d".formatted(chatId));
        } catch (TelegramApiException e) {
            failedToSendMessageErrorOutput(messageText);
            log.error("Exception:%s".formatted(e.getMessage()));
        }
    }

    @Override
    public String getBotUsername() {
        return name;
    }
}
