package cc.sukazyo.untitled.telegram.api.extra;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.ChatMember;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.model.ChatMember.Status;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.GetChatMember;
import com.pengrad.telegrambot.response.BaseResponse;

import cc.sukazyo.untitled.telegram.api.event.EventRuntimeException;

public class ExtraAction {
	
	private final TelegramBot bot;
	
	public ExtraAction (TelegramBot bot) {
		this.bot = bot;
	}
	
	public static ExtraAction as (TelegramBot bot) {
		return new ExtraAction(bot);
	}
	
	public boolean isUserInGroup (User user, Chat chat) {
		return isUserInGroup(user.id(), chat.id());
	}
	
	public <T extends BaseRequest<T, R>, R extends BaseResponse> R exec (T req) {
		return exec(req, "");
	}
	
	public <T extends BaseRequest<T, R>, R extends BaseResponse> R exec (T req, String errorMessage) {
		final R resp = bot.execute(req);
		if (!resp.isOk()) throw new EventRuntimeException.ActionFailed(
				(errorMessage.equals("") ? String.valueOf(resp.errorCode()) : errorMessage),
				resp
		);
		return resp;
	}
	
	public boolean isUserInGroup (User user, Chat chat, Status permissionLevel) {
		return isUserInGroup(user.id(), chat.id(), permissionLevel);
	}
	
	public boolean isUserInGroup (long userId, long chatId) {
		return isUserInGroup(userId, chatId, Status.restricted);
	}
	
	public boolean isUserInGroup (long userId, long chatId, Status permissionLevel) {
		final ChatMember chatMember = exec(new GetChatMember(chatId, userId)).chatMember();
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
