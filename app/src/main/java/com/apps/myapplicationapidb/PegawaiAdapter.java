package com.apps.myapplicationapidb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PegawaiAdapter extends  RecyclerView.Adapter<PegawaiAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Pegawai> daftarPegawai;

    public PegawaiAdapter(Context mContext) {
        this.mContext = mContext;
        daftarPegawai = new ArrayList<>();
    }
    public void setDaftarPegawai(ArrayList<Pegawai> daftarPegawai) {
        this.daftarPegawai = daftarPegawai;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view =inflater.inflate(R.layout.list_pegawai_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pegawai currentPegawai = daftarPegawai.get(position);
        holder.tvNama.setText(currentPegawai.getName());
    }
    @Override
    public int getItemCount() {
        return daftarPegawai.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama;
        Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_nama);
            button = itemView.findViewById(R.id.button);
        }
    }

}
