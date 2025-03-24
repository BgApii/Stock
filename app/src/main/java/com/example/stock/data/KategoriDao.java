package com.example.stock.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface KategoriDao {
    @Insert
    void insertKategori(Kategori kategori);

    @Delete
    void deleteKategori(Kategori kategori);

    @Query("SELECT * FROM kategori ORDER BY nama_kategori ASC")
    LiveData<List<Kategori>> getAllKategori();
}
