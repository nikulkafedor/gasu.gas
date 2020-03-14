package ru.gasu;


import org.telegram.abilitybots.api.objects.MessageContext;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

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

    private void execute(SendMessage sendMessage) {
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

    public void setButtons(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        //    Profile<S> keyboardRowList = new Profile<S>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton( "/help"));
        keyboardFirstRow.add(new KeyboardButton("/NewUser"));
        keyboardFirstRow.add(new KeyboardButton("/stop"));
        keyboardFirstRow.add(new KeyboardButton("/showPeople"));
        keyboardFirstRow.add(new KeyboardButton("/stop"));
    }

}
