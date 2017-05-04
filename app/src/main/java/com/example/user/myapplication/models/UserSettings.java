package com.example.user.myapplication.models;

/**
 * Created by User on 04.05.2017.
 */

public class UserSettings {
    String name;
    String password;
    boolean run;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }
}
