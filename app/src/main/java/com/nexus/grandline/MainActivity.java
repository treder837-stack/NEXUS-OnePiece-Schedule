package com.nexus.grandline;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
 @Override public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  Window w=getWindow(); w.setStatusBarColor(android.graphics.Color.rgb(7,27,45)); w.setNavigationBarColor(android.graphics.Color.rgb(7,27,45));
  WebView web=new WebView(this); web.setBackgroundColor(android.graphics.Color.rgb(7,27,45));
  WebSettings settings=web.getSettings(); settings.setJavaScriptEnabled(true); settings.setDomStorageEnabled(true); settings.setAllowFileAccess(true);
  web.setWebViewClient(new WebViewClient()); setContentView(web); web.loadUrl("file:///android_asset/index.html");
 }
 @Override public void onBackPressed(){ setContentView(getWindow().getDecorView()); super.onBackPressed(); }
}
