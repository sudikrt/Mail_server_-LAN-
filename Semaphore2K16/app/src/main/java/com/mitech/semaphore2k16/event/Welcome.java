package com.mitech.semaphore2k16.event;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mitech.semaphore2k16.R;


public class Welcome extends AppCompatActivity {

    ImageButton  Jbtn_event, Jbtn_Schedule, Jbtn_result, Jbtn_reg, Jbtn_visit;
    ImageButton Jbtn_about;
    CoordinatorLayout Jc_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_welcome);

        getSupportActionBar().setLogo(R.mipmap.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Jc_layout = (CoordinatorLayout) findViewById(R.id.co);

        Jbtn_about = (ImageButton) findViewById(R.id.btn_about);
        Jbtn_event = (ImageButton) findViewById(R.id.btn_event);
        Jbtn_result = (ImageButton) findViewById(R.id.btn_result);
        Jbtn_Schedule = (ImageButton) findViewById(R.id.schedule_btn);
        Jbtn_reg = (ImageButton) findViewById(R.id.btn_reg);
       // Jbtn_visit = (ImageButton) findViewById(R.id.btn_visit);

        Jbtn_Schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Welcome.this, Schedule.class));
            }
        });
        /* About Page */
        Jbtn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Welcome.this, About.class));
            }
        });

        /*Event Page*/
        Jbtn_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Welcome.this, MainActivity.class));
            }
        });

        /* result Page */
        Jbtn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (checkInternetConnection()) {
                    startActivity(new Intent(Welcome.this, Result.class));
                }
                else
                {
                    Snackbar snackbar = Snackbar.make(v,"Sorry, check your network Connection", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }*/

                if (checkInternetConnection()) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://nmamit.in/semaphore2k16/results.php"));
                    try {
                        startActivity(intent);
                    } catch (Exception e) {
                        e.getMessage();
                    }
                }
                else
                {
                    Snackbar snackbar = Snackbar.make(v,"Sorry, check your network Connection", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });

        /* Regestration Page */
        Jbtn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInternetConnection()) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://nmamit.in/semaphore2k16/registration.php"));
                    try {
                        startActivity(intent);
                    } catch (Exception e) {
                        e.getMessage();
                    }
                }
                else
                {
                    Snackbar snackbar = Snackbar.make(v,"Sorry, check your network Connection", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });

    }
    public void openCoordi (MenuItem menuItem) {
        startActivity(new Intent(Welcome.this, Coordi.class));
    }
    public void openContri (MenuItem menuItem) {
        startActivity(new Intent(Welcome.this, Contri.class));
    }
    public void openGallery (MenuItem menuItem) {
        if (checkInternetConnection()) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://nmamit.in/semaphore2k16/gallery.php"));
            try {
                startActivity(intent);
            } catch (Exception e) {
                e.getMessage();
            }
        }
        else {
            //Toast.makeText(getApplicationContext(),"Sorry, check your network Connection", Toast.LENGTH_LONG).show();
            Snackbar snackbar = Snackbar.make(getWindow().getDecorView(),"Sorry, check your network Connection", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main,menu);
        return  true;
    }

    public boolean checkInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) Welcome.this.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected()) {
            return true;

        } else {
            return false;
        }
    }
}
