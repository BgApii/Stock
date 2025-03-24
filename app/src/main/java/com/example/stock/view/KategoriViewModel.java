package com.example.stock.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.stock.data.Kategori;
import com.example.stock.data.KategoriRepository;

import java.util.List;

// KategoriViewModel.java
public class KategoriViewModel extends AndroidViewModel {
    private KategoriRepository repository;
    private LiveData<List<Kategori>> allKategori;

    public KategoriViewModel(@NonNull Application application) {
        super(application);
        repository = new KategoriRepository(application);
        allKategori = repository.getAllKategori();
    }

    public LiveData<List<Kategori>> getAllKategori() {
        return allKategori;
    }

    public void insert(Kategori kategori) {
        repository.insert(kategori);
    }
    public void delete(Kategori kategori) {
        repository.delete(kategori);
    }
}