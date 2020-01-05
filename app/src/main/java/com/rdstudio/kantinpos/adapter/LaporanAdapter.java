package com.rdstudio.kantinpos.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rdstudio.kantinpos.R;
import com.rdstudio.kantinpos.dataroom.Laporan;

import java.text.MessageFormat;
import java.util.List;

public class LaporanAdapter extends RecyclerView.Adapter<LaporanAdapter.ListViewHolder>{

    private final LayoutInflater mInflater;
    private List<Laporan> mLaporan;

    public LaporanAdapter(Context context){this.mInflater=LayoutInflater.from(context);}

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.isi_laporan,parent,false);

        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        if (mLaporan!=null){
            Laporan laporan=mLaporan.get(position);
            holder.tv_anggota_penyetor.setText(laporan.getNama());
            holder.tv_laba.setText(MessageFormat.format("Rp. {0}", laporan.getLaba()));
            holder.tv_bayar.setText(MessageFormat.format("Rp. {0}", laporan.getBayar()));
        }

    }

    @Override
    public int getItemCount() {
        if (mLaporan != null)
            return mLaporan.size();
        else return 0;
    }

    public void setLaporan(List<Laporan> laporan) {
        mLaporan = laporan;
        notifyDataSetChanged();
        Log.e( "setLaporan: ",mLaporan.size()+"item" );
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tv_anggota_penyetor,tv_laba,tv_bayar;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_anggota_penyetor=itemView.findViewById(R.id.tv_anggota_penyetor);
            tv_laba=itemView.findViewById(R.id.tv_laba);
            tv_bayar=itemView.findViewById(R.id.tv_bayar);
        }
    }
}
