package com.example.stock;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.stock.data.Barang;
import com.example.stock.view.BarangViewModel;

public class DetailBarangActivity extends AppCompatActivity {
    private TextView txtNama, txtHarga, txtJumlah, txtNamaKategori;
    private Barang currentBarang;
    private String namaKategori;
    private BarangViewModel barangViewModel;
    private int barangId;

    private static final int REQUEST_EDIT = 100;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_barang);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        txtNama = findViewById(R.id.txtNamaBarang);
        txtHarga = findViewById(R.id.txtHargaBarang);
        txtJumlah = findViewById(R.id.txtJumlahBarang);
        txtNamaKategori = findViewById(R.id.txtNamaKategori);

        barangId = getIntent().getIntExtra("barangId", -1);
        namaKategori = getIntent().getStringExtra("namaKategori");
        barangViewModel = new ViewModelProvider(this).get(BarangViewModel.class);

        loadBarangData();

        findViewById(R.id.btnJual).setOnClickListener(v -> {
            if (currentBarang != null && currentBarang.getJumlah() >= 1) {
                currentBarang.kurangiJumlah();
                barangViewModel.update(currentBarang);
            }
        });

        findViewById(R.id.btnHapus).setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Konfirmasi Hapus")
                    .setMessage("Apakah Anda yakin ingin menghapus barang ini?")
                    .setPositiveButton("Ya", (dialog, which) -> {
                        barangViewModel.delete(currentBarang);
                        finish();
                    })
                    .setNegativeButton("Batal", null)
                    .show();
        });

        findViewById(R.id.btnBack).setOnClickListener(v -> {
            finish();
        });

        findViewById(R.id.btnEdit).setOnClickListener(v -> {
            if (currentBarang != null) {
                Intent intent = new Intent(this, AddBarangActivity.class);
                intent.putExtra("id", currentBarang.getId());
                intent.putExtra("nama", currentBarang.getNama());
                intent.putExtra("harga", currentBarang.getHarga());
                intent.putExtra("jumlah", currentBarang.getJumlah());
                intent.putExtra("kategoriId", currentBarang.getKategori());
                intent.putExtra("namaKategori", txtNamaKategori.getText().toString());
                startActivityForResult(intent, REQUEST_EDIT);
            } else {
                Toast.makeText(this, "Barang belum dimuat", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadBarangData() {
        barangViewModel.getBarangById(barangId).observe(this, barang -> {
            if (barang != null) {
                currentBarang = barang;
                txtNama.setText(barang.getNama());
                txtNamaKategori.setText(namaKategori);
                txtJumlah.setText(String.valueOf(barang.getJumlah()));
                txtHarga.setText("Rp. " + barang.getHarga());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_EDIT && resultCode == RESULT_OK) {
            loadBarangData();
        }
    }
}