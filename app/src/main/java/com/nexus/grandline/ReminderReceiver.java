package com.nexus.grandline;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.os.Build;
import androidx.core.app.NotificationCompat;

public class ReminderReceiver extends BroadcastReceiver {
 @Override public void onReceive(Context context,Intent intent){
  NotificationManager nm=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
  if(Build.VERSION.SDK_INT>=26)nm.createNotificationChannel(new NotificationChannel("nexus_daily","NEXUS Daily Reminder",NotificationManager.IMPORTANCE_DEFAULT));
  Intent open=new Intent(context,MainActivity.class);PendingIntent pi=PendingIntent.getActivity(context,2301,open,PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE);
  Notification n=new NotificationCompat.Builder(context,"nexus_daily").setSmallIcon(android.R.drawable.ic_dialog_info).setContentTitle("NEXUS — Daily briefing").setContentText("Review today's progress and get ready for tomorrow's schedule.").setContentIntent(pi).setAutoCancel(true).setPriority(NotificationCompat.PRIORITY_DEFAULT).build();
  nm.notify(2300,n);
 }
}