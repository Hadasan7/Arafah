package com.rdstudio.kantinpos.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.rdstudio.kantinpos.R;
import com.rdstudio.kantinpos.UbahAnggota;
import com.rdstudio.kantinpos.dataroom.Setoran;
import com.rdstudio.kantinpos.utils.Tools;
import com.rdstudio.kantinpos.utils.ViewAnimation;

import java.text.MessageFormat;
import java.util.List;

public class SetoranAdapter extends RecyclerView.Adapter<SetoranAdapter.ListViewHolder> {

    private final LayoutInflater mInflater;
    private List<Setoran> mSetoran;

    public SetoranAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.isi_anggota, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        if (mSetoran != null) {
            Setoran setoran = mSetoran.get(position);
            holder.tv_anggota_penyetor.setText(setoran.getNama());
            holder.tv_tambah_jenis_setoran_1.setText(setoran.getBarang1());
            holder.tv_tambah_jenis_setoran_2.setText(setoran.getBarang2());
            holder.tv_tambah_jenis_setoran_3.setText(setoran.getBarang3());
            holder.tv_hpp_1.setText(MessageFormat.format("{0}", setoran.getHarga_beli1()));
            holder.tv_hpp_2.setText(MessageFormat.format("{0}", setoran.getHarga_beli2()));
            holder.tv_hpp_3.setText(MessageFormat.format("{0}", setoran.getHarga_beli3()));
            holder.tv_harga_jual_1.setText(MessageFormat.format("{0}", setoran.getHarga_jual1()));
            holder.tv_harga_jual_2.setText(MessageFormat.format("{0}", setoran.getHarga_jual2()));
            holder.tv_harga_jual_3.setText(MessageFormat.format("{0}", setoran.getHarga_jual3()));
            if (setoran.getHarga_beli2()==0) {
                holder.ll2.setVisibility(View.GONE);
            } else {
                holder.ll2.setVisibility(View.VISIBLE);
            }
            if (setoran.getHarga_beli3()==0) {
                holder.ll3.setVisibility(View.GONE);
            }else {
                holder.ll3.setVisibility(View.VISIBLE);
            }
            holder.btnEditDataAnggota.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mInflater.getContext(), UbahAnggota.class);
                    intent.putExtra("setoran", (Parcelable) setoran);
                    // bawa data user yang mau di edit
                    mInflater.getContext().startActivity(intent);

                }
            });
            Log.e("onBindViewHolder: ", setoran.getNama());
        }
    }

    @Override
    public int getItemCount() {
        if (mSetoran != null)
            return mSetoran.size();
        else return 0;
    }

    public void setSetoran(List<Setoran> setoran) {
        mSetoran = setoran;
        notifyDataSetChanged();
        Log.e("setSetoran: ", mSetoran.size() + "item");
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        TextView tv_anggota_penyetor, tv_tambah_jenis_setoran_1, tv_hpp_1, tv_harga_jual_1;
        TextView tv_tambah_jenis_setoran_2, tv_hpp_2, tv_harga_jual_2;
        TextView tv_tambah_jenis_setoran_3, tv_hpp_3, tv_harga_jual_3;
        NestedScrollView nestedScrollView;
        ImageButton ibArrowDetail;
        Button btnEditDataAnggota, btnHideDetail;
        View lytExpandPenyetor;
        LinearLayout ll2,ll3;


        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            ibArrowDetail = itemView.findViewById(R.id.ib_arrow_detail);
            btnEditDataAnggota = itemView.findViewById(R.id.btn_edit_data_anggota);
            btnHideDetail = itemView.findViewById(R.id.btn_hide_setoran);
            lytExpandPenyetor = itemView.findViewById(R.id.lyt_expand_anggota);
            lytExpandPenyetor.setVisibility(View.GONE);
            tv_anggota_penyetor = itemView.findViewById(R.id.tv_anggota_penyetor);
            tv_tambah_jenis_setoran_1 = itemView.findViewById(R.id.tv_tambah_jenis_setoran_1);
            tv_hpp_1 = itemView.findViewById(R.id.tv_hpp_1);
            tv_harga_jual_1 = itemView.findViewById(R.id.tv_harga_jual_1);
            tv_tambah_jenis_setoran_2 = itemView.findViewById(R.id.tv_tambah_jenis_setoran_2);
            tv_hpp_2 = itemView.findViewById(R.id.tv_hpp_2);
            tv_harga_jual_2 = itemView.findViewById(R.id.tv_harga_jual_2);
            tv_tambah_jenis_setoran_3 = itemView.findViewById(R.id.tv_tambah_jenis_setoran_3);
            tv_hpp_3 = itemView.findViewById(R.id.tv_hpp_3);
            tv_harga_jual_3 = itemView.findViewById(R.id.tv_harga_jual_3);
            ll2=itemView.findViewById(R.id.ll2);
            ll3=itemView.findViewById(R.id.ll3);
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


            // nested scrollview
            nestedScrollView = itemView.findViewById(R.id.nested_scroll_view);
        }

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



}
