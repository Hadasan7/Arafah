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
import com.rdstudio.kantinpos.adapter.MainAdapter;
import com.rdstudio.kantinpos.model.SetoranModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class SetoranFragment extends Fragment {


    public SetoranFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_setoran, container, false);

        // CardView Penyetor
        RecyclerView rv_setoran = root.findViewById(R.id.rv_setoran);
        final MainAdapter mainAdapter=new MainAdapter(getContext());
        rv_setoran.setAdapter(mainAdapter);
        rv_setoran.setLayoutManager(new LinearLayoutManager(getContext()));
        SetoranModel setoranModel = new ViewModelProvider(this).get(SetoranModel.class);
        setoranModel.getsetoran().observe(this,mainAdapter::setSetoran);

        // FAB tambahkan setoran baru
        FloatingActionButton fabTambah = root.findViewById(R.id.fab_tambah_setoran);
        fabTambah.setOnClickListener(view -> {
            Toast.makeText(getActivity(), "Tambah Setoran", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), TambahkanSetoran.class);
            startActivity(intent);
        });

        return root;

    }

}
