package Bots.MichalBot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class MoodaleBot extends TelegramLongPollingBot {

	private boolean isStart = false;
	private Map<Long, User> users = new HashMap<>();

	public String getBotUsername() {
		// TODO Auto-generated method stub
		return "moodale_bot";
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return "561853129:AAECvyNhqyqFWBzWDlJpINjQCEB6AN-m7ps";
	}

	private User getUser(Long userId, Update update) {
		User user = users.get(userId);
		if (user == null) {
			// user does not exist
			user = new User(update);
			users.put(user.getUserId(), user);
		}
		else if(update.hasCallbackQuery()){
			//update additional parameters for callback
			user.setCallbackQueryData(update.getCallbackQuery().getData());
		}

		return user;
	}

	@Override
	public void onUpdateReceived(Update update) {
		if (users == null) {
			users = new HashMap<>();
		}

		SendMessage message = new SendMessage();

		if (update.hasMessage() && update.getMessage().hasText()) {
			User user = getUser(update.getMessage().getChat().getId(), update);

			switch (update.getMessage().getText()) {
			case "/start":

				start(message, user);
				break;
			case "/help":

				help(message, user);
				break;

			default:

				String botAnswer = "Do you need HELP? /help";
				message.setChatId(user.getChatId()).setText(botAnswer);

				MoodaleBot.this.log(user, botAnswer);

				break;
			}
		}

		else if (update.hasCallbackQuery()) {

			User user = getUser(Long.valueOf(update.getCallbackQuery().getFrom().getId()), update);

			String botAnswer = user.getCallbackQueryData(); // default

			switch (update.getCallbackQuery().getData()) {

			case "sad":

				System.out.println("sad");
				botAnswer = "Ho, I'm sorry. What's bothering you?";
				break;

			case "happy":
				System.out.println("happy");
				botAnswer = "Good for you! Do you want to share?....";
				InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
				List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
				List<InlineKeyboardButton> rowInline = new ArrayList<>();
				rowInline.add(new InlineKeyboardButton().setText("Yes").setCallbackData("share"));
				rowInline.add(new InlineKeyboardButton().setText("No").setCallbackData("notShare"));
				// Set the keyboard to the markup
				rowsInline.add(rowInline);
				// Add it to the message
				markupInline.setKeyboard(rowsInline);
				message.setReplyMarkup(markupInline);

				break;

			case "share":
				System.out.println("share");
				botAnswer = "I AM JUST A BOT !!! \nOpen facebook or something.... ";
				break;

			case "notShare":
				System.out.println("notShare");
				botAnswer = "OK! It's just me and you . . . \n:]";
				break;
			default:
				// throw exception
			}

			message // Create a message object object
					.setChatId(user.getChatId()).setText(botAnswer);

			MoodaleBot.this.log(user, botAnswer);

		}
		try {
			execute(message); // Sending our message object to user
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}

	}

	private void start(SendMessage message, User user) {

		String botAnswer = user.getMessage(); // default

		if (isStart == false) {

			isStart = true;
		}
		botAnswer = "How do you feel?";

		message // Create a message object object
				.setChatId(user.getChatId()).setText(botAnswer);

		InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
		List<InlineKeyboardButton> rowInline = new ArrayList<>();
		rowInline.add(new InlineKeyboardButton().setText("Sad :(").setCallbackData("sad"));
		rowInline.add(new InlineKeyboardButton().setText("Happy :)").setCallbackData("happy"));
		// Set the keyboard to the markup
		rowsInline.add(rowInline);
		// Add it to the message
		markupInline.setKeyboard(rowsInline);
		message.setReplyMarkup(markupInline);

		MoodaleBot.this.log(user, botAnswer);

	}

	private void help(SendMessage message, User user) {

		String botAnswer = user.getMessage(); // default

		if (isStart == true) {
			botAnswer = "Sorry. I can't help you!!! :P";
		} else {
			botAnswer = "Please start /start";
		}

		message.setChatId(user.getChatId()).setText(botAnswer);

		MoodaleBot.this.log(user, botAnswer);
	}

	private void log(User user, String botAnswer) {
		System.out.println("\n ----------------------------");
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		System.out.println("Message from " + user.getFirstName() + " " + user.getLastName() + ". (id = "
				+ user.getUserId() + ") \nText - " + user.getMessage() + " + " + user.getCallbackQueryData());
		System.out.println("Bot answer: \nText - " + botAnswer);
	}

}
