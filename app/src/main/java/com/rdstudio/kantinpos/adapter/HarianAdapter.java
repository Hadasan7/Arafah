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
import com.rdstudio.kantinpos.dataroom.Harian;

import java.text.MessageFormat;
import java.util.List;

public class HarianAdapter extends RecyclerView.Adapter<HarianAdapter.ListViewHolder>{

    private List<Harian> mHarian;

    public HarianAdapter(Context context){
        LayoutInflater mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.isi_harian,parent,false);

        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        if (mHarian!=null){
            Harian harian  =mHarian.get(position);
            holder.tv_tanggal.setText(harian.getTanggal());
            holder.tv_laba.setText(MessageFormat.format("Rp. {0}", harian.getLaba()));
        }

    }

    @Override
    public int getItemCount() {
        if (mHarian!= null)
            return mHarian.size();
        else return 0;
    }

    public void setHarian(List<Harian> harian) {
        mHarian= harian;
        notifyDataSetChanged();
        Log.e( "setLaporan: ",mHarian.size()+"item" );
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tv_tanggal,tv_laba;
        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tanggal=itemView.findViewById(R.id.tv_tanggal);
            tv_laba=itemView.findViewById(R.id.tv_laba);
        }
    }
}
