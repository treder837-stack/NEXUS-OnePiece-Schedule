package com.nexus.grandline;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
 private WebView web;
 @Override public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  getWindow().setStatusBarColor(android.graphics.Color.rgb(7,27,45));
  getWindow().setNavigationBarColor(android.graphics.Color.rgb(7,27,45));
  web=new WebView(this);
  web.setBackgroundColor(android.graphics.Color.rgb(7,27,45));
  WebSettings settings=web.getSettings();
  settings.setJavaScriptEnabled(true);
  settings.setDomStorageEnabled(true);
  settings.setAllowFileAccess(true);
  web.setWebViewClient(new WebViewClient());
  setContentView(web);
  web.loadUrl("file:///android_asset/index.html");
 }
 @Override public void onBackPressed() {
  if (web != null && web.canGoBack()) web.goBack();
  else super.onBackPressed();
 }
}
