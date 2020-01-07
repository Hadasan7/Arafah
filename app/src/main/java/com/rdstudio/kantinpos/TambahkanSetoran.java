package com.rdstudio.kantinpos;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.rdstudio.kantinpos.dataroom.Setoran;
import com.rdstudio.kantinpos.model.SetoranModel;
import com.rdstudio.kantinpos.utils.Tools;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

public class TambahkanSetoran extends AppCompatActivity {

    private String[] array_penyetor;
    private SetoranModel setoranModel;
    List<Setoran> setoranList;
    EditText et_jenis_setoran_1, et_jenis_setoran_2, et_jenis_setoran_3, et_jumlah_setoran_1, et_jumlah_setoran_2, et_jumlah_setoran_3;
    LinearLayout ll2, ll3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahkan_setoran);
        et_jenis_setoran_1 = findViewById(R.id.et_jenis_setoran_1);
        et_jenis_setoran_2 = findViewById(R.id.et_jenis_setoran_2);
        et_jenis_setoran_3 = findViewById(R.id.et_jenis_setoran_3);
        et_jumlah_setoran_1 = findViewById(R.id.et_jumlah_setoran_1);
        et_jumlah_setoran_2 = findViewById(R.id.et_jumlah_setoran_2);
        et_jumlah_setoran_3 = findViewById(R.id.et_jumlah_setoran_3);
        ll2 = findViewById(R.id.ll2);
        ll3 = findViewById(R.id.ll3);
        setoranModel = new ViewModelProvider(this).get(SetoranModel.class);
        setoranModel.getmAll().observe(this, setorans -> {
            String[] strings = new String[setorans.size()];
            for (int i = 0; i < setorans.size(); i++) {
                strings[i] = setorans.get(i).getNama();
                if (i == setorans.size() - 1) {
                    array_penyetor = strings;
                    setoranList = setorans;
                }
            }
        });

//        array_penyetor = getResources().getStringArray(R.array.data_penyetor);
        initToolbar();
        initContent();
    }


    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        Objects.requireNonNull(toolbar.getNavigationIcon())
                .setColorFilter(getResources().getColor(R.color.grey_60), PorterDuff.Mode.SRC_ATOP);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(null);
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
//            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
            // Dialog menu simpan
            showConfirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    // Confirm dialog close and tambah setoran
    private void showConfirmDialog() {
/*
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selesai & Tambah Lagi?");
        builder.setMessage(R.string.confirm_setoran);
        builder.setPositiveButton(R.string.ya, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Snackbar.make(parent_view, "Tambahkan data setoran lagi", Snackbar.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(R.string.tidak, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.show();
*/
        if (!et_jenis_setoran_1.getText().toString().isEmpty() && !et_jumlah_setoran_1.getText().toString().isEmpty()) {
            setor();
            finish();
        }else {
            final AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Isian Kurang");
            builder.setMessage("1. Silahkan Pilih Nama Penyetor \n2. Isi Jumlah Setoran");
            builder.show();
        }
    }

    void setor() {
        Button etTambahPenyetor = findViewById(R.id.btn_tambah_penyetor);
        int i1, i2 = 0, i3 = 0;
        i1 = generateDot(et_jumlah_setoran_1.getText().toString());
        if (!et_jenis_setoran_2.getText().toString().equals("")) {
            i2 = generateDot(et_jumlah_setoran_2.getText().toString());
        }
        if (!et_jenis_setoran_3.getText().toString().equals("")) {
            i3 = generateDot((et_jumlah_setoran_3.getText().toString()));
        }
        String nama = etTambahPenyetor.getText().toString();
        setoranModel.setor(nama, i1, i2, i3);
        Toast.makeText(this, "Setoran Ditambahkan", Toast.LENGTH_SHORT).show();
    }

    private void initContent() {

        Button etTambahPenyetor = findViewById(R.id.btn_tambah_penyetor);
        etTambahPenyetor.setOnClickListener(view -> {

            if (view.getId() == R.id.btn_tambah_penyetor) {
                initDialogTambahSetoran((AppCompatButton) view);
            }
        });

    }

    private void initDialogTambahSetoran(final AppCompatButton btn) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setSingleChoiceItems(array_penyetor, 0, (dialogInterface, i) -> {
            dialogInterface.dismiss();
            settext0();
            btn.setTextColor(Color.BLACK);
            btn.setText(array_penyetor[i]);
            et_jenis_setoran_1.setText(setoranList.get(i).getBarang1());
            if (setoranList.get(i).getJumlah1() != 0) {
                et_jumlah_setoran_1.setText(MessageFormat.format("{0}", setoranList.get(i).getJumlah1()));
            }
            if (!(setoranList.get(i).getBarang2() == null)) {
                if (!(setoranList.get(i).getBarang2().isEmpty())) {
                    ll2.setVisibility(View.VISIBLE);
                    et_jenis_setoran_2.setText(setoranList.get(i).getBarang2());
                    if (setoranList.get(i).getJumlah2() != 0) {
                        et_jumlah_setoran_2.setText(MessageFormat.format("{0}", setoranList.get(i).getJumlah2()));
                    }
                } else {
                    ll2.setVisibility(View.GONE);
                }
            } else {
                ll2.setVisibility(View.GONE);
            }
            if (!(setoranList.get(i).getBarang3() == null)) {
                if (!(setoranList.get(i).getBarang3().isEmpty())) {
                    ll3.setVisibility(View.VISIBLE);
                    et_jenis_setoran_3.setText(setoranList.get(i).getBarang3());
                    if (setoranList.get(i).getJumlah3() != 0) {
                        et_jumlah_setoran_3.setText(MessageFormat.format("{0}", setoranList.get(i).getJumlah3()));
                    }
                } else {
                    ll3.setVisibility(View.GONE);
                }
            } else {
                ll3.setVisibility(View.GONE);
            }


        });
        builder.show();
    }

    void settext0() {
        et_jumlah_setoran_1.setText("");
        et_jumlah_setoran_2.setText("");
        et_jumlah_setoran_3.setText("");
        et_jenis_setoran_1.setText("");
        et_jenis_setoran_2.setText("");
        et_jenis_setoran_3.setText("");
    }

    private int generateDot(@NonNull String s) {
        String s1, s2;
        Log.e("generateDot: ", s);
        s1 = s.replaceAll("Rp. ", "");
        Log.e("generateDot1: ", s1);
        s2 = s1.replace(".", "");
        Log.e("generateDot2: ", s2);
        int i ;
        if (s2.equals("")) {
            i = 0;
        } else {
            i = Integer.parseInt(s2);
        }
        return i;
    }

}
