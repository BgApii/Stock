package com.example.stock.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class KategoriRepository {
    private KategoriDao kategoriDao;
    private LiveData<List<Kategori>> allKategori;

    public KategoriRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        kategoriDao = db.kategoriDao();
        allKategori = kategoriDao.getAllKategori();
    }

    public LiveData<List<Kategori>> getAllKategori() {
        return allKategori;
    }

    public void insert(Kategori kategori) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            kategoriDao.insertKategori(kategori);
        });
    }
    public void delete(Kategori kategori) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            kategoriDao.deleteKategori(kategori);
        });
    }
}