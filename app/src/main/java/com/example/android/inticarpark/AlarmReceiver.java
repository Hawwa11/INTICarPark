package com.example.android.inticarpark;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;

//implements OnMapReadyCallback, TaskLoadedCallback

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            NotificationHelper notificationHelper = new NotificationHelper(context);
//            NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
//            notificationHelper.getManager().notify(1, nb.build());
        }

//        int notificationId = intent.getIntExtra("notificationId", 0);
//        String message = intent.getStringExtra("todo");
//
//
//        Intent mainIntent = new Intent(context, ReminderSettings.class);
//        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, mainIntent, 0);
//
//        NotificationManager myNotificationManager =
//                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//
//        // Prepare notification.
//        Notification.Builder builder = new Notification.Builder(context);
//        builder.setSmallIcon(android.R.drawable.ic_dialog_info)
//                .setContentTitle("REMINDER!")
//                .setContentText(message)
//                .setWhen(System.currentTimeMillis())
//                .setAutoCancel(true)
//                .setContentIntent(contentIntent)
//                .setPriority(Notification.PRIORITY_MAX)
//                .setDefaults(Notification.DEFAULT_ALL);
//
//        // Notify
//        myNotificationManager.notify(notificationId, builder.build());

    }
//}