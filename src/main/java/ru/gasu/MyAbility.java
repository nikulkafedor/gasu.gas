package ru.gasu;

import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.abilitybots.api.util.AbilityExtension;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.Privacy;
import org.telegram.telegrambots.bots.DefaultBotOptions;

public class MyAbility implements AbilityExtension {
    private SilentSender silent;
    DBContext db;
    private final String BOT_NAME;

    MyAbility(SilentSender silent, DBContext db, String BOT_NAME) {
        this.silent = silent;
        this.db = db;
        this.BOT_NAME = BOT_NAME;
    }

//    public MyAbility(String botToken, String botName, String bot_name) {
//
//        BOT_NAME = bot_name;
//    }

    public Ability start() {
        return Ability
                .builder()
                .name("start")
                .locality(Locality.ALL)
                .privacy(Privacy.PUBLIC)
                .input(0)
                .action(ctx -> {
                    silent.send("Добрый день!.", ctx.chatId());
                    silent.send("Для начала работы необходимо выбрать команду /NewUser", ctx.chatId());
                })
                .build();
    }

    public Ability NewUser() {
        return Ability
                .builder()
                .name("NewUser")
                .locality(Locality.ALL)
                .privacy(Privacy.PUBLIC)
                .input(0)
                .action(ctx -> {
                    silent.send(String.format("User %s join this account", ctx.user().getLastName()), ctx.chatId());
                })
                .build();
    }

    public Ability showPeople() {
        return Ability
                .builder()
                .name("showPeople")
                .locality(Locality.ALL)
                .privacy(Privacy.PUBLIC)
                .action(ctx -> {
                    String message = "People:";
                    silent.send(message, ctx.chatId());
                })
                .build();
    }

    public Ability stop() {
        return Ability
                .builder()
                .name("stop")
                .locality(Locality.ALL)
                .privacy(Privacy.PUBLIC)
                .action(ctx -> {
                    silent.send("Account was cleared", ctx.chatId());
                })
                .build();
    }
}