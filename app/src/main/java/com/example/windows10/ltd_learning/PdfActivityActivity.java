package com.example.windows10.ltd_learning;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class PdfActivityActivity extends AppCompatActivity {
    private WebView  wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_activity);
       // String doc="<iframe src='http://docs.google.com/viewer?url=http://www.iasted.org/conferences/formatting/presentations-tips.ppt&embedded=true'width='100%' height='100%' style='border: none;'></iframe>";
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        wv = (WebView)findViewById(R.id.webView);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setPluginState(WebSettings.PluginState.ON);
        wv.getSettings().setAllowFileAccess(true);
        wv.loadUrl("https://docs.google.com/gview?embedded=true&url="+url);
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                wv.loadUrl("javascript:(function() { " +
                        "document.querySelector('[role=\"toolbar\"]').remove();})()");
            }
        });
    }
}
