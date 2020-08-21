package com.rathore.kitchen.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.rathore.kitchen.Model.BookmarkRecipesModel;
import com.rathore.kitchen.R;

//import com.archirayan.kitchen.Model.BookmarkRecipesModel;
//import com.archirayan.kitchen.R;

/**
 * Created by archi on 1/18/2017.
 */

public class ActivityWebview extends AppCompatActivity {

    public WebView browser;
    public BookmarkRecipesModel bookmarkRecipesModelIntent;
    public String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        if (getIntent().getExtras() != null) {

            url = getIntent().getExtras().getString("url");
            Toast.makeText(this, ""+url, Toast.LENGTH_SHORT).show();

        }
        browser = (WebView) findViewById(R.id.webview);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.setWebViewClient(new WebViewClient() {
            ProgressDialog progressDialog;

            //If you will not use this method url links are opeen in new brower not in webview
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            //Show loader on url load
            public void onLoadResource(WebView view, String url) {
                if (progressDialog == null) {
                    // in standard case YourActivity.this
                    progressDialog = new ProgressDialog(ActivityWebview.this);
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();
                }
            }

            public void onPageFinished(WebView view, String url) {
                try {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                        progressDialog = null;
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

        });
        browser.loadUrl(url);
//        init();
        //browser.loadUrl("http://www.tutorialspoint.com");

    }

    private void init() {


    }
}
