package com.example.crichaet;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface WebServiceAPI {

    @GET("Contacts")
    Call<List<Contact>> getContacts(@Header("Authorization") String token);

    @GET("Contacts/{id}")
    Call<Contact> getContact(@Header("Authorization") String token, @Path("id") String id);

    @POST("Contacts")
    Call<Void> CreateContact(@Header("Authorization") String token, @Body Mati mati);

    @PUT("Contacts/{id}")
    Call<Void> changeContact(@Header("Authorization") String token, @Path("id") String id, @Path("name") String name, @Path("server") String server);

    @DELETE("Contacts/{id}")
    Call<Void> deleteContact(@Header("Authorization") String token, @Path("id") String id);

    @GET("Contacts/{id}/messages")
    Call<List<Message>> getChat(@Header("Authorization") String token, @Path("id") String id);

    @POST("Contacts/{id}/messages")
    Call<Void> CreateMessage(@Header("Authorization") String token, @Body JsonObject body);

    @GET("Contacts/{id}/messages/{id2}")
    Call<Message> getMessage(@Header("Authorization") String token, @Path("id") String id, @Path("id2") int id2);

    @PUT("Contacts/{id}/messages/{id2}")
    Call<Void> editMessage(@Header("Authorization") String token, @Path("id") String id, @Path("id2") int id2, @Path("content") String content);

    @DELETE("Contacts/{id}/messages/{id2}")
    Call<Void> deleteMessage(@Header("Authorization") String token, @Path("id") String id, @Path("id2") int id2);

    @POST("transfer")
    Call<Void> transfer(@Body Avi avi);

    @POST("invitations")
    Call<Void> invite(@Body Neri neri);

    @POST("Users")
    Call<String> login(@Body JsonObject body);

    @POST("Users/reg")
    Call<String> register(@Body User user);

    @GET("Users")
    Call<List<User>> getUsers();

    @GET("Users/{id}")
    Call<User> getUser(@Path("id") int id);

    @PUT("Users/{id}")
    Call<Void> editUser(@Path("id") int id);

    @DELETE("Users/{id}")
    Call<Void> deleteUser(@Path("id") int id);

}
