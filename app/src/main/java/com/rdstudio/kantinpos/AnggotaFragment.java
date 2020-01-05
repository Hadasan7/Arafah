package com.rdstudio.kantinpos;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rdstudio.kantinpos.adapter.SetoranAdapter;
import com.rdstudio.kantinpos.model.SetoranModel;
import com.rdstudio.kantinpos.utils.Tools;
import com.rdstudio.kantinpos.utils.ViewAnimation;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnggotaFragment extends Fragment {

    private NestedScrollView nestedScrollView;
    private ImageButton ibArrowDetail;
    private Button btnEditDataAnggota, btnHideDetail;
    private View lytExpandPenyetor;
    private RecyclerView rv_anggota;
    private SetoranModel setoranModel;

    public AnggotaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_anggota, container, false);

        //CardView Anggota Penyetor
        ibArrowDetail = root.findViewById(R.id.ib_arrow_detail);
        btnEditDataAnggota = root.findViewById(R.id.btn_edit_data_anggota);
        btnHideDetail = root.findViewById(R.id.btn_hide_setoran);
        lytExpandPenyetor = root.findViewById(R.id.lyt_expand_anggota);
        lytExpandPenyetor.setVisibility(View.GONE);
        rv_anggota=root.findViewById(R.id.rv_anggota);
        final SetoranAdapter setoranAdapter=new SetoranAdapter(getContext());
        rv_anggota.setAdapter(setoranAdapter);
        rv_anggota.setLayoutManager(new LinearLayoutManager(getContext()));

        setoranModel=new ViewModelProvider(this).get(SetoranModel.class);
        setoranModel.getmAll().observe(this, setoranAdapter::setSetoran);

        btnHideDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSectionText(ibArrowDetail);
            }
        });

        ibArrowDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSectionText(ibArrowDetail);
            }
        });

        btnEditDataAnggota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Ubah Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), TambahkanAnggota.class);
                // bawa data user yang mau di edit
                startActivity(intent);
            }
        });

        // nested scrollview
        nestedScrollView = root.findViewById(R.id.nested_scroll_view);

        // FAB tambah anggota
        FloatingActionButton fabTambah = root.findViewById(R.id.fab_tambah_anggota);
        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Tambah Anggota Baru", Toast.LENGTH_SHORT).show();

                /**
                 * TODO: 1. klik intent tambah anggota baru
                 * TODO: 2. Dialog muncul
                 * TODO: 3. Activity Tambah anggota muncul
                 * TODO: 3. Input penyetor klik simpan
                 * TODO: 4. Toast Cek Kembali, dua opsi
                 * TODO: 5. opsi batal, tetap di activity
                 * TODO: 6. opsi simpan, kembali ke main  activity
                 */

                Intent intent = new Intent(getContext(), TambahkanAnggota.class);
                startActivity(intent);
            }
        });

        return root;
    }

    // Show Hide CardView
    private void toggleSectionText(View view) {
        boolean show = toggleArrow(view);
        if (show) {
            ViewAnimation.expand(lytExpandPenyetor, new ViewAnimation.AnimListener() {
                @Override
                public void onFinish() {
                    Tools.nestedScrollTo(nestedScrollView, lytExpandPenyetor);
                }
            });
        } else {
            ViewAnimation.collapse(lytExpandPenyetor);
        }

    }

    public boolean toggleArrow(View view) {
        if (view.getRotation() == 0) {
            view.animate().setDuration(200).rotation(180);
            return true;
        } else {
            view.animate().setDuration(200).rotation(0);
            return false;
        }
    }

}
