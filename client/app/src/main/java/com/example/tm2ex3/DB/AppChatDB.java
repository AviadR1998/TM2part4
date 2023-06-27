package com.example.tm2ex3.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ChatDB.class}, version = 1)
public abstract class AppChatDB extends RoomDatabase {
    public abstract ChatDao chatDao();
}
