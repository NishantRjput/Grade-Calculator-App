package com.example.calculatorsgpacgpapercentage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        WebView wv=(WebView)findViewById(R.id.webView);

        WebSettings ws=wv.getSettings();
        ws.setBuiltInZoomControls(true);
        wv.getSettings().setPluginState(WebSettings.PluginState.ON);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setLoadWithOverviewMode(true);
        wv.getSettings().setUseWideViewPort(true);
        wv.getSettings().setLoadsImagesAutomatically(true);
        wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wv.setWebViewClient(new Callback());
        wv.loadUrl("http://bputexam.in/studentsection/resultpublished/studentresult.aspx");

    }
    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            /*return(false);*/
            view.loadUrl(url);
            view.addJavascriptInterface(new Object() {
                @JavascriptInterface
                public void performClick() throws Exception {
                    Log.d("LOGIN::", "Clicked");

                }
            }, "login");
            return true;
        }
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub

            System.out.println("started");

            super.onPageStarted(view, url, favicon);

        }
        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub

            System.out.println("ends");
            super.onPageFinished(view, url);

        }
    }


}