package com.example.tm2ex3.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MessageDao {

    @Query("SELECT * FROM messageDB WHERE chatId = :chatId")
    List<MessageDB> getChatMessages(String chatId);

    @Query("DELETE FROM messageDB WHERE chatId = :chatId")
    void deleteChatMessages(String chatId);

    @Query("DELETE FROM messageDB")
    void deleteAllMessages();

    @Insert
    void insert(MessageDB... MessageDBs);

    @Update
    void update(MessageDB... MessageDBs);

    @Delete
    void delete(MessageDB... MessageDBs);
}
