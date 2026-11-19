package com.nexus.grandline;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.os.Build;
import androidx.core.app.NotificationCompat;

public class ReminderReceiver extends BroadcastReceiver {
 @Override public void onReceive(Context context,Intent intent){
  if(Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())){schedule(context);return;}
  NotificationManager nm=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
  if(Build.VERSION.SDK_INT>=26)nm.createNotificationChannel(new NotificationChannel("nexus_daily","NEXUS Daily Reminder",NotificationManager.IMPORTANCE_DEFAULT));
  Intent open=new Intent(context,MainActivity.class);PendingIntent pi=PendingIntent.getActivity(context,2301,open,PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE);
  NotificationCompat.Builder b=new NotificationCompat.Builder(context,"nexus_daily").setSmallIcon(android.R.drawable.ic_dialog_info).setContentTitle("NEXUS — Daily briefing").setContentText("Review today's progress and get ready for tomorrow's schedule.").setContentIntent(pi).setAutoCancel(true).setPriority(NotificationCompat.PRIORITY_DEFAULT);
  nm.notify(2300,b.build());
 }
 static void schedule(Context context){
  AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
  Intent i=new Intent(context,ReminderReceiver.class);
  PendingIntent pi=PendingIntent.getBroadcast(context,2300,i,PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE);
  java.util.Calendar cal=java.util.Calendar.getInstance();cal.set(java.util.Calendar.HOUR_OF_DAY,23);cal.set(java.util.Calendar.MINUTE,0);cal.set(java.util.Calendar.SECOND,0);cal.set(java.util.Calendar.MILLISECOND,0);if(cal.getTimeInMillis()<=System.currentTimeMillis())cal.add(java.util.Calendar.DAY_OF_YEAR,1);
  am.setInexactRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pi);
 }
}