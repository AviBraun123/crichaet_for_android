package com.example.crichaet;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User")
    List<User> index();
    @Query("SELECT * FROM User WHERE id=:id")
    User get(String id);
    @Insert
    void insert(User... users);
    @Update
    void update(User... users);
    @Delete
    void delete(User... users);
}
