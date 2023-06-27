package com.example.tm2ex3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.room.Room;

import com.example.tm2ex3.DB.AppChatDB;
import com.example.tm2ex3.DB.AppMessageDB;
import com.example.tm2ex3.DB.AppUserDB;
import com.example.tm2ex3.DB.ChatDB;
import com.example.tm2ex3.DB.ChatDao;
import com.example.tm2ex3.DB.MessageDao;
import com.example.tm2ex3.DB.UserDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ChatsActivity extends AppCompatActivity {
    private ChatDao chatDao;
    private AppChatDB db;

    private UserDao userDao;

    SharedPreferences sharedPreferences;
    private final String defaultServer = "http://10.0.2.2:5000/api/";
    private AppUserDB userDb;
    private List<Chats> chats = new ArrayList<>();
    private ListView listChats;

    private String token;

    private String displayName;

    private String profilePic;

    private String username;
    final ChatsAdapter chatsAdapter = new ChatsAdapter(chats);
    private ChatAPI chatAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);


        userDb = Room.databaseBuilder(getApplicationContext(), AppUserDB.class, "UserDB")
                .allowMainThreadQueries().build();

        userDao = userDb.userDao();


        if(userDao.getDBSize() != 0){
            token = userDao.getUser().getToken();
            profilePic = userDao.getUser().getProfilePic();
            displayName = userDao.getUser().getDisplayName();
        } else{
            userDb.close();
            return;
        }


        FloatingActionButton addContact = findViewById(R.id.btnAddChat);
        listChats = findViewById(R.id.chatsListXml);
        listChats.setAdapter(chatsAdapter);

        ImageView logout = findViewById(R.id.logoutXML);
        ImageView myProfilePic = findViewById(R.id.myUserPicXml);
        TextView myDisplayName = findViewById(R.id.myDisplayName);

        myDisplayName.setText(displayName + "'s chats");
        String cleanImage = profilePic.replace("data:image/png;base64,", "").replace("data:image/jpeg;base64,", "");
        byte[] decodedString = Base64.decode(cleanImage, Base64.DEFAULT);
        myProfilePic.setImageBitmap(BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length));

        db = Room.databaseBuilder(getApplicationContext(), AppChatDB.class, "ChatDB")
                .allowMainThreadQueries().build();

        chatDao = db.chatDao();



        AppMessageDB messageDB = Room.databaseBuilder(getApplicationContext(), AppMessageDB.class, "MessageDB")
                .allowMainThreadQueries().build();

        MessageDao messageDao = messageDB.MessageDao();

        logout.setOnClickListener(v->{
            chatDao.deleteAll();
            messageDao.deleteAllMessages();
            userDao.deleteAllUsers();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });

        listChats.setOnItemClickListener((parent, view, position, id)->{
            Intent intent = new Intent(this, MessagesActivity.class);
            intent.putExtra("contactDisplayname", chats.get(position).getName() + " ");
            intent.putExtra("chatId", chats.get(position).getId());
            intent.putExtra("contactUsername", chats.get(position).getUsername());
            startActivity(intent);
        });
        addContact.setOnClickListener(view->{
            Intent intent = new Intent(this, AddContact.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver((mMessageReceiver),
                new IntentFilter("MyData")
        );
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            /*String msg = intent.getExtras().getString("message");
            String sendChatId = intent.getExtras().getString("sendChatId");
            String sendDate = intent.getExtras().getString("sendDate");
            for (int i = 0; i < chats.size(); i++) {
                if (chats.get(i).getId().equals(sendChatId)) {
                    chats.get(i).setNewMessage(msg, sendDate);
                    chatsAdapter.notifyDataSetChanged();
                    chatDao.updateChat(sendDate, msg, sendChatId);
                    return;
                }
            }*/
            chatAPI.getChats(chats, chatsAdapter, chatDao);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        if(userDao.getDBSize() == 0){
            return;
        }
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        String serverUrl = sharedPreferences.getString("server", defaultServer);
        chatAPI = new ChatAPI(token, serverUrl);
        chatAPI.getChats(chats, chatsAdapter, chatDao);

        chats.clear();
        List<ChatDB> list = chatDao.index();
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i).getProfilePic();
            byte[] decodedString = Base64.decode(str, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            chats.add(new Chats(list.get(i).getName(), list.get(i).getLastMessage(),
                    list.get(i).getDate(), decodedByte, list.get(i).getChatId(),
                    list.get(i).getUsername()));

        }
        chatsAdapter.notifyDataSetChanged();
        chatAPI.getChats(chats, chatsAdapter, chatDao);
    }
}