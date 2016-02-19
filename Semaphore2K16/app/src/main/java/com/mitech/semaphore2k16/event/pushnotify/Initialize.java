package com.mitech.semaphore2k16.event.pushnotify;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by root on 10/1/16.
 */
public class Initialize extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "djT5mdZHITJB2YU7BcmZF8i4oOXRYaxYHTR1wTtN", "ZyMsppRP6pzvnIxhMA61CxhZNaS3IXS0pg6fdqkl");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
