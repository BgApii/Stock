package com.example.stock.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "kategori")
public class Kategori {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "nama_kategori")
    public String namaKategori;

    public Kategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }
    public String getKategori() { return namaKategori; }

}

