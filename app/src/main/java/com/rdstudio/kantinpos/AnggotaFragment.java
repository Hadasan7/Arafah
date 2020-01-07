package com.rdstudio.kantinpos;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rdstudio.kantinpos.adapter.SetoranAdapter;
import com.rdstudio.kantinpos.model.SetoranModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnggotaFragment extends Fragment {


    public AnggotaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_anggota, container, false);

        //CardView Anggota Penyetor
        RecyclerView rv_anggota = root.findViewById(R.id.rv_anggota);
        final SetoranAdapter setoranAdapter=new SetoranAdapter(getContext());
        rv_anggota.setAdapter(setoranAdapter);
        rv_anggota.setLayoutManager(new LinearLayoutManager(getContext()));

        SetoranModel setoranModel = new ViewModelProvider(this).get(SetoranModel.class);
        setoranModel.getmAll().observe(this, setoranAdapter::setSetoran);

        // FAB tambah anggota
        FloatingActionButton fabTambah = root.findViewById(R.id.fab_tambah_anggota);
        fabTambah.setOnClickListener(view -> {
            Toast.makeText(getActivity(), "Tambah Anggota Baru", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), TambahkanAnggota.class);
            startActivity(intent);
        });

        return root;
    }


}
