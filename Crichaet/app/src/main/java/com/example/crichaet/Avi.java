package com.example.crichaet;

public class Avi {
    private String from;
    private String to;
    private String content;

    public Avi(String from, String to, String content) {
        this.from = from;
        this.to = to;
        this.content = content;
    }

    public Avi() {
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String server) {
        this.content = server;
    }
}
