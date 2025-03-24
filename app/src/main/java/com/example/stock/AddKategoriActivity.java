package com.example.stock;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.stock.data.Kategori;
import com.example.stock.view.KategoriViewModel;

public class AddKategoriActivity extends AppCompatActivity {
    private EditText edtNamaKategori;
    private Button btnSave;
    private KategoriViewModel kategoriViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kategori);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        edtNamaKategori = findViewById(R.id.edtNamaKategori);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setEnabled(false);

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

        edtNamaKategori.addTextChangedListener(textWatcher);
        kategoriViewModel = new ViewModelProvider(this).get(KategoriViewModel.class);

        btnSave.setOnClickListener(view -> {
            String namaKategori = edtNamaKategori.getText().toString();
            if (!namaKategori.isEmpty()) {
                Kategori kategori = new Kategori(namaKategori);
                kategoriViewModel.insert(kategori);
                finish();
            } else {
                Toast.makeText(this, "Nama kategori tidak boleh kosong!", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btnBack).setOnClickListener(v -> {
            finish();
        });
    }
    private void checkInputFields() {
        String namaKategori = edtNamaKategori.getText().toString().trim();


        if (!namaKategori.isEmpty()) {
            btnSave.setEnabled(true);
            btnSave.setBackgroundResource(R.drawable.btn_background);
        } else {
            btnSave.setEnabled(false);
            btnSave.setBackgroundResource(R.drawable.btn_background);
        }
    }
}