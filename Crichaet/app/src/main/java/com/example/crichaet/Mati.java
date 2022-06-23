package com.example.crichaet;

public class Mati {
    private String id;
    private String name;
    private String server;

    public Mati(String id, String name, String server) {
        this.id = id;
        this.name = name;
        this.server = server;
    }

    public Mati() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }
}
