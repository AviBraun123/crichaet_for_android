package com.example.crichaet;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String idname;
    private String nickName;
    private String server;
    private String last;
    private int idmassage;
    private String lastdate;
    private String userid;

    public Contact(int id, String idname, String nickName, String server, String last, int idmassage, String lastdate, String userid) {
        this.id = id;
        this.idname = idname;
        this.nickName = nickName;
        this.server = server;
        this.last = last;
        this.idmassage = idmassage;
        this.lastdate = lastdate;
        this.userid = userid;
    }

    public Contact() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdname() {
        return idname;
    }

    public void setIdname(String idname) {
        this.idname = idname;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public int getIdmassage() {
        return idmassage;
    }

    public void setIdmassage(int idmassage) {
        this.idmassage = idmassage;
    }

    public String getLastdate() {
        return lastdate;
    }

    public void setLastdate(String lastdate) {
        this.lastdate = lastdate;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        String dots = " ";
        String a = this.last;
        if (this.last == null) {
            return this.nickName;
        }
        if (this.last.length() > 12) {
            a = this.last.substring(0,12);
            dots = "... ";
        }
        return this.nickName + " " + a + dots +
                this.lastdate.substring(11,16) + " " + this.lastdate.substring(0,10);
    }
}
