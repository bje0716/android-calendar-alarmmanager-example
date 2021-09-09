package com.grapefruit.alarm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

    private NotificationManager manager;
    private NotificationCompat.Builder builder;

    @Override
    public void onReceive(Context context, Intent intent) {
        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(new NotificationChannel("channel1", "channel1", NotificationManager.IMPORTANCE_DEFAULT));
            builder = new NotificationCompat.Builder(context, "channel1");
        } else {
            builder = new NotificationCompat.Builder(context);
        }

        Intent intent1 = new Intent(context, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, 101, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentTitle("alarm");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setAutoCancel(true);
        builder.setContentIntent(pIntent);

        Notification notification = builder.build();
        manager.notify(1, notification);
    }
}
