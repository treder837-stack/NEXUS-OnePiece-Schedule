package com.nexus.grandline;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;

public class MainActivity extends Activity {
 private WebView web;
 private static final int REQ_NOTIFICATIONS=41;
 @Override public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  getWindow().setStatusBarColor(android.graphics.Color.rgb(8,27,45));
  getWindow().setNavigationBarColor(android.graphics.Color.rgb(8,27,45));
  createChannel();
  web=new WebView(this); web.setBackgroundColor(android.graphics.Color.rgb(8,27,45));
  WebSettings settings=web.getSettings(); settings.setJavaScriptEnabled(true); settings.setDomStorageEnabled(true); settings.setAllowFileAccess(true);
  web.setWebViewClient(new WebViewClient()); setContentView(web); web.loadUrl("file:///android_asset/index.html");
  if (Build.VERSION.SDK_INT >= 33 && checkSelfPermission("android.permission.POST_NOTIFICATIONS") != PackageManager.PERMISSION_GRANTED) requestPermissions(new String[]{"android.permission.POST_NOTIFICATIONS"},REQ_NOTIFICATIONS);
  else scheduleDailyReminder();
 }
 private void createChannel(){
  if(Build.VERSION.SDK_INT>=26){NotificationChannel c=new NotificationChannel("nexus_daily","NEXUS Daily Reminder",NotificationManager.IMPORTANCE_DEFAULT);c.setDescription("Daily schedule and preparation reminder");getSystemService(NotificationManager.class).createNotificationChannel(c);}
 }
 private void scheduleDailyReminder(){
  AlarmManager am=(AlarmManager)getSystemService(ALARM_SERVICE);
  Intent i=new Intent(this,ReminderReceiver.class);
  PendingIntent pi=PendingIntent.getBroadcast(this,2300,i,PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE);
  java.util.Calendar cal=java.util.Calendar.getInstance();cal.setTimeInMillis(System.currentTimeMillis());cal.set(java.util.Calendar.HOUR_OF_DAY,23);cal.set(java.util.Calendar.MINUTE,0);cal.set(java.util.Calendar.SECOND,0);if(cal.getTimeInMillis()<=System.currentTimeMillis())cal.add(java.util.Calendar.DAY_OF_YEAR,1);
  am.setInexactRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pi);
 }
 @Override public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] results){super.onRequestPermissionsResult(requestCode,permissions,results);if(requestCode==REQ_NOTIFICATIONS&&results.length>0&&results[0]==PackageManager.PERMISSION_GRANTED)scheduleDailyReminder();}
 @Override public void onBackPressed(){if(web!=null&&web.canGoBack())web.goBack();else super.onBackPressed();}
}