package Bots.MichalBot;

import org.telegram.telegrambots.api.objects.Update;

public class User {

	private String firstName;
	private String lastName;
	private String username;
	private long userId;
	private long chatId;
	private String message;
	private String callbackQueryData;

	public User(Update update) {

		if (update != null) {
			if (update.hasMessage()) {
				firstName = update.getMessage().getChat().getFirstName();
				lastName = update.getMessage().getChat().getLastName();
				username = update.getMessage().getChat().getUserName();
				userId = update.getMessage().getChat().getId();
				chatId = update.getMessage().getChatId();
				message = update.getMessage().getText();
			}
			if (update.hasCallbackQuery()) {
				callbackQueryData = update.getCallbackQuery().getData();
			}
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((callbackQueryData == null) ? 0 : callbackQueryData.hashCode());
		result = prime * result + (int) (chatId ^ (chatId >>> 32));
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null && this.equals(null))
			return true;
		else if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (callbackQueryData == null) {
			if (other.callbackQueryData != null)
				return false;
		} else if (!callbackQueryData.equals(other.callbackQueryData))
			return false;
		if (chatId != other.chatId)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (userId != other.userId)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserUsername() {
		return username;
	}

	public void setUserUsername(String userUsername) {
		this.username = userUsername;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getChatId() {
		return chatId;
	}

	public void setChatId(long chatId) {
		this.chatId = chatId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCallbackQueryData() {
		return callbackQueryData;
	}

	public void setCallbackQueryData(String callbackQueryData) {
		this.callbackQueryData = callbackQueryData;
	}

	@Override
	public String toString() {
		return "User[" + "firstName=" + firstName + ", lastName=" + lastName + ", userName=" + username + ", userId="
				+ userId + ", chatId=" + chatId + ", userMessage=" + message + ']';
	}

}
