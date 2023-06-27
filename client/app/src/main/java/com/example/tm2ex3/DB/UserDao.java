package com.example.tm2ex3.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {

    @Query("SELECT * FROM userdb")
    UserDB getUser();

    @Query("DELETE FROM userdb")
    void deleteAllUsers();

    @Query("SELECT COUNT(*) FROM userdb")
    int getDBSize();

    @Insert
    void insert(UserDB... UserDBs);

    @Update
    void update(UserDB... UserDBs);

    @Delete
    void delete(UserDB... UserDBs);
}
