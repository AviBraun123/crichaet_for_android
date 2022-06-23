package com.example.crichaet;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ChatViewModel extends ViewModel {
    private MutableLiveData<String> token;
    private ChatRepository repo;

    public ChatViewModel(MutableLiveData<String> token) {
        this.token = token;
        this.repo = new ChatRepository(token);
    }

    public void getContacts(MutableLiveData<List<Contact>> list,  MutableLiveData<String> tok) {
        this.repo.getContacts(list, tok);
    }

    public void getContact(MutableLiveData<Contact> contact, String id) {
        this.repo.getContact(contact, id);
    }

    public void CreateContact(String id, String name, String server) {
        this.repo.CreateContact(id, name, server);
    }

    public void changeContact(String id, String name, String server) {
        this.repo.changeContact(id, name, server);
    }

    public void deleteContact(String id) {
        this.repo.deleteContact(id);
    }

    public void getChat(MutableLiveData<List<Message>> lista, String id) {
        this.repo.getChat(lista, id);
    }

    public void CreateMessage(String id, String content) {
        this.repo.CreateMessage(id, content);
    }

    public void getMessage(String id1, int id2) {
        this.repo.getMessage(id1, id2);
    }

    public void editMessage(String id1,int id2, String content) {
        this.repo.editMessage(id1,id2,content);
    }

    public void deleteMessage(String id1, int id2) {
        this.repo.deleteMessage(id1, id2);
    }

    public void transfer(String from, String to, String content) {
        this.repo.transfer(from, to, content);
    }
    public void invite(String from, String to, String server) {
        this.repo.invite(from, to, server);
    }
}
