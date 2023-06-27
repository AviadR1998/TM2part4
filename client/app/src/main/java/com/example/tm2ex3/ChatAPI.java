package com.example.tm2ex3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.example.tm2ex3.messagesJsons.GetAllChatInfo;
import com.example.tm2ex3.messagesJsons.MessageMJson;
import com.example.tm2ex3.messagesJsons.Msg;
import com.example.tm2ex3.messagesJsons.PostMessagesJSON;
import com.example.tm2ex3.messagesJsons.SenderJSON;
import com.example.tm2ex3.DB.ChatDB;
import com.example.tm2ex3.DB.ChatDao;
import com.example.tm2ex3.DB.MessageDB;
import com.example.tm2ex3.DB.MessageDao;
import com.example.tm2ex3.api.WebServiceAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatAPI {
    //private MutableLiveData<List<Chats>> postListData;
    //private PostDao dao;
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    private String token;
    public ChatAPI(String token, String serverUrl) {
        this.token = token;
        retrofit = new Retrofit.Builder()
                .baseUrl(serverUrl  )
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void postMessages(String chatId, String msg) {
        Call<PostMessagesJSON> call = webServiceAPI.postMessages("bearer " + token, chatId, new Msg(msg));
        call.enqueue(new Callback<PostMessagesJSON>() {
            @Override
            public void onResponse(Call<PostMessagesJSON> call, Response<PostMessagesJSON> response) {
            }

            @Override
            public void onFailure(Call<PostMessagesJSON> call, Throwable t) {
            }
        });
    }

    public void getMessages(List<Messages> listMessages, MessagesAdapter messagesAdapter, String chatId, MessageDao messageDao, String username) {
        Call<List<MessageMJson>> call = webServiceAPI.getMessages(chatId,"bearer " + token);
        call.enqueue(new Callback<List<MessageMJson>>() {
            @Override
            public void onResponse(Call<List<MessageMJson>> call, Response<List<MessageMJson>> response) {
                List<MessageMJson> messages = response.body();
                messageDao.deleteChatMessages(chatId);
                listMessages.clear();
                if (messages != null) {
                    for (int i = messages.size() - 1; i >= 0; i--) {
                        listMessages.add(new Messages(messages.get(i).getContent(), messages.get(i).getSender().getUsername().equals(username)));
                        messageDao.insert(new MessageDB(chatId, messages.get(i).getContent(), messages.get(i).getSender().getUsername()));
                    }
                    messagesAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<MessageMJson>> call, Throwable t) {
            }
        });
    }

    public void getChatInfo(String chatId) {
        Call<GetAllChatInfo> call = webServiceAPI.getChatInfo(chatId, "bearer " + token);
        call.enqueue(new Callback<GetAllChatInfo>() {
            @Override
            public void onResponse(Call<GetAllChatInfo> call, Response<GetAllChatInfo> response) {
            }

            @Override
            public void onFailure(Call<GetAllChatInfo> call, Throwable t) {
            }
        });
    }

    public Call<NewChatJSON> postChat(String username) {
        return webServiceAPI.postChats(new SenderJSON(username), "bearer "  + token);
    }

    public void getChats(List<Chats> listChats, ChatsAdapter chatsAdapter, ChatDao chatDao) {
        Call<List<ChatJSON>> call = webServiceAPI.getChats("bearer " + token);

        call.enqueue(new Callback<List<ChatJSON>>() {
            @Override
            public void onResponse(Call<List<ChatJSON>> call, Response<List<ChatJSON>> response) {
                List<ChatJSON> chats = response.body();
                chatDao.deleteAll();
                listChats.clear();
                for (int i = 0; chats != null && i < chats.size(); i++) {
                    String cleanImage = chats.get(i).getUser().getProfilePic().replace("data:image/png;base64,", "").replace("data:image/jpeg;base64,", "");
                    byte[] decodedString = Base64.decode(cleanImage, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    if (chats.get(i).getLastMessage() == null) {
                        listChats.add(new Chats(chats.get(i).getUser().getDisplayName(),
                                "",
                                "", decodedByte, chats.get(i).getId(),
                                chats.get(i).getUser().getUsername()));
                        chatDao.insert(new ChatDB(chats.get(i).getUser().getDisplayName(),
                                "",
                                "", cleanImage, chats.get(i).getId(),
                                chats.get(i).getUser().getUsername()));
                    } else {
                        listChats.add(new Chats(chats.get(i).getUser().getDisplayName(),
                                chats.get(i).getLastMessage().getContent(),
                                chats.get(i).getLastMessage().getCreated(), decodedByte,
                                chats.get(i).getId(),
                                chats.get(i).getUser().getUsername()));
                        chatDao.insert(new ChatDB(chats.get(i).getUser().getDisplayName(),
                                chats.get(i).getLastMessage().getContent(),
                                chats.get(i).getLastMessage().getCreated(), cleanImage,
                                chats.get(i).getId(),
                                chats.get(i).getUser().getUsername()));
                    }
                }
                chatsAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<ChatJSON>> call, Throwable t) {
            }
        });
    }
}
