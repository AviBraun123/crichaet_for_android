package com.example.crichaet;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class UserViewModel extends ViewModel {
    private MutableLiveData<String> token;
    private UserRepository repo;

    public UserViewModel() {
        this.token = new MutableLiveData<>();
        this.repo = new UserRepository();
    }

    public MutableLiveData<String> getToken() {
        return token;
    }

    public void login(MutableLiveData<String> tok,String id) {
        this.repo.login(tok, id);
    }

    public void register(String id, String nickname, String password, String profPic, MutableLiveData<String> tok) {
        this.repo.register(id, nickname, password, profPic, tok);
    }

    public void getUsers(MutableLiveData<List<User>> list) {

        this.repo.getUsers(list);
    }
}