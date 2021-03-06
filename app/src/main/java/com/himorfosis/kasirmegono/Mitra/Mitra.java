package com.himorfosis.kasirmegono.Mitra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.himorfosis.kasirmegono.Database;
import com.himorfosis.kasirmegono.Kasir.BeliClassData;
import com.himorfosis.kasirmegono.profil.Profil;
import com.himorfosis.kasirmegono.Pemesanan.TabPemesanan;
import com.himorfosis.kasirmegono.Login;
import com.himorfosis.kasirmegono.R;
import com.himorfosis.kasirmegono.Sumber;

import java.util.ArrayList;
import java.util.List;

public class Mitra extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Fragment fragment;
    FragmentTransaction ft;

    TextView email, user;

    Database db;
    List<BeliClassData> databeli = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mitra);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);

        String getemail = Sumber.getData("akun", "email", getApplicationContext());
        String getuser = Sumber.getData("akun", "user", getApplicationContext());

        email = headerView.findViewById(R.id.emailuser);
        user = headerView.findViewById(R.id.namauser);

        email.setText(getemail);
        user.setText(getuser);

        if (navigationView != null) {

            Menu menu = navigationView.getMenu();
            onNavigationItemSelected(menu.getItem(0));

        }

        db = new Database(Mitra.this);

        databeli = db.getBeli();

        Sumber.deleteData("tagihan", getApplicationContext());

        if (databeli.isEmpty()) {

            Log.e("data ", "kosong");

        } else {

            Log.e("data ", "dihapus");

            db.delAllData("tabelbeli");
            Sumber.deleteData("pemesanan", getApplicationContext());

        }


    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;

        finishAffinity();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mitra, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {

            case R.id.produk:

                fragment = new TabPemesanan();

                break;

            case R.id.riwayat:
                fragment = new Riwayat();

                break;

            case R.id.reward:
                fragment = new Reward();

                break;

            case R.id.profil:
                fragment = new Profil();

                break;

            case R.id.logout:
                Sumber.deleteData("akun", getApplicationContext());
                Intent in = new Intent(Mitra.this, Login.class);
                startActivity(in);
                break;

        }

        if (fragment != null) {

            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame, fragment);
            ft.commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
