package com.example.myapplication;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RawRes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class useService extends Service {

    UseAudio useAudio;

    @Override
    public void onCreate() {
        super.onCreate();

        useAudio = new UseAudio();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Context context = getApplicationContext();
        String channelId = "default";
        String title = context.getString(R.string.app_name);

        // 通知からActivityを起動できるようにする
        Intent notifyIntent = new Intent(this, MainActivity.class);
        // Set the Activity to start in a new, empty task
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
                        | PendingIntent.FLAG_IMMUTABLE
        );

        NotificationManager notificationManager =
                (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Notification　Channel 設定
        NotificationChannel channel = new NotificationChannel(
                channelId, title , NotificationManager.IMPORTANCE_DEFAULT);

        if(notificationManager != null){
            notificationManager.createNotificationChannel(channel);

            Notification notification = new Notification.Builder(context, channelId)
                    .setContentTitle(title)
                    // android標準アイコンから
                    .setSmallIcon(android.R.drawable.ic_media_play)
                    .setContentText("MediaPlay")
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setWhen(System.currentTimeMillis())
                    .build();

            // startForeground
            startForeground(1, notification);
            Toast.makeText(this,"成功",Toast.LENGTH_LONG).show();
            useAudio.wavPlay(useService.,R.raw.bgm2, AudioTrack.MODE_STREAM);
        }
        return START_NOT_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

        stopSelf();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
