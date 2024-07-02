package com.telegram.PizzaBot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class MessageService {

    public SendMessage messageReceiver(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            var text = update.getMessage().getText();
            var chatId = update.getMessage().getChatId();
            var name = update.getMessage().getChat().getFirstName();

            String responseText;
            switch (text) {
                case "/start" -> responseText = String.format("Привет, " + name);
                case "/stop" -> responseText = String.format("Пока, " + name);
                default -> responseText = "Чо это?";
            }

            var message =  new SendMessage();
            message.setChatId(chatId);
            message.setText(responseText);
            return message;
        }
        else return null;
    }
}
