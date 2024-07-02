package com.telegram.PizzaBot.service;

import com.telegram.PizzaBot.config.properties.BotProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
@RequiredArgsConstructor
public class PizzaBot extends TelegramLongPollingBot {

	private final BotProperties botProperties;
	private final MessageService messageService;

	@Override
	public void onUpdateReceived(Update update){
		try {
			SendMessage sendMessage = messageService.messageReceiver(update);
			execute(sendMessage);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getBotUsername() {
		return botProperties.name();
	}

	@Override
	public String getBotToken() {
		return botProperties.token();
	}
}
