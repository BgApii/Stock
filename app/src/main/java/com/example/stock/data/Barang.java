package com.example.stock.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "barang",
        foreignKeys = @ForeignKey(
                entity = Kategori.class,
                parentColumns = "id",
                childColumns = "kategoriId",
                onDelete = ForeignKey.CASCADE // Barang akan terhapus jika kategori terkait dihapus
        ),
        indices = @Index("kategoriId") // Index untuk mempercepat query
)
public class Barang {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String namaBarang;
    public int hargaBarang;
    public int jumlahBarang;

    @ColumnInfo(name = "kategoriId")
    public int kategoriId;

    public Barang(String namaBarang, int hargaBarang, int jumlahBarang, int kategoriId) {
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
        this.jumlahBarang = jumlahBarang;
        this.kategoriId = kategoriId;
    }

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }
    public String getNama() { return namaBarang; }
    public int getHarga() { return hargaBarang; }
    public int getJumlah() { return jumlahBarang; }
    public int getKategori() { return kategoriId; }
    public void kurangiJumlah() {
        if (jumlahBarang > 0) {
            jumlahBarang--;
        }
    }
}
