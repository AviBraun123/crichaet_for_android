package com.example.crichaet;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class UserRepository {
    private UserApi userApi;

    public UserRepository(){
        this.userApi = new UserApi();
    }

    public void login(MutableLiveData<String> tok, String id)
    {
        this.userApi.login(tok, id);
    }

    public void register(String id, String nickname, String password, String profPic, MutableLiveData<String> tok){
        this.userApi.register(id,nickname,password,profPic,tok);
    }

    public void getUsers(MutableLiveData<List<User>> list) {
        this.userApi.getUsers(list);
    }


}
