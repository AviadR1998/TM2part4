package com.example.tm2ex3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.room.Room;

import com.example.tm2ex3.DB.AppChatDB;
import com.example.tm2ex3.DB.AppMessageDB;
import com.example.tm2ex3.DB.AppUserDB;
import com.example.tm2ex3.DB.ChatDao;
import com.example.tm2ex3.DB.MessageDao;
import com.example.tm2ex3.DB.UserDB;
import com.example.tm2ex3.DB.UserDao;
import com.example.tm2ex3.api.TokenAPI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    String myToken;

    private final String CHANNEL_ID = "1";
    Boolean isValid = false;

    String myAppToken;
    private UserDao userDao;
    private AppUserDB db;

    Context context = this;

    private final String defaultServer = "http://10.0.2.2:5000/api/";
    boolean nightMode;
    SharedPreferences sharedPreferences;

    public static final String TAG = "TreehouseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView  signUpTv = findViewById(R.id.textViewSignUp);
        signUpTv.setOnClickListener(v -> {
            Intent i = new Intent(this, RegisterActivity.class);
            startActivity(i);
        });

        EditText username = findViewById(R.id.editTextUsername);
        EditText password = findViewById(R.id.editTextPassword);
        ImageView settings = findViewById(R.id.settingsIV);

        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("night", false);
        String serverUrl = sharedPreferences.getString("server", defaultServer);

        if(nightMode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, SettingsActivity.class);
                i.putExtra("Activity", "LOGIN");
                startActivity(i);
            }
        });


        Button loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(v->{

            String userStr = username.getText().toString();
            String passwordStr = password.getText().toString();

            if(!userStr.equals("") && !passwordStr.equals("")){

                FirebaseMessaging.getInstance().getToken()
                        .addOnCompleteListener(new OnCompleteListener<String>() {
                            @Override
                            public void onComplete(@NonNull Task<String> task) {
                                if (!task.isSuccessful()) {
                                    return;
                                }

                                // Get new FCM registration token
                                myAppToken = task.getResult();

                                //Log and toast
                                String msg = "Bad Server Url";
                                TokenAPI tokenAPI = new TokenAPI(serverUrl);
                                if(tokenAPI.getRetrofit() == null){
                                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                Call<String> tokenCall = tokenAPI.postToken(userStr, passwordStr);
                                tokenCall.enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {
                                        if(response.isSuccessful()){
                                            if(response.body() != null){
                                                myToken = response.body();
                                            }
                                            isValid = true;
                                            Call<UserJSON> getUserCall = tokenAPI.getUser(userStr, "Bearer " + myToken);
                                            getUserCall.enqueue(new Callback<UserJSON>() {
                                                @Override
                                                public void onResponse(Call<UserJSON> call, Response<UserJSON> response) {
                                                    Intent i = new Intent(context, ChatsActivity.class);
                                                    db = Room.databaseBuilder(getApplicationContext(), AppUserDB.class, "UserDB")
                                                            .allowMainThreadQueries().build();

                                                    userDao = db.userDao();
                                                    userDao.deleteAllUsers();
                                                    userDao.insert(new UserDB(userStr, myToken, response.body().getDisplayName(), response.body().getProfilePic()));

                                                    AppChatDB chatDB = Room.databaseBuilder(getApplicationContext(), AppChatDB.class, "ChatDB")
                                                            .allowMainThreadQueries().build();
                                                    ChatDao chatDao = chatDB.chatDao();
                                                    chatDao.deleteAll();

                                                    AppMessageDB messageDB = Room.databaseBuilder(getApplicationContext(), AppMessageDB.class, "MessageDB")
                                                            .allowMainThreadQueries().build();
                                                    MessageDao messageDao = messageDB.MessageDao();
                                                    messageDao.deleteAllMessages();

                                                    Call<Void>  tokenCall = tokenAPI.postAndroidToken(userStr, myAppToken);
                                                    tokenCall.enqueue(new Callback<Void>() {
                                                        @Override
                                                        public void onResponse(Call<Void> call, Response<Void> response) {
                                                            startActivity(i);
                                                        }

                                                        @Override
                                                        public void onFailure(Call<Void> call, Throwable t) {
                                                            startActivity(i);
                                                        }
                                                    });
                                                }

                                                @Override
                                                public void onFailure(Call<UserJSON> call, Throwable t) {
                                                }
                                            });
                                        }else{
                                            TextView wrongDetails = findViewById(R.id.textViewWrongDetails);
                                            wrongDetails.setText(R.string.wrong_login);
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {
                                    }
                                });
                            }
                        });


            }
        });
    }

}