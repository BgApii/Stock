package com.example.stock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stock.view.KategoriAdapter;
import com.example.stock.view.KategoriViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloatingActionButton fabAddKategori;
    private KategoriAdapter adapter;
    private KategoriViewModel kategoriViewModel;
    private TextView textEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        recyclerView = findViewById(R.id.recyclerKategori);
        fabAddKategori = findViewById(R.id.fabAddKategori);
        textEmpty = findViewById(R.id.textEmpty);

        adapter = new KategoriAdapter(new ArrayList<>(), kategori -> {
            Intent intent = new Intent(MainActivity.this, BarangActivity.class);
            intent.putExtra("kategoriId", kategori.id);
            intent.putExtra("namaKategori", kategori.namaKategori);
            startActivity(intent);
        }, kategori -> {
            kategoriViewModel.delete(kategori);
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        kategoriViewModel = new ViewModelProvider(this).get(KategoriViewModel.class);
        kategoriViewModel.getAllKategori().observe(this, kategoriList -> {
            if (kategoriList.isEmpty()) {
                textEmpty.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            } else {
                textEmpty.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                adapter.setKategoriList(kategoriList);
            }
        });

        fabAddKategori.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddKategoriActivity.class);
            startActivity(intent);
        });
    }
}
