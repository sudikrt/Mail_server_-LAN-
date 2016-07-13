package com.mitech.semaphore2k16.event;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.mitech.semaphore2k16.R;

/**
 * Created by root on 23/1/16.
 */
public class Contri extends Activity{
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contributer);
        tv = (TextView) findViewById(R.id.about_content);
        tv.setText("<name> Geeky_Boy </name>" + "\n" + "<mob> 8553973266 </mob>" +
                "\n" + "\n" + "<name> Dead_Shot </name>" + "\n" + "<mob> 8105768701 </mob>");
    }
}
