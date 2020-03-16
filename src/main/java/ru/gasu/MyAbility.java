package ru.gasu;

import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.objects.*;
import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.abilitybots.api.util.AbilityExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static javax.swing.UIManager.get;

public class MyAbility implements AbilityExtension {
    private SilentSender silent;
    DBContext db;
    private final String BOT_NAME;
    private String Name;

    MyAbility(SilentSender silent, DBContext db, String BOT_NAME) {
        this.silent = silent;
        this.db = db;
        this.BOT_NAME = BOT_NAME;
    }

private Map<Integer, Profile> profileDB;

    public Ability start() {
        return Ability
                .builder()
                .name("start")
                .locality(Locality.ALL)
                .privacy(Privacy.PUBLIC)
                .input(0)
                .action(ctx -> {
                    silent.send("Добрый день!", ctx.chatId());
                    silent.send("Введите информацию о себе через пробел в формате \"/add Name_LastName Age Gender\" ", ctx.chatId());
                })
                .build();
    }

public Ability join(){
        return  Ability
                .builder()
                .name("join")
                .locality(Locality.ALL)
                .privacy(Privacy.PUBLIC)
                .input(3)
                .action(ctx -> {
                    Profile profile = new Profile();
                    profile.setName(ctx.firstArg());
                    profile.setAge(ctx.secondArg());
                    profile.setSex(ctx.thirdArg());
                    profileDB.put(profileDB.size(),profile);
                    silent.send("Hi" + Name,ctx.chatId());
                })
                .build();
}

    public Reply allProfiles() {
        return Reply.of(update -> {
            Map<Integer, String[]> profileMap;
            Map<Boolean, String[]> likeMap;
            profileMap = db.getMap("Profiles");
            Integer q = 0;
            String[] myArray = new String[profileMap.size()];
            for (int i : profileMap.keySet()) {
                myArray[i] = profileMap.get(i)[0];
            }
            silent.send(Arrays.toString(myArray), update.getMessage().getChatId());
        }, update -> update.getMessage().getText().equals("/all"));
    }

    public Reply mutualLove() {
        return Reply.of(update -> {
            Map<Integer, String[]> profileMap;
            profileMap = db.getMap("newProfile");
            int[][] twoDimArray = new int[2][profileMap.size()];

//            for (int i = 0; i < twoDimArray.length; i++) {
//                System.out.println(twoDimArray[i]);
//            }

            Random rnd = new Random();
            String[] profile = profileMap.get(rnd.nextInt(profileMap.size()));

            silent.send(String.valueOf(profile), update.getMessage().getChatId());
        }, update -> update.getMessage().getText().equals("/liked"));
    }

//
//    public Ability liked() {
//        return Ability
//                .builder()
//                .name("liked")
//                .locality(Locality.ALL)
//                .privacy(Privacy.PUBLIC)
//                .input(0)
//                .action(ctx -> {
//                    silent.send("Вам нравится этот человек? Ответ в формате: /add2 1/0 (да/нет)\"/add2 liked\" ", ctx.chatId());
//                })
//                .build();
//    }
//
//
//    public Ability liked2(){
//        return  Ability
//                .builder()
//                .name("liked2")
//                .locality(Locality.ALL)
//                .privacy(Privacy.PUBLIC)
//                .input(3)
//                .action(ctx -> {
//                    Map<Integer, String[]> profileMap;
//                    profileMap = db.getMap("newProfile");
//                    int[][] twoDimArray = new int[2][profileMap.size()];
//                    Liked liked = new Liked();
//                    String answer;
//                    liked.setLiked(ctx.firstArg());
//                    if (liked.setLiked(ctx.firstArg()) == "1"){
//                        twoDimArray[1][profileMap.size()] = 1;
//                    } else {
//                        twoDimArray[0][profileMap.size()] = 1;
//                    }
////                    profileDB.put(profileDB.size(),liked());
//                    silent.send("Hi" + liked,ctx.chatId());
//                })
//                .build();
//    }


    public Ability stop() {
        return Ability
                .builder()
                .name("stop")
                .locality(Locality.ALL)
                .privacy(Privacy.PUBLIC)
                .action(ctx -> {
                    silent.send("Ваш аккаунт был очистен.", ctx.chatId());
                })
                .build();
    }
}