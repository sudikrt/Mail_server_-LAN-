package com.mitech.semaphore2k16.event;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.TextView;

import com.mitech.semaphore2k16.R;

/**
 * Created by root on 7/2/16.
 */
public class Coordi extends AppCompatActivity{
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coordinator);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        webView = (WebView) findViewById(R.id.id_about);

        webView.loadData(getString(R.string.dept_info), "text/html", "utf-8");
    }
    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return  true;
        //return super.onSupportNavigateUp();
    }
}
