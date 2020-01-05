package com.rdstudio.kantinpos;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rdstudio.kantinpos.adapter.MainAdapter;
import com.rdstudio.kantinpos.model.SetoranModel;
import com.rdstudio.kantinpos.utils.Tools;
import com.rdstudio.kantinpos.utils.ViewAnimation;


/**
 * A simple {@link Fragment} subclass.
 */
public class SetoranFragment extends Fragment {

    private NestedScrollView nestedScrollView;
    private ImageButton btnArrowDetail;
    private TextView tvPenyetor;
    private Button btnHide;
    private View lytExpandPenyetor;
    private RecyclerView rv_setoran;
    private SetoranModel setoranModel;

    public SetoranFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_setoran, container, false);


        // CardView Penyetor
        btnArrowDetail = root.findViewById(R.id.ib_arrow_detail);
        tvPenyetor = root.findViewById(R.id.tv_penyetor);
        lytExpandPenyetor = root.findViewById(R.id.lyt_expand_setoran);
        lytExpandPenyetor.setVisibility(View.GONE);
        btnHide = root.findViewById(R.id.btn_hide_setoran);
        rv_setoran=root.findViewById(R.id.rv_setoran);
        final MainAdapter mainAdapter=new MainAdapter(getContext());
        rv_setoran.setAdapter(mainAdapter);
        rv_setoran.setLayoutManager(new LinearLayoutManager(getContext()));
        setoranModel=new ViewModelProvider(this).get(SetoranModel.class);
        setoranModel.getsetoran().observe(this,mainAdapter::setSetoran);


        btnArrowDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSectionText(btnArrowDetail);
            }
        });

        btnHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSectionText(btnArrowDetail);
            }
        });

        // nested scrollview
        nestedScrollView = root.findViewById(R.id.nested_scroll_view);

        // FAB tambahkan setoran baru
        FloatingActionButton fabTambah = root.findViewById(R.id.fab_tambah_setoran);
        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Tambah Setoran", Toast.LENGTH_SHORT).show();

                /**
                 * TODO: 1. klik intent tambah penyetor baru
                 * TODO: 2. Activity penyetor muncul
                 * TODO: 3. Input penyetor
                 * TODO: 4. Tambah? kembali ke Activity penyetor
                 * TODO: 5. Selesai? kembali ke MainActivity
                 */

                Intent intent = new Intent(getContext(), TambahkanSetoran.class);
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
