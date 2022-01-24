package cc.sukazyo.untitled.telegram.api.formatting;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.User;

public class TGToString {
	
	public static TGToStringFromChat as (Chat chat) {
		return new TGToStringFromChat(chat);
	}
	
	public static TGToStringFromUser as (User user) {
		return new TGToStringFromUser(user);
	}
	
}
