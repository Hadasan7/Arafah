package com.rdstudio.kantinpos.adapter;

import android.content.Context;
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
import com.rdstudio.kantinpos.dataroom.Setoran;
import com.rdstudio.kantinpos.utils.Tools;
import com.rdstudio.kantinpos.utils.ViewAnimation;

import java.text.MessageFormat;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ListViewHolder> {

    private List<Setoran> mSetoran;

    public MainAdapter(Context context){
        LayoutInflater mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.isi_setoran, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        if (mSetoran!=null){
            Setoran setoran=mSetoran.get(position);
            holder.tv_penyetor.setText(setoran.getNama());
            holder.tv_setoran_1.setText(setoran.getBarang1());
            holder.tv_setoran_2.setText(setoran.getBarang2());
            holder.tv_setoran_3.setText(setoran.getBarang3());
            holder.tv_jumlah_1.setText(MessageFormat.format("{0}", setoran.getJumlah1()));
            holder.tv_jumlah_2.setText(MessageFormat.format("{0}", setoran.getJumlah2()));
            holder.tv_jumlah_3.setText(MessageFormat.format("{0}", setoran.getJumlah3()));
            if (setoran.getJumlah2()==0) {
                holder.ll2.setVisibility(View.GONE);
            } else {
                holder.ll2.setVisibility(View.VISIBLE);
            }
            if ( setoran.getJumlah3()==0) {
                holder.ll3.setVisibility(View.GONE);
            }else {
                holder.ll3.setVisibility(View.VISIBLE);
            }
        }

    }

    public void setSetoran(List<Setoran> setoran) {
        mSetoran = setoran;
        notifyDataSetChanged();
        Log.e("setSetoranJ: ", mSetoran.size() + "item");
    }

    @Override
    public int getItemCount() {
        if (mSetoran != null)
            return mSetoran.size();
        else return 0;
    }

    class ListViewHolder extends RecyclerView.ViewHolder{
        TextView tv_penyetor, tv_setoran_1, tv_jumlah_1;
        TextView tv_setoran_2, tv_jumlah_2;
        TextView tv_setoran_3, tv_jumlah_3;
        ImageButton ibArrowDetail;
        Button  btnHideDetail;
        NestedScrollView nestedScrollView;
        View lytExpandPenyetor;
        LinearLayout ll2,ll3;

        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            lytExpandPenyetor = itemView.findViewById(R.id.lyt_expand_setoran);
            lytExpandPenyetor.setVisibility(View.GONE);
            tv_penyetor=itemView.findViewById(R.id.tv_penyetor);
            tv_setoran_1=itemView.findViewById(R.id.tv_setoran_1);
            tv_setoran_2=itemView.findViewById(R.id.tv_setoran_2);
            tv_setoran_3=itemView.findViewById(R.id.tv_setoran_3);
            tv_jumlah_1=itemView.findViewById(R.id.tv_jumlah_1);
            tv_jumlah_2=itemView.findViewById(R.id.tv_jumlah_2);
            tv_jumlah_3=itemView.findViewById(R.id.tv_jumlah_3);
            ibArrowDetail = itemView.findViewById(R.id.ib_arrow_detail);
            btnHideDetail = itemView.findViewById(R.id.btn_hide_setoran);
            ll2=itemView.findViewById(R.id.ll2);
            ll3=itemView.findViewById(R.id.ll3);
            btnHideDetail.setOnClickListener(view -> toggleSectionText(ibArrowDetail));

            ibArrowDetail.setOnClickListener(view -> toggleSectionText(ibArrowDetail));


            // nested scrollview
            nestedScrollView = itemView.findViewById(R.id.nested_scroll_view);
        }
        private void toggleSectionText(View view) {
            boolean show = toggleArrow(view);
            if (show) {
                ViewAnimation.expand(lytExpandPenyetor, () -> Tools.nestedScrollTo(nestedScrollView, lytExpandPenyetor));
            } else {
                ViewAnimation.collapse(lytExpandPenyetor);
            }

        }
        boolean toggleArrow(View view) {
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
