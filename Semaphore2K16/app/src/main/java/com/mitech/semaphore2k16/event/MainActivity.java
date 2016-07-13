package com.mitech.semaphore2k16.event;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.mitech.semaphore2k16.R;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    WebView jwebView;
    TextView jtxtcontact1, jcontact1, jtxtcontact2, jcontact2;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerLayout.openDrawer(Gravity.LEFT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Events");
        try {
            if (toolbar == null) {
                setSupportActionBar(toolbar);
            }
        } catch (Exception e) {
            e.getMessage();
        }


        jcontact1 = (TextView) findViewById(R.id.contact1);
        jtxtcontact1 = (TextView) findViewById(R.id.contact1_name);
        jcontact2 = (TextView) findViewById(R.id.contact2);
        jtxtcontact2 = (TextView) findViewById(R.id.contact2_name);

        jwebView = (WebView) findViewById(R.id.webview);
        jwebView.loadData(getString(R.string.dat), "text/html", "utf-8");
        jwebView.bringToFront();

        jcontact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setMessage("Select a way to contact");

                alertDialogBuilder.setPositiveButton("SMS", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        String s = jcontact1.getText().toString();
                        intent.setData(Uri.parse("sms:" + s));
                        startActivity(intent);
                    }
                });

                alertDialogBuilder.setNegativeButton("CALL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String s = jcontact1.getText().toString();
                        Intent i = new Intent(Intent.ACTION_CALL);
                        i.setData(Uri.parse("tel:" + s));
                        startActivity(i);
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });


        jcontact2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setMessage("Select a way to contact");

                alertDialogBuilder.setPositiveButton("SMS", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        String s = jcontact2.getText().toString();
                        intent.setData(Uri.parse("sms:" + s));
                        startActivity(intent);
                    }
                });

                alertDialogBuilder.setNegativeButton("CALL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String s = jcontact2.getText().toString();
                        Intent i = new Intent(Intent.ACTION_CALL);
                        i.setData(Uri.parse("tel:" + s));
                        startActivity(i);
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "sunil.shenoy738@gmail.com"));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Queary");
                    startActivity(intent);
                } catch (Exception e) {
                    Snackbar.make(view, "No proper email client", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_codeing) {
            jwebView.loadData(getString(R.string.coding), "text/html", "utf-8");

            jtxtcontact1.setText(getString(R.string.cod_name_1));
            jtxtcontact2.setText(getString(R.string.cod_name_2));

            jcontact1.setText(getString(R.string.cod_contact_1));
            jcontact2.setText(getString(R.string.cod_contact_2));
        } else if (id == R.id.nav_web) {
            jwebView.loadData(getString(R.string.web_design), "text/html", "utf-8");

            jtxtcontact1.setText(getString(R.string.web_name_1));
            jtxtcontact2.setText(getString(R.string.web_name_2));

            jcontact1.setText(getString(R.string.web_contact_1));
            jcontact2.setText(getString(R.string.web_contact_2));

        } else if (id == R.id.nav_app) {
            jwebView.loadData(getString(R.string.App_development), "text/html", "utf-8");

            jtxtcontact1.setText(getString(R.string.app_name_1));
            jtxtcontact2.setText(getString(R.string.app_name_2));

            jcontact1.setText(getString(R.string.app_contact_1));
            jcontact2.setText(getString(R.string.app_contact_2));
        } else if (id == R.id.nav_itmgr) {
            jwebView.loadData(getString(R.string.Best_IT_Manager), "text/html", "utf-8");

            jtxtcontact1.setText(getString(R.string.itmgr_name_1));
            jtxtcontact2.setText(getString(R.string.itmgr_name_2));

            jcontact1.setText(getString(R.string.itmgr_contact_1));
            jcontact2.setText(getString(R.string.itmgr_contact_2));
        } else if (id == R.id.nav_trhnt) {
            jwebView.loadData(getString(R.string.Treasure), "text/html", "utf-8");

            jtxtcontact1.setText(getString(R.string.treasure_name_1));
            jtxtcontact2.setText(getString(R.string.treasure_name_2));

            jcontact1.setText(getString(R.string.treasure_contact_1));
            jcontact2.setText(getString(R.string.treasure_contact_2));
        } else if (id ==R.id.nav_itdebat) {
            jwebView.loadData(getString(R.string.IT_DEBATE), "text/html", "utf-8");

            jtxtcontact1.setText(getString(R.string.itdebate_name_1));
            jtxtcontact2.setText(getString(R.string.itdebate_name_2));

            jcontact1.setText(getString(R.string.itdebate_contact_1));
            jcontact2.setText(getString(R.string.itdebate_contact_2));
        } else if (id == R.id.nav_gaming) {
            jwebView.loadData(getString(R.string.Gaming), "text/html", "utf-8");

            jtxtcontact1.setText(getString(R.string.gaming_name_1));
            jtxtcontact2.setText(getString(R.string.gaming_name_2));

            jcontact1.setText(getString(R.string.gaming_contact_1));
            jcontact2.setText(getString(R.string.gaming_contact_2));
        } else if (id == R.id.nav_madad) {
            jwebView.loadData(getString(R.string.Mad_Ad), "text/html", "utf-8");

            jtxtcontact1.setText(getString(R.string.mad_name_1));
            jtxtcontact2.setText(getString(R.string.mad_name_2));

            jcontact1.setText(getString(R.string.mad_contact_1));
            jcontact2.setText(getString(R.string.mad_contact_2));
        } else if (id == R.id.nav_img) {
            jwebView.loadData(getString(R.string.Img_video), "text/html", "utf-8");

            jtxtcontact1.setText(getString(R.string.img_name_1));
            jtxtcontact2.setText(getString(R.string.img_name_2));

            jcontact1.setText(getString(R.string.img_contact_1));
            jcontact2.setText(getString(R.string.img_contact_2));
        }else if (id == R.id.nav_paper) {
            jwebView.loadData(getString(R.string.Paper_Presentation), "text/html", "utf-8");

            jtxtcontact1.setText(getString(R.string.paper_name_1));
            jtxtcontact2.setText(getString(R.string.paper_name_2));

            jcontact1.setText(getString(R.string.paper_contact_1));
            jcontact2.setText(getString(R.string.paper_contact_2));
        } else if (id == R.id.nav_itquiz) {
            jwebView.loadData(getString(R.string.IT_Quiz), "text/html", "utf-8");

            jtxtcontact1.setText(getString(R.string.itquiz_name_1));
            jtxtcontact2.setText(getString(R.string.itquiz_name_2));

            jcontact1.setText(getString(R.string.itquiz_contact_1));
            jcontact2.setText(getString(R.string.itquiz_contact_2));
        } else if (id == R.id.nav_cultural) {
            jwebView.loadData(getString(R.string.Cultural), "text/html", "utf-8");

            jtxtcontact1.setText(getString(R.string.cultural_name_1));
            jtxtcontact2.setText(getString(R.string.cultural_name_2));

            jcontact1.setText(getString(R.string.cultural_contact_1));
            jcontact2.setText(getString(R.string.cultural_contact_2));
        } else if (id == R.id.nav_ice) {
            jwebView.loadData(getString(R.string.Ice_breaker), "text/html", "utf-8");

            jtxtcontact1.setText(getString(R.string.ice_name_1));
            jtxtcontact2.setText(getString(R.string.ice_name_2));

            jcontact1.setText(getString(R.string.ice_contact_1));
            jcontact2.setText(getString(R.string.ice_contact_2));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}