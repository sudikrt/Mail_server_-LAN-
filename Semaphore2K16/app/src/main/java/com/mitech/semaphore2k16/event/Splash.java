package com.mitech.semaphore2k16.event;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mitech.semaphore2k16.R;

/**
 * Created by root on 22/1/16.
 */
public class Splash extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Thread t=new Thread(){
            public void run()
            {
                try{
                    sleep(3000);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    Class c = null;
                    try {
                        c = Class.forName("com.mitech.semaphore2k16.event.Welcome");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    Intent i = new Intent(getApplicationContext(),c);
                    startActivity(i);
                    finish();
                }
            }
        };
        t.start();
    }
}
