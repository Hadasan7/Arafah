package com.rdstudio.kantinpos;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rdstudio.kantinpos.utils.Tools;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initMainContent();

    }

    // Toolbar
    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_layers);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Kantin Ta'awun");
        Tools.setSystemBarColor(this);
    }

    // top menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!(bottomNav.getSelectedItemId() == R.id.nav_hitung)) {
            getMenuInflater().inflate(R.menu.menu_top, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // menu click listener
        if (item.getItemId() == R.id.action_laporan) {
            //Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, HistoryLaporanActivity.class);
            startActivity(intent);

        } else if (item.getItemId() == R.id.action_about) {
            //Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
            showDialogAbout();
        }

        return true;
    }

    // MainContent
    private void initMainContent() {

        // load default fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SetoranFragment setoranFragment = new SetoranFragment();
        fragmentTransaction.add(R.id.frame_container, setoranFragment, SetoranFragment.class.getSimpleName());
        fragmentTransaction.commit();

        // Bottom Menu
        bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(item -> {
            FragmentManager fragmentManager1 = getSupportFragmentManager();
            final FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();

            if (item.getItemId() == R.id.nav_setoran) {
//                    Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                SetoranFragment setoranFragment1 = new SetoranFragment();
                fragmentTransaction1.add(R.id.frame_container, setoranFragment1, SetoranFragment.class.getSimpleName());
            } else if (item.getItemId() == R.id.nav_hitung) {
//                    Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                HitungFragment hitungFragment = new HitungFragment();
                fragmentTransaction1.add(R.id.frame_container, hitungFragment, HitungFragment.class.getSimpleName());
            } else if (item.getItemId() == R.id.nav_laporan) {
//                    Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                LaporanFragment laporanFragment = new LaporanFragment();
                fragmentTransaction1.add(R.id.frame_container, laporanFragment, LaporanFragment.class.getSimpleName());
            } else if (item.getItemId() == R.id.nav_anggota) {
//                    Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                AnggotaFragment anggotaFragment = new AnggotaFragment();
                fragmentTransaction1.add(R.id.frame_container, anggotaFragment, AnggotaFragment.class.getSimpleName());
            }

            fragmentTransaction1.commit();
            return true;
        });

    }

    // top menu about dialog
    private void showDialogAbout() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_about);
        dialog.setCancelable(true);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;

        ((TextView) dialog.findViewById(R.id.tv_version)).setText(String.format("%s%s ", getResources().getString(R.string.version), BuildConfig.VERSION_NAME));

        dialog.findViewById(R.id.btn_top_close).setOnClickListener(view -> dialog.dismiss());

        dialog.findViewById(R.id.btn_bottom_dialog_close).setOnClickListener(view -> dialog.dismiss());

        dialog.findViewById(R.id.tv_bottom_dialog_close).setOnClickListener(view -> dialog.dismiss());

        dialog.show();
        dialog.getWindow().setAttributes(layoutParams);

    }

}
