package com.example.stock.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.stock.data.BarangWithKategori;
import com.example.stock.data.Barang;
import com.example.stock.data.BarangRepository;

import java.util.List;

// BarangViewModel.java
public class BarangViewModel extends AndroidViewModel {
    private BarangRepository repository;
    private LiveData<List<Barang>> barangByKategori;

    public BarangViewModel(@NonNull Application application) {
        super(application);
        repository = new BarangRepository(application); // Inisialisasi repository
    }

    public LiveData<List<Barang>> getBarangByKategori(int kategoriId) {
        barangByKategori = repository.getBarangByKategori(kategoriId);
        return barangByKategori;
    }

    public LiveData<Barang> getBarangById(int barangId) {
        return repository.getBarangById(barangId);
    }

    public LiveData<BarangWithKategori> getBarangWithKategoriById(int barangId) {
        return repository.getBarangWithKategoriById(barangId); // Pindahkan ke repository
    }

    public void insert(Barang barang) {
        repository.insert(barang);
    }

    public void delete(Barang barang) {
        repository.delete(barang);
    }

    public void update(Barang barang) {
        repository.update(barang);
    }
}