package ru.gasu;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import java.util.ArrayList;

public class KeyBoards {
    public void sendMsg(Message message, String text){
        SendMessage sendMessage= new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        setButtons(sendMessage);
        execute(sendMessage);
    }

    private static ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    static SendMessage addKeyboard(String[] text, Update update, String answer) {
        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();

        replyKeyboardMarkup.setSelective(true);
        //replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        for (String s : text) {
            keyboardRow.add(s);
            keyboard.add(keyboardRow);
            keyboardRow = new KeyboardRow();
        }
        keyboardRow.add("back");
        keyboard.add(keyboardRow);

        replyKeyboardMarkup.setKeyboard(keyboard);
        sendMessage.setText(answer);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }

    private void execute(SendMessage sendMessage) {
    }
    private void setButtons(SendMessage sendMessage) {

    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();

        if (message.equals("/start")) {
            sendMsg(message, "Добрый день!");
        }

        if (message != null && message.hasText()){
            switch (message.getText()) {
                case "/help":
                    sendMsg(message, "Нужна помощь?");
                    break;
                default:
            }
        }
    }
}
