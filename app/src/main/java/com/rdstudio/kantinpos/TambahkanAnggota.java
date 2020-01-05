package com.rdstudio.kantinpos;

import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.rdstudio.kantinpos.dataroom.Setoran;
import com.rdstudio.kantinpos.model.SetoranModel;
import com.rdstudio.kantinpos.utils.Tools;

public class TambahkanAnggota extends AppCompatActivity {

    private View parent_view;
    EditText btn_nama_anggota_baru, et_nama_setoran_1, et_hpp_1, et_harga_jual_1;
    EditText et_nama_setoran_2, et_hpp_2, et_harga_jual_2;
    EditText et_nama_setoran_3, et_hpp_3, et_harga_jual_3;
    private SetoranModel setoranModel;
    private Setoran setoran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahkan_anggota);
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
            // Dialog menu simpan
            insert();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSimpanAnggotaBaru() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Data Tersimpan");
        builder.setMessage(R.string.confirm_anggota);
        builder.setPositiveButton(R.string.ya, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                btn_nama_anggota_baru.setText("");
                et_nama_setoran_1.setText("");
                et_nama_setoran_2.setText("");
                et_nama_setoran_3.setText("");
                et_hpp_1.setText("");
                et_hpp_2.setText("");
                et_hpp_3.setText("");
                et_harga_jual_1.setText("");
                et_harga_jual_2.setText("");
                et_harga_jual_3.setText("");
//                Snackbar.make(parent_view, "Tambahkan anggota baru", Snackbar.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(R.string.tidak, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.show();
    }

    void insert() {
        if (!btn_nama_anggota_baru.getText().toString().isEmpty() && !et_nama_setoran_1.getText().toString().isEmpty() && !et_hpp_1.getText().toString().isEmpty() && !et_harga_jual_1.getText().toString().isEmpty()) {
            Setoran setoran = new Setoran();
            setoran.setNama(btn_nama_anggota_baru.getText().toString());
            setoran.setBarang1(et_nama_setoran_1.getText().toString());
            setoran.setHarga_beli1(Integer.parseInt(et_hpp_1.getText().toString()));
            setoran.setHarga_jual1(Integer.parseInt(et_harga_jual_1.getText().toString()));
            if (!et_nama_setoran_2.getText().toString().isEmpty() && !et_hpp_2.getText().toString().isEmpty() && !et_harga_jual_2.getText().toString().isEmpty()) {
                setoran.setBarang2(et_nama_setoran_2.getText().toString());
                setoran.setHarga_beli2(Integer.parseInt(et_hpp_2.getText().toString()));
                setoran.setHarga_jual2(Integer.parseInt(et_harga_jual_2.getText().toString()));
            }
            if (!et_nama_setoran_3.getText().toString().isEmpty() && !et_hpp_3.getText().toString().isEmpty() && !et_harga_jual_3.getText().toString().isEmpty()) {
                setoran.setBarang3(et_nama_setoran_3.getText().toString());
                setoran.setHarga_beli3(Integer.parseInt(et_hpp_3.getText().toString()));
                setoran.setHarga_jual3(Integer.parseInt(et_harga_jual_3.getText().toString()));
            }
            setoranModel.insert(setoran);
            showSimpanAnggotaBaru();
        }else {
            final AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Isian Kurang");
            builder.setMessage("1. Silahkan Isi Nama Penyetor\n2. Isikan Nama Barang dan Harga");
            builder.show();
        }

    }

}
