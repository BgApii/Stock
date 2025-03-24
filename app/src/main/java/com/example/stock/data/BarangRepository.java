package com.example.stock.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

// BarangRepository.java
public class BarangRepository {
    private BarangDao barangDao;
    private LiveData<List<Barang>> barangByKategori;

    public BarangRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        barangDao = db.barangDao(); // Inisialisasi barangDao
    }

    public LiveData<List<Barang>> getBarangByKategori(int kategoriId) {
        return barangDao.getBarangByKategori(kategoriId);
    }

    public LiveData<Barang> getBarangById(int barangId) {
        return barangDao.getBarangById(barangId);
    }

    public LiveData<BarangWithKategori> getBarangWithKategoriById(int barangId) {
        return barangDao.getBarangWithKategoriById(barangId); // Pindahkan ke repository
    }

    public void insert(Barang barang) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            barangDao.insertBarang(barang);
        });
    }

    public void delete(Barang barang) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            barangDao.deleteBarang(barang);
        });
    }

    public void update(Barang barang) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            barangDao.updateBarang(barang);
        });
    }
}