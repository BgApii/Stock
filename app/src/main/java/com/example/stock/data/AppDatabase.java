package com.example.stock.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Kategori.class, Barang.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract KategoriDao kategoriDao();
    public abstract BarangDao barangDao();

    private static AppDatabase INSTANCE;

    // Executor untuk menjalankan operasi database di latar belakang
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "stock_database")
                    .allowMainThreadQueries() // Sementara untuk testing, sebaiknya pakai AsyncTask/Coroutine untuk produksi
                    .fallbackToDestructiveMigration() // Jika ada perubahan skema, database di-reset
                    .build();
        }
        return INSTANCE;
    }
}