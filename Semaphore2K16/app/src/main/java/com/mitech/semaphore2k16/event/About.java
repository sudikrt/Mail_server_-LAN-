package com.mitech.semaphore2k16.event;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.webkit.WebView;
import android.widget.TextView;

import com.mitech.semaphore2k16.R;

public class About extends AppCompatActivity {
    WebView jtv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        jtv = (WebView) findViewById(R.id.id_about);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        jtv.loadData(getString(R.string.about_string), "text/html", "utf-8");
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return  true;
        //return super.onSupportNavigateUp();
    }

}
