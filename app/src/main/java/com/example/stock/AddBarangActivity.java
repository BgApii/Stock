package com.example.stock;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.stock.data.Barang;
import com.example.stock.view.BarangViewModel;

public class AddBarangActivity extends AppCompatActivity {
    private EditText edtNamaBarang, edtHargaBarang, edtJumlahBarang, edtNamaKategori;
    private Button btnSave;
    private BarangViewModel barangViewModel;
    private int kategoriId;
    private int barangId = -1; // Default -1 untuk menandakan mode tambah baru

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_barang);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        edtNamaBarang = findViewById(R.id.edtNamaBarang);
        edtHargaBarang = findViewById(R.id.edtHargaBarang);
        edtJumlahBarang = findViewById(R.id.edtJumlahBarang);
        edtNamaKategori = findViewById(R.id.edtNamaKategori);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setEnabled(false);

        // Cek apakah ada data yang dikirim (mode edit)
        if (getIntent().hasExtra("id")) {
            barangId = getIntent().getIntExtra("id", -1);
            String nama = getIntent().getStringExtra("nama");
            int harga = getIntent().getIntExtra("harga", 0);
            int jumlah = getIntent().getIntExtra("jumlah", 0);
            String namaKategori = getIntent().getStringExtra("namaKategori");

            edtNamaBarang.setText(nama);
            edtHargaBarang.setText(String.valueOf(harga));
            edtJumlahBarang.setText(String.valueOf(jumlah));
            edtNamaKategori.setText(namaKategori);
        }
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputFields();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        edtNamaBarang.addTextChangedListener(textWatcher);
        edtHargaBarang.addTextChangedListener(textWatcher);
        edtJumlahBarang.addTextChangedListener(textWatcher);

        kategoriId = getIntent().getIntExtra("kategoriId", -1);
        edtNamaKategori.setText(getIntent().getStringExtra("namaKategori"));

        barangViewModel = new ViewModelProvider(this).get(BarangViewModel.class);

        btnSave.setOnClickListener(view -> {
            String namaBarang = edtNamaBarang.getText().toString().trim();
            String hargaStr = edtHargaBarang.getText().toString().trim();
            String jumlahStr = edtJumlahBarang.getText().toString().trim();


            if (namaBarang.isEmpty() || hargaStr.isEmpty() || jumlahStr.isEmpty()) {
                Toast.makeText(this, "Semua bidang harus diisi!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!namaBarang.isEmpty()) {
                Barang barang = new Barang(namaBarang, Integer.parseInt(hargaStr), Integer.parseInt(jumlahStr), kategoriId);

                try {
                    int harga = Integer.parseInt(hargaStr);
                    int jumlah = Integer.parseInt(jumlahStr);

                    if (barangId != -1) {  // Mode Edit
                        barang.setId(barangId);
                        barangViewModel.update(barang);
                    } else {  // Mode Tambah Baru
                        barangViewModel.insert(barang);
                    }
                    setResult(RESULT_OK);
                    finish();
                } catch (NumberFormatException e){
                    Toast.makeText(this, "Isi semua data dengan benar!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.btnBack).setOnClickListener(v -> {
            finish();
        });
    }
    private void checkInputFields() {
        String nama = edtNamaBarang.getText().toString().trim();
        String harga = edtHargaBarang.getText().toString().trim();
        String jumlah = edtJumlahBarang.getText().toString().trim();


        if (!nama.isEmpty() && !harga.isEmpty() && !jumlah.isEmpty()) {
            btnSave.setEnabled(true);
            btnSave.setBackgroundResource(R.drawable.btn_background);
        } else {
            btnSave.setEnabled(false);
            btnSave.setBackgroundResource(R.drawable.btn_background);
        }
    }
}