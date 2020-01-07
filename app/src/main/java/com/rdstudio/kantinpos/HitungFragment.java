package com.rdstudio.kantinpos;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.rdstudio.kantinpos.dataroom.Laporan;
import com.rdstudio.kantinpos.dataroom.Setoran;
import com.rdstudio.kantinpos.model.LaporanModel;
import com.rdstudio.kantinpos.model.SetoranModel;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class HitungFragment extends Fragment {

    private Button btn_tambah_penyetor;
    private Button btn_masukkan_sisa_1;
    private Button btn_masukkan_sisa_2;
    private Button btn_masukkan_sisa_3;
    private SetoranModel setoranModel;
    private LaporanModel laporanModel;
    private String[] array_penyetor;
    private List<Setoran> setoranList;
    private List<Laporan> laporanList;
    private TextView barang1, barang2, barang3, tv_hpp_1, tv_hpp_2, tv_hpp_3, tv_jumlah_1, tv_jumlah_2, tv_jumlah_3, et_nama_setoran_1, et_nama_setoran_2, et_nama_setoran_3;
    private TextView terjual1, terjual2, terjual3, bayar_penyetor1, bayar_penyetor2, bayar_penyetor3;
    private TextView tv1, tv2, total_laba, total_bayar;
    private LinearLayout ll2, ll3;
    private int posisi = 0;


    public HitungFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hitung, container, false);
        btn_tambah_penyetor = view.findViewById(R.id.btn_tambah_penyetor);
        barang1 = view.findViewById(R.id.barang1);
        barang2 = view.findViewById(R.id.barang2);
        barang3 = view.findViewById(R.id.barang3);
        tv_hpp_1 = view.findViewById(R.id.tv_hpp_1);
        tv_hpp_2 = view.findViewById(R.id.tv_hpp_2);
        tv_hpp_3 = view.findViewById(R.id.tv_hpp_3);
        tv_jumlah_1 = view.findViewById(R.id.tv_jumlah_1);
        tv_jumlah_2 = view.findViewById(R.id.tv_jumlah_2);
        tv_jumlah_3 = view.findViewById(R.id.tv_jumlah_3);
        et_nama_setoran_1 = view.findViewById(R.id.et_nama_setoran_1);
        et_nama_setoran_2 = view.findViewById(R.id.et_nama_setoran_2);
        et_nama_setoran_3 = view.findViewById(R.id.et_nama_setoran_3);
        btn_masukkan_sisa_1 = view.findViewById(R.id.btn_masukkan_sisa_1);
        btn_masukkan_sisa_2 = view.findViewById(R.id.btn_masukkan_sisa_2);
        btn_masukkan_sisa_3 = view.findViewById(R.id.btn_masukkan_sisa_3);
        bayar_penyetor1 = view.findViewById(R.id.bayar_penyetor1);
        bayar_penyetor2 = view.findViewById(R.id.bayar_penyetor2);
        bayar_penyetor3 = view.findViewById(R.id.bayar_penyetor3);
        terjual1 = view.findViewById(R.id.terjual1);
        terjual2 = view.findViewById(R.id.terjual2);
        terjual3 = view.findViewById(R.id.terjual3);
        ll2 = view.findViewById(R.id.ll2);
        ll3 = view.findViewById(R.id.ll3);
        total_laba = view.findViewById(R.id.total_laba);
        total_bayar = view.findViewById(R.id.total_bayar);
        Button btn_simpan = view.findViewById(R.id.btn_simpan);
        btn_simpan.setOnClickListener(v -> karivikasi());
        btn_masukkan_sisa_1.setOnClickListener(v -> sisa((Button) v));
        btn_masukkan_sisa_2.setOnClickListener(v -> sisa((Button) v));
        btn_masukkan_sisa_3.setOnClickListener(v -> sisa((Button) v));

        btn_tambah_penyetor.setOnClickListener(v -> initDialogTambahSetoran((AppCompatButton) v));
        setoranModel = new ViewModelProvider(this).get(SetoranModel.class);
        setoranModel.getsetoran().observe(this, setorans -> {
            String[] strings = new String[setorans.size()];
            for (int i = 0; i < setorans.size(); i++) {
                strings[i] = setorans.get(i).getNama();
                if (i == setorans.size() - 1) {
                    array_penyetor = strings;
                    setoranList = setorans;
                }
            }
        });
        laporanModel = new ViewModelProvider(this).get(LaporanModel.class);
        laporanModel.getmAllLaporan().observe(this, laporans -> laporanList = laporans);
        // Inflate the layout for this fragment
        return view;
    }

    private void karivikasi() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
        builder.setCancelable(true);
        builder.setTitle("Klarifikasi");
        builder.setMessage("Stok Barang Akan Diganti Sisa Barang");
        builder.setPositiveButton("Simpan", (dialog, which) -> simpan());
        builder.setNegativeButton("Batal", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void simpan() {
        if (!btn_tambah_penyetor.getText().toString().isEmpty() && !terjual1.getText().toString().isEmpty()) {
            lapor();
        } else {
            final AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
            builder.setCancelable(true);
            builder.setTitle("Isian Kurang");
            builder.setMessage("1. Silahkan Pilih Nama Penyetor\n2. Pilih Sisa Barang");
            builder.show();
        }
    }

    private void lapor() {
        boolean validasi2, validasi3;
        int sisa1, sisa2, sisa3;
        String nama;
        int laba, bayar;
        nama = btn_tambah_penyetor.getText().toString();
        sisa1 = generateDot(btn_masukkan_sisa_1.getText().toString());
        if (ll2.getVisibility() == View.GONE) {
            sisa2 = 0;
            validasi2 = true;
        } else {
            if (terjual2.getText().toString().isEmpty()) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
                builder.setCancelable(true);
                builder.setTitle("Isian Kurang");
                builder.setMessage("Pilih Sisa Barang Ke-2");
                builder.show();
                sisa2 = generateDot(tv_jumlah_2.getText().toString());
                validasi2 = false;
            } else {
                sisa2 = generateDot(btn_masukkan_sisa_2.getText().toString());
                validasi2 = true;
            }
        }
        if (ll3.getVisibility() == View.GONE) {
            sisa3 = 0;
            validasi3 = true;
        } else {
            if (terjual3.getText().toString().isEmpty()) {
                sisa3 = generateDot(tv_jumlah_3.getText().toString());
                final AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
                builder.setCancelable(true);
                builder.setTitle("Isian Kurang");
                builder.setMessage("Pilih Sisa Barang Ke-3");
                builder.show();
                validasi3 = false;
            } else {
                sisa3 = generateDot(btn_masukkan_sisa_3.getText().toString());
                validasi3 = true;
            }
        }
//        Log.e("lapor: ", "\nNama:" + nama + "\nSisa1:" + sisa1 + "\nSisa2:" + sisa2 + "\nSisa3:" + sisa3);
        if (validasi2 && validasi3) {
            laba = generateDot(total_laba.getText().toString());
            bayar = generateDot(total_bayar.getText().toString());
            Log.e("lapor: ", "Update" + laba + "+" + bayar);
            int maxlaporan;
            if (laporanList.size() == 0) {
                maxlaporan = 0;
            } else {
                maxlaporan = laporanList.get(laporanList.size() - 1).getId() + 1;
            }
            Laporan laporan = new Laporan();
            laporan.setId(maxlaporan);
            laporan.setNama(nama);
            laporan.setLaba(laba);
            laporan.setBayar(bayar);
            setoranModel.setor(nama, sisa1, sisa2, sisa3);
            laporanModel.insert(laporan);
            settext0();
            btn_tambah_penyetor.setText("");
            total_laba.setText("");
            total_bayar.setText("");

        }
    }


    private void initDialogTambahSetoran(final AppCompatButton btn) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
        builder.setCancelable(true);
        builder.setSingleChoiceItems(array_penyetor, 0, (dialogInterface, i) -> {
            dialogInterface.dismiss();
            settext0();
            posisi = i;
            btn.setTextColor(Color.BLACK);
            btn.setText(array_penyetor[i]);
            barang1.setText(setoranList.get(i).getBarang1());
            et_nama_setoran_1.setText(setoranList.get(i).getBarang1());
            tv_hpp_1.setText(MessageFormat.format("Rp. {0}", setoranList.get(i).getHarga_beli1()));
            tv_jumlah_1.setText(MessageFormat.format("{0}", setoranList.get(i).getJumlah1()));
            if (!(setoranList.get(i).getBarang2() == null)) {
                if (!(setoranList.get(i).getBarang2().isEmpty())) {
                    barang2.setText(setoranList.get(i).getBarang2());
                    et_nama_setoran_2.setText(setoranList.get(i).getBarang2());
                    tv_hpp_2.setText(MessageFormat.format("Rp. {0}", setoranList.get(i).getHarga_beli2()));
                    tv_jumlah_2.setText(MessageFormat.format("{0}", setoranList.get(i).getJumlah2()));
                    ll2.setVisibility(View.VISIBLE);
                } else {
                    barang2.setText("");
                    et_nama_setoran_2.setText("");
                    tv_hpp_2.setText("");
                    tv_jumlah_2.setText("0");
                    ll2.setVisibility(View.GONE);
                }
            } else {
                barang2.setText("");
                et_nama_setoran_2.setText("");
                tv_hpp_2.setText("");
                tv_jumlah_2.setText("0");
                ll2.setVisibility(View.GONE);
            }
            if (!(setoranList.get(i).getBarang3() == null)) {
                if (!(setoranList.get(i).getBarang3().isEmpty())) {
                    barang3.setText(setoranList.get(i).getBarang3());
                    et_nama_setoran_3.setText(setoranList.get(i).getBarang3());
                    tv_hpp_3.setText(MessageFormat.format("Rp. {0}", setoranList.get(i).getHarga_beli3()));
                    tv_jumlah_3.setText(MessageFormat.format("{0}", setoranList.get(i).getJumlah3()));
                    ll3.setVisibility(View.VISIBLE);
                } else {
                    barang3.setText("");
                    et_nama_setoran_3.setText("");
                    tv_hpp_3.setText("");
                    tv_jumlah_3.setText("0");
                    ll3.setVisibility(View.GONE);
                }
            } else {
                barang3.setText("");
                et_nama_setoran_3.setText("");
                tv_hpp_3.setText("");
                tv_jumlah_3.setText("0");
                ll3.setVisibility(View.GONE);
            }
        });
        builder.show();
    }

    private void sisa(Button btn) {
        int max = 0, harga = 0, laba = 0;
        if (!btn_tambah_penyetor.getText().toString().isEmpty()) {
            switch (btn.getId()) {
                case R.id.btn_masukkan_sisa_1:
                    max = setoranList.get(posisi).getJumlah1();
                    tv1 = terjual1;
                    tv2 = bayar_penyetor1;
                    harga = setoranList.get(posisi).getHarga_beli1();
                    laba = setoranList.get(posisi).getHarga_jual1() - setoranList.get(posisi).getHarga_beli1();
                    break;
                case R.id.btn_masukkan_sisa_2:
                    max = setoranList.get(posisi).getJumlah2();
                    tv1 = terjual2;
                    tv2 = bayar_penyetor2;
                    harga = setoranList.get(posisi).getHarga_beli2();
                    laba = setoranList.get(posisi).getHarga_jual2() - setoranList.get(posisi).getHarga_beli2();
                    break;
                case R.id.btn_masukkan_sisa_3:
                    max = setoranList.get(posisi).getJumlah3();
                    tv1 = terjual3;
                    tv2 = bayar_penyetor3;
                    harga = setoranList.get(posisi).getHarga_beli3();
                    laba = setoranList.get(posisi).getHarga_jual3() - setoranList.get(posisi).getHarga_beli3();
                    break;
            }
            String[] ints = new String[max + 1];
            for (int i = 0; i < max; i++) {
                ints[i] = String.valueOf(i);
                if (i == max - 1) {
                    ints[i + 1] = String.valueOf(i + 1);
                    final AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
                    builder.setCancelable(true);
                    int finalMax = max;
                    int finalHarga = harga;
                    int finalLaba = laba;
                    builder.setSingleChoiceItems(ints, 0, (dialogInterface, i1) -> {
                        dialogInterface.dismiss();
                        btn.setTextColor(Color.BLACK);
                        btn.setText(ints[i1]);
                        tv1.setText(MessageFormat.format("Rp. {0}", (finalMax - i1) * finalLaba));
                        tv2.setText(MessageFormat.format("Rp. {0}", (finalMax - i1) * finalHarga));
                        int iLaba, iBayar;
                        iLaba = generateDot(terjual1.getText().toString()) + generateDot(terjual2.getText().toString()) + generateDot(terjual3.getText().toString());
                        iBayar = generateDot(bayar_penyetor1.getText().toString()) + generateDot(bayar_penyetor2.getText().toString()) + generateDot(bayar_penyetor3.getText().toString());
//                            Log.e("onClick: ", iLaba + " " + iBayar);
                        total_laba.setText(MessageFormat.format("Rp. {0}", iLaba));
                        total_bayar.setText(MessageFormat.format("Rp. {0}", iBayar));
                    });
                    builder.show();

                }
            }
        }

    }

    private void settext0() {
        tv_jumlah_1.setText("");
        tv_jumlah_2.setText("");
        tv_jumlah_3.setText("");
        terjual1.setText("");
        terjual2.setText("");
        terjual3.setText("");
        btn_masukkan_sisa_1.setText("");
        btn_masukkan_sisa_2.setText("");
        btn_masukkan_sisa_3.setText("");
        et_nama_setoran_1.setText("");
        et_nama_setoran_2.setText("");
        et_nama_setoran_3.setText("");
        bayar_penyetor1.setText("");
        bayar_penyetor2.setText("");
        bayar_penyetor3.setText("");
        barang1.setText("");
        barang2.setText("");
        barang3.setText("");
        tv_hpp_1.setText("");
        tv_hpp_2.setText("");
        tv_hpp_3.setText("");
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
