package com.example.tm2ex3;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.room.Room;

import com.example.tm2ex3.DB.AppChatDB;
import com.example.tm2ex3.DB.AppMessageDB;
import com.example.tm2ex3.DB.AppUserDB;
import com.example.tm2ex3.DB.ChatDao;
import com.example.tm2ex3.DB.MessageDB;
import com.example.tm2ex3.DB.MessageDao;
import com.example.tm2ex3.DB.UserDao;

import java.util.ArrayList;
import java.util.List;


public class MessagesActivity extends AppCompatActivity {
    private ChatDao chatDao;
    private AppChatDB chatDB;

    private UserDao userDao;
    private AppUserDB userDb;
    private MessageDao messageDao;
    private AppMessageDB messageDB;
    private EditText etSend;
    private List<Messages> messages = new ArrayList<>();
    private List<String> myMessages = new ArrayList<>();
    final MessagesAdapter messagesAdapter = new MessagesAdapter(messages);
    private ListView listMessages;
    //private Bitmap contactImg;
    private String chatId;
    private ChatAPI chatAPI;

    private String contactUsername;
    private String token;
    private String username;

    private final String defaultServer = "http://10.0.2.2:5000/api/";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        userDb = Room.databaseBuilder(getApplicationContext(), AppUserDB.class, "UserDB")
                .allowMainThreadQueries().build();

        userDao = userDb.userDao();

        if(userDao.getDBSize() == 0){
            return;
        }
        token = userDao.getUser().getToken();
        username = userDao.getUser().getUsername();

        chatId = getIntent().getStringExtra("chatId");
        TextView contactName = findViewById(R.id.contactNameXML);
        contactName.setText(getIntent().getStringExtra("contactDisplayname"));
        contactUsername = getIntent().getStringExtra("contactUsername");
        ImageButton send = findViewById(R.id.sendXml);
        etSend = findViewById(R.id.etSendXml);
        ImageView back = findViewById(R.id.backFromMessages);
        back.setOnClickListener(v->{
            Intent intent = new Intent(this, ChatsActivity.class);
            startActivity(intent);
        });
        listMessages = findViewById(R.id.messagesListXml);
        listMessages.setAdapter(messagesAdapter);


        chatDB = Room.databaseBuilder(getApplicationContext(), AppChatDB.class, "ChatDB")
                .allowMainThreadQueries().build();
        chatDao = chatDB.chatDao();

        messageDB = Room.databaseBuilder(getApplicationContext(), AppMessageDB.class, "MessageDB")
                .allowMainThreadQueries().build();
        messageDao = messageDB.MessageDao();

        String imgStr = chatDao.getChat(chatId).getProfilePic();


        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        String serverUrl = sharedPreferences.getString("server", defaultServer);
        byte[] decodedString = Base64.decode(imgStr, Base64.DEFAULT);

        ImageView contactImg = findViewById(R.id.profileContactImgXml);
        contactImg.setImageBitmap(BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length));

        chatAPI = new ChatAPI(token, serverUrl);
        chatAPI.getMessages(messages, messagesAdapter, chatId, messageDao, username);

        send.setOnClickListener(view->{
            if (!etSend.getText().toString().equals("")) {
                myMessages.add(etSend.getText().toString());
                messages.add(new Messages(etSend.getText().toString(), true));
                chatAPI.postMessages(chatId, etSend.getText().toString());
                messageDao.insert(new MessageDB(chatId, etSend.getText().toString(), username));
                messagesAdapter.notifyDataSetChanged();
                etSend.setText("");
                listMessages.setSelection(messagesAdapter.getCount() - 1);
            }
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
            String senderUsername = intent.getExtras().getString("senderUsername");
            if (sendChatId.equals(chatId)) {
                if (senderUsername.equals(contactUsername)) {
                    messages.add(new Messages(msg, false));
                    messagesAdapter.notifyDataSetChanged();
                    messageDao.insert(new MessageDB(sendChatId, msg, contactUsername));
                    listMessages.setSelection(messagesAdapter.getCount() - 1);
                } else {
                    for (int i = 0; i < myMessages.size(); i++) {
                        if (myMessages.get(i).equals(msg)){
                            myMessages.remove(msg);
                            return;
                        }
                    }
                    messages.add(new Messages(msg, true));
                    messagesAdapter.notifyDataSetChanged();
                    messageDao.insert(new MessageDB(sendChatId, msg, username));
                    listMessages.setSelection(messagesAdapter.getCount() - 1);
                }
            }*/
            chatAPI.getMessages(messages, messagesAdapter, chatId, messageDao, username);
            listMessages.setSelection(messagesAdapter.getCount() - 1);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        if(userDao.getDBSize() == 0){
            return;
        }
        myMessages.clear();
        messages.clear();
        List<MessageDB> list = messageDao.getChatMessages(chatId);
        for (int i = 0; i < list.size(); i++) {
            messages.add(new Messages(list.get(i).getMessage(), list.get(i).getSender().equals(username)));
        }
        messagesAdapter.notifyDataSetChanged();
    }
}