package com.example.stock.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stock.R;
import com.example.stock.data.Barang;

import java.util.List;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.ViewHolder> {
    private List<Barang> barangList;
    private OnBarangClickListener listener;

    public interface OnBarangClickListener {
        void onBarangClick(Barang barang);
    }

    public BarangAdapter(List<Barang> barangList, OnBarangClickListener listener) {
        this.barangList = barangList;
        this.listener = listener;
    }

    public void setBarangList(List<Barang> barangList) {
        this.barangList = barangList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Barang barang = barangList.get(position);
        holder.txtNamaBarang.setText(barang.namaBarang);
        holder.txtJumlahBarang.setText("Jumlah: " + barang.jumlahBarang);
        holder.txtHargaBarang.setText("Rp. " + barang.hargaBarang);
        holder.itemView.setOnClickListener(view -> listener.onBarangClick(barang));
    }

    @Override
    public int getItemCount() {
        return barangList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNamaBarang;
        TextView txtJumlahBarang;
        TextView txtHargaBarang;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNamaBarang = itemView.findViewById(R.id.txtNamaBarang);
            txtJumlahBarang = itemView.findViewById(R.id.txtJumlahBarang);
            txtHargaBarang = itemView.findViewById(R.id.txtHargaBarang);
        }
    }
}
