package com.example.crichaet;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity
public class User {
    @PrimaryKey()
    @NonNull
    private String id;
    private String nickname;
    private String password;
    private String profpic;

    public User(String id, String nickname, String password, String profpic) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.profpic = profpic;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfpic() {
        return profpic;
    }

    public void setProfpic(String profpic) {
        this.profpic = profpic;
    }

}
