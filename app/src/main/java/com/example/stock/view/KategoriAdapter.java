package com.example.stock.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stock.R;
import com.example.stock.data.Kategori;

import java.util.List;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.ViewHolder> {
    private List<Kategori> kategoriList;
    private OnKategoriClickListener listener;
    private OnKategoriDeleteListener deleteListener;
    private Context context;

    public interface OnKategoriClickListener {
        void onKategoriClick(Kategori kategori);
    }

    public interface OnKategoriDeleteListener {
        void onKategoriDelete(Kategori kategori);
    }

    public KategoriAdapter(List<Kategori> kategoriList, OnKategoriClickListener listener, OnKategoriDeleteListener deleteListener) {
        this.kategoriList = kategoriList;
        this.listener = listener;
        this.deleteListener = deleteListener;
    }

    public void setKategoriList(List<Kategori> kategoriList) {
        this.kategoriList = kategoriList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_kategori, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Kategori kategori = kategoriList.get(position);
        holder.txtNamaKategori.setText(kategori.namaKategori);

        holder.itemView.setOnClickListener(view -> listener.onKategoriClick(kategori));

        holder.imgDelete.setOnClickListener(view -> {
            new AlertDialog.Builder(context)
                    .setTitle("Konfirmasi Hapus")
                    .setMessage("Apakah Anda yakin ingin menghapus kategori ini (termasuk barang di dalamnya)?")
                    .setPositiveButton("Ya", (dialog, which) -> {
                        deleteListener.onKategoriDelete(kategori);
                    })
                    .setNegativeButton("Batal", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return kategoriList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNamaKategori;
        ImageView imgDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNamaKategori = itemView.findViewById(R.id.txtNamaKategori);
            imgDelete = itemView.findViewById(R.id.btnDeleteKategori);
        }
    }
}
