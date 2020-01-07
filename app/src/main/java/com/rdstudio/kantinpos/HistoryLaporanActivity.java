package com.rdstudio.kantinpos;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rdstudio.kantinpos.adapter.HarianAdapter;
import com.rdstudio.kantinpos.dataroom.Harian;
import com.rdstudio.kantinpos.dataroom.Laporan;
import com.rdstudio.kantinpos.model.HarianModel;
import com.rdstudio.kantinpos.model.LaporanModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class HistoryLaporanActivity extends AppCompatActivity {

    RecyclerView rv_harian;
    Button btn_tambah_harian;
    ImageButton bt_close;
    HarianModel harianModel;
    List<Harian> harianList;
    LaporanModel laporanModel;
    List<Laporan> laporanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_laporan);
        bt_close = findViewById(R.id.bt_close);
        bt_close.setOnClickListener(v -> finish());
        btn_tambah_harian = findViewById(R.id.btn_tambah_harian);
        btn_tambah_harian.setOnClickListener(v -> karivikasi());
        rv_harian = findViewById(R.id.rv_harian);
        final HarianAdapter harianAdapter = new HarianAdapter(this);
        rv_harian.setAdapter(harianAdapter);
        rv_harian.setLayoutManager(new LinearLayoutManager(this));
        harianModel = new ViewModelProvider(this).get(HarianModel.class);
        harianModel.getmAllHarian().observe(this, harians -> harianList=harians);
        harianModel.getmAllHarian().observe(this, harianAdapter::setHarian);
        laporanModel=new ViewModelProvider(this).get(LaporanModel.class);
        laporanModel.getmAllLaporan().observe(this,laporans -> laporanList=laporans);
    }

    private void karivikasi() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(this));
        builder.setCancelable(true);
        builder.setTitle("Klarifikasi");
        builder.setMessage("Setiap Transaki Perhitungan Akan Dihapus dan dikonver jadi satu");
        builder.setPositiveButton("Simpan", (dialog, which) -> simpan());
        builder.setNegativeButton("Batal", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void simpan() {
        Log.e("simpan: ", hariini());
        int maxharian;
        if (harianList.size()==0){
            maxharian=0;
        }else {
            maxharian=harianList.get(harianList.size()-1).getId()+1;
        }
        if (laporanList.size() != 0) {
            Harian harian = new Harian();
            harian.setId(maxharian);
            harian.setTanggal(hariini());
            harian.setLaba(laba());
            harianModel.insert(harian);
            laporanModel.delete();
        }else {
            final AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(this));
            builder.setCancelable(true);
            builder.setTitle("Data Kosong");
            builder.setMessage("Data Dalam Laporan belum diinputkan");
            builder.show();
        }
    }

    private String laba() {
        int laba=0;
        if (laporanList.size() != 0) {
            for (int i = 0; i < laporanList.size(); i++) {
                laba = laba + laporanList.get(i).getLaba();
            }
        }
        Log.e("laba: ",laba+"" );
        return " "+laba;
    }

    private String hariini() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

}
