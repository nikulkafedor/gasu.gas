package ru.gasu;

import org.telegram.abilitybots.api.db.DBContext;

import java.util.Map;

public class Profile<S> {
    private int idProfile;
    private String Name;
    private String age;
    private String sex;
    private boolean like;

    public Profile() {
        this.idProfile = idProfile;
        this.Name = Name;
        this.age = age;
        this.sex =  sex;
        this.like = like;
    }

    public int getIdProfile(Integer idProfile, String name,  Integer age, boolean like) {
        return this.idProfile;
    }

    public String getName(String s) {
        return Name;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean isLike() {
        return like;
    }

    public String getAge(String s) {
        return age;
    }

    public void setIdProfile(int idProfile) {
        this.idProfile = idProfile;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }
    private  Map<Boolean,String> likeMap;

    void Like(DBContext db){
        likeMap = db.getMap("Like");
    }

    private Map<Integer, String> profileMap;

    Profile(DBContext db) {
        profileMap = db.getMap("Profiles");
    }

    public void add(String s) {
        profileMap.put(profileMap.size(), s);
    }

    String[] get() {
        String[] myArray = new String[profileMap.size()];
        ;
        for (int i : profileMap.keySet()) {
            myArray[i] = (profileMap.get(i));
        }
        return myArray;
    }

    public void remove() {
        for (int i : profileMap.keySet()) {
            profileMap.remove(i);
        }
    }
}