package com.example.stock.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BarangDao {
    @Insert
    void insertBarang(Barang barang);

    @Query("SELECT * FROM barang WHERE kategoriId = :kategoriId ORDER BY namaBarang ASC")
    LiveData<List<Barang>> getBarangByKategori(int kategoriId);

    @Delete
    void deleteBarang(Barang barang);

    @Update
    void updateBarang(Barang barang);

    @Query("DELETE FROM barang WHERE kategoriId = :kategoriId")
    void deleteBarangByKategori(int kategoriId);

    @Query("SELECT * FROM barang WHERE id = :barangId LIMIT 1")
    LiveData<Barang> getBarangById(int barangId);

    @Query("SELECT barang.*, kategori.nama_kategori AS namaKategori FROM barang " +
            "INNER JOIN kategori ON barang.kategoriId = kategori.id WHERE barang.id = :barangId")
    LiveData<BarangWithKategori> getBarangWithKategoriById(int barangId);
}