package com.telegram.PizzaBot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;


@Service
public class MessageService {

    private String responseText;
    private SendMessage message = new SendMessage();

    public SendMessage messageReceiver(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            var text = update.getMessage().getText();
            var chatId = update.getMessage().getChatId();
            var name = update.getMessage().getChat().getFirstName();


            switch (text) {
                case "/start" -> responseText = String.format("Привет, " + name);
                case "/stop" -> responseText = String.format("Пока, " + name);
                case "/register" -> register(update);
                default -> responseText = text;
            }

            message.setChatId(chatId);
            message.setText(responseText);
            return message;
        }
        else return null;
    }

    private void register(Update update) {

        responseText = "Регист?";
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineKeyboardButtons = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();

        var button = new InlineKeyboardButton();
        button.setText("Da");
        button.setCallbackData("Da_button");
        var button2 = new InlineKeyboardButton();
        button2.setText("NO");
        button2.setCallbackData("No_button");

        row1.add(button);
        row1.add(button2);
        inlineKeyboardButtons.add(row1);


        inlineKeyboardMarkup.setKeyboard(inlineKeyboardButtons);

        message.setReplyMarkup(inlineKeyboardMarkup);



    }
}
