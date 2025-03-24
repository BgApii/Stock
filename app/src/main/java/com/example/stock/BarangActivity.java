package com.example.stock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stock.view.BarangAdapter;
import com.example.stock.view.BarangViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BarangActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloatingActionButton fabAddBarang;
    private BarangAdapter adapter;
    private BarangViewModel barangViewModel;
    private int kategoriId;
    private String namaKategori;
    private TextView textEmpty, tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        recyclerView = findViewById(R.id.recyclerBarang);
        fabAddBarang = findViewById(R.id.fabAddBarang);

        kategoriId = getIntent().getIntExtra("kategoriId", -1);
        namaKategori = getIntent().getStringExtra("namaKategori");
        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText(namaKategori);


        adapter = new BarangAdapter(new ArrayList<>(), barang -> {
            Intent intent = new Intent(BarangActivity.this, DetailBarangActivity.class);
            intent.putExtra("barangId", barang.id);
            intent.putExtra("kategoriId", kategoriId);
            intent.putExtra("namaKategori", namaKategori);
            startActivity(intent);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        barangViewModel = new ViewModelProvider(this).get(BarangViewModel.class);
        textEmpty = findViewById(R.id.textEmpty);
        barangViewModel.getBarangByKategori(kategoriId).observe(this, barangList -> {
            if (barangList.isEmpty()) {
                textEmpty.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            } else {
                textEmpty.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                adapter.setBarangList(barangList);
            }
        });

        fabAddBarang.setOnClickListener(view -> {
            Intent intent = new Intent(BarangActivity.this, AddBarangActivity.class);
            intent.putExtra("kategoriId", kategoriId);
            intent.putExtra("namaKategori", namaKategori);
            startActivity(intent);
        });

        findViewById(R.id.btnBack).setOnClickListener(v -> {
            finish();
        });
    }
}