package com.example.tm2ex3;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class FirebaseService extends FirebaseMessagingService {

    private LocalBroadcastManager broadcaster;
    private final String CHANNEL_ID = "1";
    private int notificationId = 1;

    public FirebaseService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        broadcaster = LocalBroadcastManager.getInstance(this);
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.i("Token: ", token);
    }


    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        createNotificationChannel();
        Map<String, String> myDataMessage = message.getData();
        String title = message.getNotification().getTitle();
        String date = myDataMessage.get("fullDate"), chatId = myDataMessage.get("chatId"), senderUsername = myDataMessage.get("username"), check = myDataMessage.get("check");
        //String[] splitTitle = message.getNotification().getTitle().toString().split("@", 3);
        /*String[] splitTitle = {"", "", ""};
        int place = 0;
        for (int i = 0; i < 3; i++) {
            for (; place < fullTitle.length() && (i == 2 || fullTitle.charAt(place) != '$'); place++) {
                splitTitle[i] += fullTitle.charAt(place);
            }
            place++;
        }*/
        if (check.equals("0")) {
            System.out.println(message.getNotification().getTitle());
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "1")
                    .setSmallIcon(R.drawable.notifications_active)
                    .setContentTitle(title)
                    .setContentText(message.getNotification().getBody())
                    .setStyle(new NotificationCompat.BigTextStyle())
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

// notificationId is a unique int for each notification that you must define
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

                notificationManager.notify(notificationId++, builder.build());
            }

        }

        Intent intent = new Intent("MyData");
        intent.putExtra("message", message.getNotification().getBody());
        intent.putExtra("sendChatId", chatId);
        intent.putExtra("sendDate", date);
        intent.putExtra("senderUsername", senderUsername);
        broadcaster.sendBroadcast(intent);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        /*if (ContextCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
        } else {
            requestPermission();
        }*/
    }

}