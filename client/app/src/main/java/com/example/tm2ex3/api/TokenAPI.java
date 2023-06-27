package com.example.tm2ex3.api;

import com.example.tm2ex3.UserAndPassJson;
import com.example.tm2ex3.UserJSON;
import com.example.tm2ex3.UserPostJson;
import com.example.tm2ex3.messagesJsons.SenderJSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TokenAPI {

    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public TokenAPI(String serverUrl){
        try {
            Gson gson = new GsonBuilder().setLenient().create();
            retrofit = new Retrofit.Builder().baseUrl(serverUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson)).build();

            webServiceAPI = retrofit.create(WebServiceAPI.class);
        } catch (Exception e){
            retrofit = null;
            return;
        }

    }

    public Call<String> postToken(String username, String password){
        return webServiceAPI.createToken(new UserAndPassJson(username, password));
    }

    public Call<Void> createUser(String username, String password, String displayName, String profilePic){
        return webServiceAPI.createUser(new UserPostJson(username, password, displayName, profilePic));
    }

    public Call<UserJSON> getUser(String username, String token){
        return  webServiceAPI.getUser(username, token);
    }

    public Call<Void> postAndroidToken(String username, String andToken){
        return webServiceAPI.createAndroidToken(new SenderJSON(username), andToken);
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
