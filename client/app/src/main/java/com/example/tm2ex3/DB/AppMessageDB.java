package com.example.tm2ex3.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MessageDB.class}, version = 1)
public abstract class AppMessageDB extends RoomDatabase {
    public abstract MessageDao MessageDao();
}
