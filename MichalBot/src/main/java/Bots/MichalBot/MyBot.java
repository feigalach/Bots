package Bots.MichalBot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class MyBot extends TelegramLongPollingBot{

	private boolean isStart = false;
	
	public String getBotUsername() {
		// TODO Auto-generated method stub
		return "duk1_bot";
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return "584211303:AAEscHWwmOplDXb79rlPaNm5emN-QXbnXOw";
	}

	public void onUpdateReceived(Update update) {

		// We check if the update has a message and the message has text
		if (update.hasMessage() && update.getMessage().hasText()) {
			// Set variables

			SendMessage message = new SendMessage();

			switch (update.getMessage().getText()) {
			case "/start":
				start(message, update);
				break;
			case "/help":
				help(message, update);
				break;

			default:
				
				String userFirstName = update.getMessage().getChat().getFirstName();
		        String userLastName = update.getMessage().getChat().getLastName();
		        long userId = update.getMessage().getChat().getId();
		        String userMessage = update.getMessage().getText();
		        String botAnswer = "Do you need HELP? /help";
		        long chatId = update.getMessage().getChatId();
				
				message.setChatId(chatId).setText(botAnswer);
				MyBot.this.log(userFirstName, userLastName, String.valueOf(userId), userMessage, botAnswer);

				break;
			}

			try {
				execute(message); // Sending our message object to user
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private void start(SendMessage message, Update update){
		
		String userFirstName = update.getMessage().getChat().getFirstName();
        String userLastName = update.getMessage().getChat().getLastName();
        String userUsername = update.getMessage().getChat().getUserName();
        long userId = update.getMessage().getChat().getId();
        String userMessage = update.getMessage().getText();
        String botAnswer = userMessage; // default
        long chatId = update.getMessage().getChatId();
		
		
		if (isStart == false) {
			
			isStart = true;

			botAnswer = "You send /start";
	        
			message // Create a message object object
					.setChatId(chatId).setText(botAnswer);
			
			KeyboardButton keyBoardButton = new KeyboardButton("option1");
			keyBoardButton.setRequestContact(false);			
			
			
			////
			
			ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
			List<KeyboardRow> keyboard = new ArrayList<>();
			KeyboardRow keyboardRow1 = new KeyboardRow();
			//keyboardRow.add(keyBoardButton);
			keyboardRow1.add("keyboardRow1");
			//keyboardRow.add(1, "keyboardRow2");
			
			KeyboardRow keyboardRow2 = new KeyboardRow();
			keyboardRow2.add("keyboardRow2");
			keyboard.add(keyboardRow1);
			keyboard.add(keyboardRow2);
			replyKeyboardMarkup.setKeyboard(keyboard);
			
			message.setReplyMarkup(replyKeyboardMarkup);
			
		/*	InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
			List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
			List<InlineKeyboardButton> rowInline = new ArrayList<>();
			rowInline.add(new InlineKeyboardButton().setText("Update message text")
					.setCallbackData("option1"));
			// Set the keyboard to the markup
			rowsInline.add(rowInline);
			// Add it to the message
			markupInline.setKeyboard(rowsInline);
			message.setReplyMarkup(markupInline);*/

		} else {
			
			botAnswer = "started already (choose something)";
			
			message // Create a message object object
			.setChatId(chatId).setText(botAnswer);
		}
		
		MyBot.this.log(userFirstName, userLastName, String.valueOf(userId), userMessage, botAnswer);
		
	}
	
	private void help(SendMessage message, Update update){
		
		String userFirstName = update.getMessage().getChat().getFirstName();
        String userLastName = update.getMessage().getChat().getLastName();
        String userUsername = update.getMessage().getChat().getUserName();
        long userId = update.getMessage().getChat().getId();
        String userMessage = update.getMessage().getText();
        String botAnswer = userMessage; // default
        long chatId = update.getMessage().getChatId();
		
		//message 
		//.setChatId(chat_id).setText("You can control me by sending these commands: \n /start ");
        
        botAnswer = "Sorry. I can't help you :P";
        
		message 
		.setChatId(chatId).setText(botAnswer);
		
		MyBot.this.log(userFirstName, userLastName, String.valueOf(userId), userMessage, botAnswer);
	}
	
	private void log(String first_name, String last_name, String user_id, String txt, String bot_answer) {
        System.out.println("\n ----------------------------");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        System.out.println("Message from " + first_name + " " + last_name + ". (id = " + user_id + ") \n Text - " + txt);
        System.out.println("Bot answer: \n Text - " + bot_answer);
    }

	

}
