package com.mitech.semaphore2k16.event;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.mitech.semaphore2k16.R;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by root on 6/2/16.
 */
public class Schedule extends AppCompatActivity {
    TableLayout tableLayout;
    int color ;

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    /**
     * Generate a value suitable for use in {@link #setId(int)}.
     * This value will not collide with ID values generated at build time by aapt for R.id.
     *
     * @return a generated ID value
     */
    public static int generateViewId() {
        for (;;) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tableLayout = (TableLayout) findViewById(R.id.tbl);
        tableLayout.setPadding(15, 15, 25, 15);
        TableRow tr_dayone = new TableRow(this);
        tr_dayone.setId(generateViewId());
        tr_dayone.setBackgroundColor(Color.WHITE);
        tr_dayone.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        TextView id1 = new TextView(this);
        id1.setId(generateViewId());
        id1.setText("Day 1");
        id1.setTextSize(20);
        id1.setTextColor(Color.RED);
        id1.setPadding(15, 15, 25, 15);
        tr_dayone.addView(id1);

        TextView id2 = new TextView(this);
        id2.setId(generateViewId());
        id2.setText("");
        id2.setTextColor(Color.WHITE);
        id2.setPadding(15, 15, 25, 15);
        tr_dayone.addView(id2);

        tableLayout.addView(tr_dayone, new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


        TableRow tr_head = new TableRow(this);
        tr_head.setId(generateViewId());
        tr_head.setBackgroundColor(getResources().getColor(R.color.txtblugray));
        tr_head.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


        TextView id = new TextView(this);
        id.setId(generateViewId());
        id.setText("Event_name");
        id.setTextColor(Color.WHITE);
        id.setPadding(15, 15, 25, 15);
        tr_head.addView(id);

        TextView date = new TextView(this);
        date.setId(generateViewId());
        date.setText("Venue");
        date.setTextColor(Color.WHITE);
        date.setPadding(15, 15, 25, 15);
        tr_head.addView(date);

        TextView name = new TextView(this);
        name.setId(generateViewId());
        name.setText("Timings");
        name.setTextColor(Color.WHITE);
        name.setPadding(15, 15, 25, 15);
        tr_head.addView(name);

        tableLayout.addView(tr_head, new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        for (int i = 0; i < 34; i++){

            String e_name = "e_name" + String.valueOf(i);
            String e_venu = "e_venu" + String.valueOf(i);
            String e_time = "e_time" + String.valueOf(i);


            int e_n = getResources().getIdentifier(e_name,
                    "string", this.getPackageName());
            int e_v = getResources().getIdentifier(e_venu,
                    "string", this.getPackageName());
            int e_t = getResources().getIdentifier(e_time,
                    "string", this.getPackageName());
            if (i % 2 == 0) {
                color = getResources().getColor(R.color.ligblue);
            }
            else {
                color = Color.WHITE;
            }
            TableRow tr_head_in = new TableRow(this);
            tr_head_in.setId(generateViewId());
            tr_head_in.setBackgroundColor(color);
            tr_head_in.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


            TextView id_in = new TextView(this);
            id_in.setId(generateViewId());
            id_in.setText(getString(e_n));
            id_in.setTextColor(Color.BLACK);
            id_in.setPadding(15, 15, 25, 15);
            tr_head_in.addView(id_in);

            TextView date_in = new TextView(this);
            date_in.setId(generateViewId());
            date_in.setText(getString(e_v));
            date_in.setTextColor(Color.BLACK);
            date_in.setPadding(10, 15, 25, 15);
            tr_head_in.addView(date_in);

            TextView name_in = new TextView(this);
            name_in.setId(generateViewId());
            name_in.setText(getString(e_t));
            name_in.setTextColor(Color.BLACK);
            name_in.setPadding(15, 15, 25, 15);
            tr_head_in.addView(name_in);

            tableLayout.addView(tr_head_in, new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            if (i == 18) {
                TableRow tr_dayo = new TableRow(this);
                tr_dayo.setId(generateViewId());
                tr_dayo.setBackgroundColor(Color.WHITE);
                tr_dayo.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                TextView id4 = new TextView(this);
                id4.setId(generateViewId());
                id4.setText("");
                id4.setTextColor(Color.WHITE);
                id4.setPadding(15, 15, 25, 15);
                tr_dayo.addView(id4);

                tableLayout.addView(tr_dayo, new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


                TableRow tr_dayo00 = new TableRow(this);
                tr_dayo00.setId(generateViewId());
                tr_dayo00.setBackgroundColor(Color.WHITE);
                tr_dayo00.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


                TextView id3 = new TextView(this);
                id3.setId(generateViewId());
                id3.setText("Day 2");
                id3.setTextSize(20);
                id3.setTextColor(Color.RED);
                id3.setPadding(15, 15, 25, 15);
                tr_dayo00.addView(id3);

                tableLayout.addView(tr_dayo00, new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                TableRow tr_head0 = new TableRow(this);
                tr_head0.setId(generateViewId());
                tr_head0.setBackgroundColor(getResources().getColor(R.color.txtblugray));
                tr_head0.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


                TextView id0 = new TextView(this);
                id0.setId(generateViewId());
                id0.setText("Event_name");
                id0.setTextColor(Color.WHITE);
                id0.setPadding(15, 15, 25, 15);
                tr_head0.addView(id0);

                TextView date0 = new TextView(this);
                date0.setId(generateViewId());
                date0.setText("Venue");
                date0.setTextColor(Color.WHITE);
                date0.setPadding(15, 15, 25, 15);
                tr_head0.addView(date0);

                TextView name0 = new TextView(this);
                name0.setId(generateViewId());
                name0.setText("Timings");
                name0.setTextColor(Color.WHITE);
                name0.setPadding(15, 15, 25, 15);
                tr_head0.addView(name0);

                tableLayout.addView(tr_head0, new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


            }
        }

    }


}