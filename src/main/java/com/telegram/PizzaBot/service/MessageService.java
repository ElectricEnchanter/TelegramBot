package com.telegram.PizzaBot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
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

            message.setChatId(chatId);
            message.setText(text);

            switch (text) {
                case "/start" -> order(update) ;
                case "/stop" -> responseText = String.format("До связи.");
                default -> responseText = String.format("Ты сказал: " + text);
            }

            message.setChatId(chatId);
            message.setText(responseText);
            return message;

        }
        if (update.hasCallbackQuery()) {
            String callbackQuery = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            if(callbackQuery.equals("PIZZA")) {
                pizza();
                return message;
            } else if (callbackQuery.equals("DRINKS")) {
                go();
                return message;
            } else if (callbackQuery.equals("PIPI")) {
                String text = "Там на параше 4 штуки валяеца - все твои.";
                message.setChatId(chatId);
                message.setText(text);
                message.setReplyMarkup(null);

            } else if (callbackQuery.equals("POPA")) {
                String text = "Ну ты и говноед.";
                message.setChatId(chatId);
                message.setText(text);
                message.setReplyMarkup(null);
            } else if (callbackQuery.equals("GO") || callbackQuery.equals("DRINK")) {
                String text = "До связи.";
                message.setChatId(chatId);
                message.setText(text);
                message.setReplyMarkup(null);
            }
        }
        return message;
    }

    private void go() {
        responseText = "Папей гавна";

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineKeyboardButtons = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();

        var button = new InlineKeyboardButton();
        button.setText("Папить");
        button.setCallbackData("DRINK");
        var button2 = new InlineKeyboardButton();
        button2.setText("Пойти нахуй");
        button2.setCallbackData("GO");

        row1.add(button);
        row1.add(button2);
        inlineKeyboardButtons.add(row1);

        inlineKeyboardMarkup.setKeyboard(inlineKeyboardButtons);

        message.setReplyMarkup(inlineKeyboardMarkup);
        message.setText(responseText);

    }

    private void pizza() {
        responseText = "У нас есть 2 стула:";

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineKeyboardButtons = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();

        var button = new InlineKeyboardButton();
        button.setText("Пипироне");
        button.setCallbackData("PIPI");
        var button2 = new InlineKeyboardButton();
        button2.setText("Супер попа");
        button2.setCallbackData("POPA");

        row1.add(button);
        row1.add(button2);
        inlineKeyboardButtons.add(row1);

        inlineKeyboardMarkup.setKeyboard(inlineKeyboardButtons);

        message.setReplyMarkup(inlineKeyboardMarkup);
        message.setText(responseText);

    }

    private void order(Update update) {
        var name = update.getMessage().getChat().getFirstName();
        responseText = String.format("Здарова, " + name + "\nЧо хочишь заказать?");

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineKeyboardButtons = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();

        var button = new InlineKeyboardButton();
        button.setText("Пицца");
        button.setCallbackData("PIZZA");
        var button2 = new InlineKeyboardButton();
        button2.setText("Напитки");
        button2.setCallbackData("DRINKS");

        row1.add(button);
        row1.add(button2);
        inlineKeyboardButtons.add(row1);


        inlineKeyboardMarkup.setKeyboard(inlineKeyboardButtons);

        message.setReplyMarkup(inlineKeyboardMarkup);


    }
}
