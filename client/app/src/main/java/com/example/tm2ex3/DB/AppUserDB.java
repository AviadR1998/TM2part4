package com.example.tm2ex3.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UserDB.class}, version = 1)
public abstract class AppUserDB extends RoomDatabase {

    public abstract UserDao userDao();
}
