package ru.gasu;

import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.telegrambots.bots.DefaultBotOptions;

public class FamiliarityBot extends AbilityBot {

    private static final String BOT_NAME = "@znakommstvoBot";
    private static final String BOT_TOKEN = "1143599166:AAEQ7eeovLcGMTm79f1XrrMRHmhJY77SuUg";
//    List<Description> arrayOfDescription;
//    FamiliarityBot FamiliarityBot;

//    public FamiliarityBot(SilentSender silent, DBContext db) {
//        super(silent, db);
//    }



    public FamiliarityBot(DefaultBotOptions botOptions) {
        super(BOT_TOKEN, BOT_NAME, botOptions);
    }

    public FamiliarityBot() {
        super(BOT_TOKEN, BOT_NAME);
    }

    public MyAbility myAbility() {
        return new MyAbility( silent,db, BOT_NAME);
    }


    public int creatorId() {
        return 0;
    }

//    public static void trySendMessage(AbsSender absSender, User user, SendMessage sendMessage) {
//        try {
//            absSender.execute(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
}