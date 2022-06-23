package com.example.crichaet;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MessageDao {
    @Query("SELECT * FROM Message WHERE (user1 = :id1 AND user2 = :id2) OR (user1 = :id2 AND user2 = :id1)")
    List<Message> index(String id1, String id2);
    @Query("SELECT * FROM Message WHERE id=:id")
    Message get(int id);
    @Insert
    void insert(Message... messages);
    @Update
    void update(Message... messages);
    @Delete
    void delete(Message... messages);
}
