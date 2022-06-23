package com.example.crichaet;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Message.class, User.class, Contact.class}, version = 1)
public abstract class AppDB extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract MessageDao messageDao();
    public abstract ContactDao contactDao();
}
