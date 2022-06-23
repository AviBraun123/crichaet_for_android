package com.example.crichaet;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class ChatRepository {
    private MutableLiveData<String> token;
    private ChatApi chatApi;

    public ChatRepository(MutableLiveData<String> token){
        this.token = token;
        this.chatApi = new ChatApi(this.token);
    }

    public void getContacts(MutableLiveData<List<Contact>> list,  MutableLiveData<String> tok) {
        this.chatApi.getContacts(list, tok);
    }

    public void getContact(MutableLiveData<Contact> contact, String id) {
        this.chatApi.getContact(contact, id);
    }

    public void CreateContact(String id, String name, String server) {
        Mati mati = new Mati(id,name,server);
        this.chatApi.CreateContact(mati);
    }

    public void changeContact(String id, String name, String server) {
        this.chatApi.changeContact(id, name, server);
    }

    public void deleteContact(String id) {
        this.chatApi.deleteContact(id);
    }

    public void getChat(MutableLiveData<List<Message>> lista, String id) {
        this.chatApi.getChat(lista, id);
    }

    public void CreateMessage(String id, String content) {
        this.chatApi.CreateMessage(id, content);
    }

    public void getMessage(String id1, int id2) {
        this.chatApi.getMessage(id1, id2);
    }

    public void editMessage(String id1,int id2, String content) {
        this.chatApi.editMessage(id1,id2,content);
    }

    public void deleteMessage(String id1, int id2) {
        this.chatApi.deleteMessage(id1, id2);
    }

    public void transfer(String from, String to, String content) {
        Avi avi = new Avi(from, to,content);
        this.chatApi.transfer(avi);
    }
    public void invite(String from, String to, String server) {
        Neri neri = new Neri(from, to, server);
        this.chatApi.invite(neri);
    }



}
