package com.example.stock.data;

import androidx.room.Embedded;

public class BarangWithKategori {
    @Embedded
    public Barang barang;

    public String namaKategori;
}
