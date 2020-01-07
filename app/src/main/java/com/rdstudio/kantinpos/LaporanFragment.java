package com.rdstudio.kantinpos;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rdstudio.kantinpos.adapter.LaporanAdapter;
import com.rdstudio.kantinpos.model.LaporanModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class LaporanFragment extends Fragment {

    public LaporanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_laporan,container,false);
        RecyclerView rv_laporan = view.findViewById(R.id.rv_laporan);
        final LaporanAdapter laporanAdapter=new LaporanAdapter(getContext());
        rv_laporan.setAdapter(laporanAdapter);
        rv_laporan.setLayoutManager(new LinearLayoutManager(getContext()));
        LaporanModel laporanModel = new ViewModelProvider(this).get(LaporanModel.class);
        laporanModel.getmAllLaporan().observe(this,laporanAdapter::setLaporan);
        // Inflate the layout for this fragment
        return view;
    }

}
