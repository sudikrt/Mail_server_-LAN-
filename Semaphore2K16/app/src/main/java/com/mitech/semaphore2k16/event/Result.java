package com.mitech.semaphore2k16.event;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mitech.semaphore2k16.R;

/**
 * Created by root on 2/1/16.
 */
public class Result extends  AppCompatActivity{

    WebView JWeb_result;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressDialog = ProgressDialog.show(Result.this,"Loading","Wait For a while..");

        JWeb_result = (WebView) findViewById(R.id.web_result);
        JWeb_result.getSettings().setJavaScriptEnabled(true);
        JWeb_result.loadUrl("http://nmamit.in/semaphore2k16/results.php");

        JWeb_result.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            // when finish loading page
            public void onPageFinished(WebView view, String url) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return  true;
        //return super.onSupportNavigateUp();
    }
}
