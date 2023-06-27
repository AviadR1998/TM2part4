package com.example.tm2ex3.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface ChatDao {
    @Query("SELECT * FROM chatdb")
    List<ChatDB> index();

    @Query("SELECT * FROM chatdb WHERE id = :id")
    ChatDB get(int id);

    @Query("SELECT * FROM chatdb WHERE chatId = :chatId")
    ChatDB getChat(String chatId);

    @Query("DELETE FROM chatdb")
    void deleteAll();

    @Query("UPDATE chatdb SET date = :date, lastMessage = :lastMessage WHERE chatId = :chatId")
    void updateChat(String date, String lastMessage, String chatId);

    @Insert
    void insert(ChatDB... ChatDBs);

    @Update
    void update(ChatDB... ChatDBs);

    @Delete
    void delete(ChatDB... ChatDBs);

}
