package com.rdstudio.kantinpos;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.rdstudio.kantinpos.dataroom.Setoran;
import com.rdstudio.kantinpos.model.SetoranModel;
import com.rdstudio.kantinpos.utils.Tools;

import java.text.MessageFormat;

public class UbahAnggota extends AppCompatActivity {

    private View parent_view;
    EditText btn_nama_anggota_baru, et_nama_setoran_1, et_hpp_1, et_harga_jual_1;
    EditText et_nama_setoran_2, et_hpp_2, et_harga_jual_2;
    EditText et_nama_setoran_3, et_hpp_3, et_harga_jual_3;
    private SetoranModel setoranModel;
    private Setoran setoran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_anggota);
        parent_view = findViewById(R.id.parent_view);
        btn_nama_anggota_baru = findViewById(R.id.btn_nama_anggota_baru);
        et_nama_setoran_1 = findViewById(R.id.et_nama_setoran_1);
        et_hpp_1 = findViewById(R.id.et_hpp_1);
        et_harga_jual_1 = findViewById(R.id.et_harga_jual_1);
        et_nama_setoran_2 = findViewById(R.id.et_nama_setoran_2);
        et_hpp_2 = findViewById(R.id.et_hpp_2);
        et_harga_jual_2 = findViewById(R.id.et_harga_jual_2);
        et_nama_setoran_3 = findViewById(R.id.et_nama_setoran_3);
        et_hpp_3 = findViewById(R.id.et_hpp_3);
        et_harga_jual_3 = findViewById(R.id.et_harga_jual_3);
        initToolbar();
        if (getIntent().getParcelableExtra("setoran") != null) {
            setoran = getIntent().getParcelableExtra("setoran");
            if (!setoran.getNama().isEmpty()) {
                btn_nama_anggota_baru.setText(setoran.getNama());
                et_nama_setoran_1.setText(setoran.getBarang1());
                et_hpp_1.setText(MessageFormat.format("{0}", setoran.getHarga_beli1()));
                et_harga_jual_1.setText(MessageFormat.format("{0}", setoran.getHarga_jual1()));
            }
            if (!(setoran.getBarang2() == null)) {
                et_nama_setoran_2.setText(setoran.getBarang2());
                et_hpp_2.setText(MessageFormat.format("{0}", setoran.getHarga_beli2()));
                et_harga_jual_2.setText(MessageFormat.format("{0}", setoran.getHarga_jual2()));
            }
            if (!(setoran.getBarang3() == null)) {
                et_nama_setoran_3.setText(setoran.getBarang3());
                et_hpp_3.setText(MessageFormat.format("{0}", setoran.getHarga_beli3()));
                et_harga_jual_3.setText(MessageFormat.format("{0}", setoran.getHarga_jual3()));
            }
        }
        setoranModel = new ViewModelProvider(this).get(SetoranModel.class);
        setoranModel.getmAll().observe(this, setorans -> Log.e("onCreate: ", String.valueOf(setorans.size())));
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_close);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.grey_60), PorterDuff.Mode.SRC_ATOP);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
        Tools.setSystemBarLight(this);
    }

    // Top menu simpan
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_simpan, menu);
        Tools.changeMenuIconColor(menu, getResources().getColor(R.color.grey_60));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
            // Dialog menu simpan
            showSimpanAnggotaBaru();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSimpanAnggotaBaru() {
        insert();
        finish();
    }

    void insert() {
        if (et_nama_setoran_2.getText().toString().isEmpty() || et_hpp_2.getText().toString().isEmpty() || et_harga_jual_2.getText().toString().isEmpty()) {
            setoranModel.update(btn_nama_anggota_baru.getText().toString(),et_nama_setoran_1.getText().toString(), Integer.parseInt(et_hpp_1.getText().toString()), Integer.parseInt(et_harga_jual_1.getText().toString()), "", 0, 0, "", 0, 0);
        } else if (et_nama_setoran_3.getText().toString().isEmpty() || et_hpp_3.getText().toString().isEmpty() || et_harga_jual_3.getText().toString().isEmpty()) {
            setoranModel.update(btn_nama_anggota_baru.getText().toString(),et_nama_setoran_1.getText().toString(), Integer.parseInt(et_hpp_1.getText().toString()), Integer.parseInt(et_harga_jual_1.getText().toString()), et_nama_setoran_2.getText().toString(), Integer.parseInt(et_hpp_2.getText().toString()), Integer.parseInt(et_harga_jual_2.getText().toString()), "", 0, 0);
        } else {
            setoranModel.update(btn_nama_anggota_baru.getText().toString(),et_nama_setoran_1.getText().toString(), Integer.parseInt(et_hpp_1.getText().toString()), Integer.parseInt(et_harga_jual_1.getText().toString()), et_nama_setoran_2.getText().toString(), Integer.parseInt(et_hpp_2.getText().toString()), Integer.parseInt(et_harga_jual_2.getText().toString()), et_nama_setoran_3.getText().toString(), Integer.parseInt(et_hpp_3.getText().toString()), Integer.parseInt(et_harga_jual_3.getText().toString()));
        }
    }

}
