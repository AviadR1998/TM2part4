package com.example.tm2ex3;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.tm2ex3.DB.AppUserDB;
import com.example.tm2ex3.DB.UserDao;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddContact extends AppCompatActivity {
    private Context context = this;
    private String token;

    private final String defaultServer = "http://10.0.2.2:5000/api/";
    SharedPreferences sharedPreferences;
    private UserDao userDao;
    private AppUserDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        db = Room.databaseBuilder(getApplicationContext(), AppUserDB.class, "UserDB")
                .allowMainThreadQueries().build();

        userDao = db.userDao();
        if(userDao.getDBSize() == 0){
            return;
        }
        token = userDao.getUser().getToken();

        ImageView back = findViewById(R.id.backFromAdd);

        back.setOnClickListener(v->{
            Intent intent = new Intent(this, ChatsActivity.class);
            startActivity(intent);
        });

        EditText etCon = findViewById(R.id.etCon);
        Button addCon = findViewById(R.id.addCon);
        addCon.setOnClickListener(view -> {
            sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
            String serverUrl = sharedPreferences.getString("server", defaultServer);
            ChatAPI chatAPI = new ChatAPI(token, serverUrl);
            Call<NewChatJSON> call = chatAPI.postChat(etCon.getText().toString());
            call.enqueue(new Callback<NewChatJSON>() {
                @Override
                public void onResponse(Call<NewChatJSON> call, Response<NewChatJSON> response) {
                    if (response.isSuccessful()) {

                        Intent intent = new Intent(context, ChatsActivity.class);
                        startActivity(intent);
                    } else {
                        etCon.setError("Invalid User. Please try again");
                    }
                }

                @Override
                public void onFailure(Call<NewChatJSON> call, Throwable t) {
                }
            });

        });
    }
}
