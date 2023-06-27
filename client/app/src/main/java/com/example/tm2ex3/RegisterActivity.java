package com.example.tm2ex3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.tm2ex3.api.TokenAPI;

import java.io.ByteArrayOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    int SELECT_PICTURE = 200;
    ImageView profilePicIv;

    SharedPreferences sharedPreferences;
    Context context = this;

    private final String defaultServer = "http://10.0.2.2:5000/api/";
    Boolean changeProfile = false, validPass = false, validConfPass = false, validUser = false;
    Boolean validDisplayName = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        profilePicIv = findViewById(R.id.profilePic);
        TextView signUpTv = findViewById(R.id.textViewSignIn);
        signUpTv.setOnClickListener(v -> {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });
        EditText userNameEt = findViewById(R.id.editTextUsername);
        EditText passwordEt = findViewById(R.id.editTextPassword);
        EditText confirmPassEt = findViewById(R.id.editTextConfirmPass);
        EditText displayNameEt = findViewById(R.id.editTextDisplayName);
        Button signUpBtn = findViewById(R.id.signUpBtn);

        ImageView settings = findViewById(R.id.settingsIV);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, SettingsActivity.class);
                i.putExtra("Activity", "REGISTER");
                startActivity(i);
            }
        });

        setOnFocusForEt(passwordEt, confirmPassEt);
        signUpBtn.setOnClickListener(v->{
            String user = userNameEt.getText().toString();
            String password = passwordEt.getText().toString();
            String displayName = displayNameEt.getText().toString();
            profilePicIv.buildDrawingCache();
            Bitmap bmap = profilePicIv.getDrawingCache();
            String profilePic = getEncoded64ImageStringFromBitmap(bmap);
            Drawable drawableCheck = ContextCompat.getDrawable(this, R.drawable.ic_check);
            Drawable drawableCancel = ContextCompat.getDrawable(this, R.drawable.ic_close);
            if (isValidUsername(user)) {
                validUser = true;
                // Drawable is currently invisible, make it visible
                userNameEt.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        userNameEt.getCompoundDrawablesRelative()[0],
                        userNameEt.getCompoundDrawablesRelative()[1],
                        drawableCheck,
                        userNameEt.getCompoundDrawablesRelative()[3]
                );
            }else{
                validUser = false;
                userNameEt.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        userNameEt.getCompoundDrawablesRelative()[0],
                        userNameEt.getCompoundDrawablesRelative()[1],
                        drawableCancel,
                        userNameEt.getCompoundDrawablesRelative()[3]
                );
            }
            if(isValidPass(password)){
                validPass = true;
                passwordEt.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        passwordEt.getCompoundDrawablesRelative()[0],
                        passwordEt.getCompoundDrawablesRelative()[1],
                        drawableCheck,
                        passwordEt.getCompoundDrawablesRelative()[3]
                );
            } else{
                validPass = false;
                passwordEt.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        passwordEt.getCompoundDrawablesRelative()[0],
                        passwordEt.getCompoundDrawablesRelative()[1],
                        drawableCancel,
                        passwordEt.getCompoundDrawablesRelative()[3]
                );
            }
            if(validPass && confirmPassEt.getText().toString().equals(passwordEt.getText().toString())){
                validConfPass = true;
                confirmPassEt.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        confirmPassEt.getCompoundDrawablesRelative()[0],
                        confirmPassEt.getCompoundDrawablesRelative()[1],
                        drawableCheck,
                        confirmPassEt.getCompoundDrawablesRelative()[3]
                );
            } else{
                validConfPass = false;
                confirmPassEt.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        confirmPassEt.getCompoundDrawablesRelative()[0],
                        confirmPassEt.getCompoundDrawablesRelative()[1],
                        drawableCancel,
                        confirmPassEt.getCompoundDrawablesRelative()[3]
                );
            }
            if (isValidDisName(displayName)) {
                validDisplayName = true;
                // Drawable is currently invisible, make it visible
                displayNameEt.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        displayNameEt.getCompoundDrawablesRelative()[0],
                        displayNameEt.getCompoundDrawablesRelative()[1],
                        drawableCheck,
                        displayNameEt.getCompoundDrawablesRelative()[3]
                );
            }else{
                validDisplayName = false;
                displayNameEt.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        displayNameEt.getCompoundDrawablesRelative()[0],
                        displayNameEt.getCompoundDrawablesRelative()[1],
                        drawableCancel,
                        displayNameEt.getCompoundDrawablesRelative()[3]
                );
            }
            TextView changeProfileTV = findViewById(R.id.textViewChangeProfile);
            if(!changeProfile){
                changeProfileTV.setText(R.string.change_profile);
            } else{
                changeProfileTV.setText("");
            }

            if(validUser && validPass && validConfPass && validDisplayName && changeProfile){
                sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
                String serverUrl = sharedPreferences.getString("server", defaultServer);
                TokenAPI tokenAPI = new TokenAPI(serverUrl);
                Call<Void> call = tokenAPI.createUser(user, password, displayName, profilePic);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                                                if(response.code() != 200){
                            validUser = false;
                            userNameEt.setCompoundDrawablesRelativeWithIntrinsicBounds(
                                    userNameEt.getCompoundDrawablesRelative()[0],
                                    userNameEt.getCompoundDrawablesRelative()[1],
                                    drawableCancel,
                                    userNameEt.getCompoundDrawablesRelative()[3]
                            );
                            userNameEt.setText("");
                            userNameEt.setHint("This username already exist");
                        } else{
                            Intent i = new Intent(context, MainActivity.class);
                            startActivity(i);
                        }

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });

        profilePicIv.setOnClickListener(v->{
            imageChooser();
        });
    }

    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    profilePicIv.setImageURI(selectedImageUri);
                    changeProfile = true;
                }
            }
        }
    }

    private boolean isValidUsername(String user){
        Pattern pUsername = Pattern.compile("^[A-Za-z0-9]*$");
        Matcher mUsername = pUsername.matcher(user);
        return mUsername.matches() && !user.equals("");
    }

    public String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        byte[] byteFormat = stream.toByteArray();
        // get the base 64 string
        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
        return imgString;
    }

    private boolean isValidPass(String pass){
        Pattern pNoSigns = Pattern.compile("^[A-Za-z0-9]*$");
        Pattern pCapital = Pattern.compile(".*[A-Z].*");
        Pattern pDigit = Pattern.compile(".*[0-9].*");
        Matcher mCapital = pCapital.matcher(pass);
        Matcher mDigit = pDigit.matcher(pass);
        Matcher mNoSigns = pNoSigns.matcher(pass);
        int passLen = pass.length();
        return passLen >= 8 && passLen <= 16 && mCapital.matches() && mDigit.matches() && mNoSigns.matches();
    }

    private boolean isValidDisName(String user){
        Pattern pDisName = Pattern.compile("^[A-Za-z0-9]*$");
        Matcher mDisName = pDisName.matcher(user);
        int userLen = user.length();
        return userLen >= 1 && userLen <= 8 && mDisName.matches();
    }
    private void setOnFocusForEt(EditText password, EditText confPass){
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    password.setHint("8-16, 1 cap letter & 1 digit at least");
                else
                    password.setHint("Password");
            }
        });

        confPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    confPass.setHint("Must match the previous");
                else
                    confPass.setHint("Confirm Password");
            }
        });
    }
}