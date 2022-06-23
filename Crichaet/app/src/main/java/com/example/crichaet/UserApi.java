package com.example.crichaet;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserApi {
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public UserApi() {
        retrofit = new Retrofit.Builder().baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create()).build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void login(MutableLiveData<String> token,String id) {
        try {
            JsonObject bod = new JsonObject();
            bod.addProperty("id", id);
            Call<String> call = webServiceAPI.login(bod);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {

                    if (response.body() != null) {
                        Log.i("login api", response.body());
                        token.setValue("Bearer " + response.body());
                    } else
                        token.setValue(null);
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    int i = 0;
                }
            });
        } catch (Exception e) {

        }
    }

    public void register(String id, String nickname, String password, String profPic, MutableLiveData<String> token) {
        User user = new User(id, nickname, password, profPic);
        Call<String> call = webServiceAPI.register(user);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.body() != null) {
                    Log.i("login api", response.body());
                    token.setValue("Bearer " + response.body());
                } else
                    token.setValue(null);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                int i = 0;
            }
        });
    }

    public void getUsers(MutableLiveData<List<User>> list) {
        Call<List<User>> call = webServiceAPI.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                list.setValue(response.body());
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                int k = 0;
            }
        });
    }
}
