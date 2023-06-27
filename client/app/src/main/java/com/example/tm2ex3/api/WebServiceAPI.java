package com.example.tm2ex3.api;

import com.example.tm2ex3.ChatJSON;
import com.example.tm2ex3.NewChatJSON;
import com.example.tm2ex3.UserAndPassJson;
import com.example.tm2ex3.UserJSON;
import com.example.tm2ex3.UserPostJson;
import com.example.tm2ex3.messagesJsons.GetAllChatInfo;
import com.example.tm2ex3.messagesJsons.MessageMJson;
import com.example.tm2ex3.messagesJsons.Msg;
import com.example.tm2ex3.messagesJsons.PostMessagesJSON;
import com.example.tm2ex3.messagesJsons.SenderJSON;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceAPI {

    @POST("Tokens")
    Call<String> createToken(@Body UserAndPassJson user);

    @POST("Users")
    Call<Void> createUser(@Body UserPostJson user);
    //Call<UserPostJson> createUser(@Body UserPostJson user);

    @GET("Users/{username}")
    Call<UserJSON> getUser(@Path("username") String username, @Header("authorization") String token);

    @GET("Chats")
    Call<List<ChatJSON>> getChats(@Header("authorization") String token);

    @POST("Chats")
    Call<NewChatJSON> postChats(@Body SenderJSON sender, @Header("authorization") String token);

    @GET("Chats/{id}")
    Call<GetAllChatInfo> getChatInfo(@Path("id") String id, @Header("authorization") String token);

    @GET("Chats/{id}/Messages")
    Call<List<MessageMJson>> getMessages(@Path("id") String id, @Header("authorization") String token);

    @POST("Chats/{id}/Messages")
    Call<PostMessagesJSON> postMessages(@Header("authorization") String token, @Path("id") String id, @Body Msg msg);

    @POST("Tokens/androidToken")
    Call<Void> createAndroidToken(@Body SenderJSON user, @Header("authorization") String andToken);
}
