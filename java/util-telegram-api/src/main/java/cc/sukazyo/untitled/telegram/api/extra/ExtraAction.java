package cc.sukazyo.untitled.telegram.api.extra;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.ChatMember;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.model.ChatMember.Status;
import com.pengrad.telegrambot.request.GetChatMember;

public class ExtraAction {
	
	private final TelegramBot bot;
	
	public ExtraAction (TelegramBot bot) {
		this.bot = bot;
	}
	
	public static ExtraAction as (TelegramBot bot) {
		return new ExtraAction(bot);
	}
	
	public boolean isUserInGroup (User user, Chat chat) {
		return isUserInGroup(user, chat, Status.restricted);
	}
	
	public boolean isUserInGroup (User user, Chat chat, Status permissionLevel) {
		final ChatMember chatMember = bot.execute(new GetChatMember(chat.id(), user.id())).chatMember();
		return
				chatMember != null &&
				UserPermissionLevel.as(chatMember.status()).hasPermission(UserPermissionLevel.as(permissionLevel));
	}
	
}

enum UserPermissionLevel {
	
	CREATOR(3),
	ADMINISTRATOR(2),
	MEMBER(1),
	RESTRICTED(0),
	LEFT(-1),
	KICKED(-2);
	
	final int permissionLevel;
	
	UserPermissionLevel (int permissionLevel) {
		this.permissionLevel = permissionLevel;
	}
	
	static UserPermissionLevel as (Status status) {
		switch (status) {
			case creator:
				return CREATOR;
			case administrator:
				return ADMINISTRATOR;
			case member:
				return MEMBER;
			case restricted:
				return RESTRICTED;
			case left:
				return LEFT;
			case kicked:
				return KICKED;
		}
		throw new IllegalArgumentException();
	}
	
	boolean hasPermission (UserPermissionLevel required) {
		return this.permissionLevel >= required.permissionLevel;
	}
	
}
