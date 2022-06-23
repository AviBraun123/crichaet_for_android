package com.example.crichaet;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatApi {
    private MutableLiveData<List<Contact>> contactListData;
    private MutableLiveData<List<Message>> messageListData;
    private MutableLiveData<List<User>> userListData;
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;
    ContactDao contactDao;
    MessageDao messageDao;
    UserDao userDao;
    private String token;

    //    public ChatApi(MutableLiveData<List<Contact>> contactListData, MutableLiveData<List<Message>> messageListData, MutableLiveData<List<User>> userListData, ContactDao contactDao, MessageDao messageDao, UserDao userDao) {
    public ChatApi(MutableLiveData<String> token) {
        retrofit = new Retrofit.Builder().baseUrl(MyApplication.context.getString(R.string.BaseUrl)).addConverterFactory(GsonConverterFactory.create()).build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
        this.token = token.getValue();
    }

    public void getContacts(MutableLiveData<List<Contact>> list, MutableLiveData<String> tok) {
        Call<List<Contact>> calls = webServiceAPI.getContacts(tok.getValue());
        calls.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                list.setValue(response.body());
                int i = 45;
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                list.setValue(new ArrayList<>());
                Log.w("MyTag", "requestFailed", t);
            }
        });
    }

    public void getContact(MutableLiveData<Contact> contact, String id) {
        Call<Contact> call = webServiceAPI.getContact(token, id);
        call.enqueue(new Callback<Contact>(){
            @Override
            public void onResponse(Call<Contact> call, Response<Contact> response) {
                Log.i("hh", "onResponse: ");
                contact.setValue(response.body());
            }
            @Override
            public void onFailure(Call<Contact> call, Throwable t) {
                Log.i("hh", t.getMessage() );
                int i = 0;
            }
        });
    }
    public void CreateContact(Mati mati) {
        Call call = webServiceAPI.CreateContact(token, mati);
        call.enqueue(new Callback (){
            @Override
            public void onResponse(Call call, Response response) {
                int i = 0;
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                int i = 1;
            }
        });
    }
    public void changeContact(String id, String name, String server) {
        Call call = webServiceAPI.changeContact(token, id, name, server);
        call.enqueue(new Callback (){
            @Override
            public void onResponse(Call call, Response response) {

            }
            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
    public void deleteContact(String id) {
        Call call = webServiceAPI.deleteContact(token, id);
        call.enqueue(new Callback (){
            @Override
            public void onResponse(Call call, Response response) {

            }
            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
    public void getChat(MutableLiveData<List<Message>> lista, String id) {
        Call<List<Message>> call = webServiceAPI.getChat(token, id);
        call.enqueue(new Callback<List<Message>>(){
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                lista.setValue(response.body());
            }
            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                int i = 0;
            }
        });
    }
    public void CreateMessage(String id, String content) {
        JsonObject body = new JsonObject();
        body.addProperty("id", id);
        body.addProperty("content", content);
        Call call = webServiceAPI.CreateMessage(token, body);
        call.enqueue(new Callback (){
            @Override
            public void onResponse(Call call, Response response) {
                int f = 0;
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                int g = 3;
            }
        });
    }
    public void getMessage(String id1, int id2) {
        Call<Message> call = webServiceAPI.getMessage(token, id1, id2);
        call.enqueue(new Callback<Message>(){
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                List<Message> list = new ArrayList<>();
                list.add(response.body());
                messageListData.setValue(list);
            }
            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });
    }
    public void editMessage(String id1,int id2, String content) {
        Call call = webServiceAPI.editMessage(token, id1, id2, content);
        call.enqueue(new Callback (){
            @Override
            public void onResponse(Call call, Response response) {

            }
            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
    public void deleteMessage(String id1, int id2) {
        Call call = webServiceAPI.deleteMessage(token, id1, id2);
        call.enqueue(new Callback (){
            @Override
            public void onResponse(Call call, Response response) {

            }
            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
    public void transfer(Avi avi) {
        Call call = webServiceAPI.transfer(avi);
        call.enqueue(new Callback (){
            @Override
            public void onResponse(Call call, Response response) {
            }
            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
    public void invite(Neri neri) {
        Call call = webServiceAPI.invite(neri);
        call.enqueue(new Callback (){
            @Override
            public void onResponse(Call call, Response response) {
            }
            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
    public void getUser(int id) {
        Call<User> call = webServiceAPI.getUser(id);
        call.enqueue(new Callback<User>(){
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
    public void editUser(int id) {
        Call call = webServiceAPI.editUser(id);
        call.enqueue(new Callback (){
            @Override
            public void onResponse(Call call, Response response) {

            }
            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
    public void deleteUser(int id) {
        Call call = webServiceAPI.deleteUser(id);
        call.enqueue(new Callback(){
            @Override
            public void onResponse(Call call, Response response) {

            }
            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}
