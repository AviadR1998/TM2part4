    package com.example.tm2ex3;

    import android.content.Context;
    import android.content.Intent;
    import android.content.SharedPreferences;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.CompoundButton;
    import android.widget.EditText;
    import android.widget.ImageView;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.appcompat.app.AppCompatDelegate;
    import androidx.appcompat.widget.SwitchCompat;

    public class SettingsActivity extends AppCompatActivity {

        SwitchCompat switcher;

        ImageView setServer;

        boolean isSwitchedManually = true;

        private String lastActivity;

        EditText editServer;

        boolean nightMode;
        SharedPreferences sharedPreferences;

        String myServer;
        SharedPreferences.Editor editor;

        Context context = this;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_settings);
            lastActivity = getIntent().getStringExtra("Activity");

            ImageView backBtn = findViewById(R.id.backBtn);

            backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    if (lastActivity.equals("LOGIN")) {
                        intent = new Intent(context, MainActivity.class);
                    } else {
                        intent = new Intent(context, RegisterActivity.class);
                    }
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(intent);
                }
            });


            switcher = findViewById(R.id.nightModeSwitch);
            setServer = findViewById(R.id.setServerIV);
            editServer = findViewById(R.id.etServer);
            sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
            nightMode = sharedPreferences.getBoolean("night", false);
            myServer = sharedPreferences.getString("server", "http://10.0.2.2:5263/api/");


            if(nightMode) {
                switcher.setChecked(true);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }

            editor = sharedPreferences.edit();

            switcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged (CompoundButton buttonView, boolean isChecked){

                    // checking if the switch is turned on
                    if (nightMode) {

                        // setting theme to night mode
                        editor.putBoolean("night", false);
                        editor.apply();
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        nightMode = false;
                        //buttonView.setText("Night Mode");
                    }

                    // if the above condition turns false
                    // it means switch is turned off
                    // by-default the switch will be off
                    else {

                        // setting theme to light theme
                        editor.putBoolean("night", true);
                        editor.apply();
                        AppCompatDelegate.setDefaultNightMode (AppCompatDelegate.MODE_NIGHT_YES);
                        nightMode = true;
                        //buttonView.setText("Light Mode");
                    }


                }
            });

            setServer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String newServer = editServer.getText().toString();
                    if(!newServer.equals("")){
                        editor.putString("server", newServer);
                    }

                    editor.apply();

                    editServer.setHint("Server has changed!");
                    editServer.setText("");
                }
            });
        }

        @Override
        protected void onNightModeChanged(int mode) {
            super.onNightModeChanged(mode);
        }

        protected void onPause() {
            super.onPause();
        }
        @Override
        protected void onStop() {
            super.onStop();
        }
        @Override
        protected void onRestart() {
            super.onRestart();
        }
        @Override
        protected void onDestroy() {
            super.onDestroy();
        }

        @Override
        protected void onStart() {
            super.onStart();
        }
        @Override
        protected void onResume() {
            super.onResume();
        }
    }