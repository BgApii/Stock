# 📦 Aplikasi Manajemen Stok Barang
Selamat datang di **Aplikasi Stock**! Aplikasi ini dibuat menggunakan **Java** dan **Android Studio** untuk membantu Anda mengelola barang dan kategori dengan mudah dan efisien.

---

## 🚀 Fitur Utama
- **Tambah Kategori:** Memungkinkan penambahan kategori baru untuk pengelompokan barang.
- **Hapus Kategori:** Menghapus kategori beserta barang di dalamnya.
- **Tambah Barang:** Menyimpan data barang beserta harga dan jumlah stok yang tersedia.
- **Edit Barang:** Ubah data barang yang sudah ada.
- **Hapus Barang:** Menghapus barang dari kategori terkait.
- **Penjualan Barang:** Mengurangi jumlah stok barang.

---

## 🛠️ Struktur Folder
- `data/` - Berisi model data untuk **Barang** dan **Kategori**.
- `view/` - Berisi ViewModel dan Adapter untuk menampilkan data.
- `activity/` - Berisi Activity untuk setiap layar aplikasi.

---

## 🔧 Instalasi & Penggunaan
### Prasyarat
Pastikan Anda sudah menginstal:
- **Android Studio** (disarankan versi terbaru)
- **Java Development Kit (JDK)**
- **Gradle** (biasanya sudah terintegrasi dalam Android Studio)

### Instalasi
1. Clone repository ini menggunakan perintah berikut:
   ```bash
   git clone https://github.com/BgApii/Stock.git
   ```
2. Buka project dengan **Android Studio**.
3. Pastikan semua dependency sudah terunduh. Buka file **build.gradle** dan klik **Sync Now** jika ada notifikasi sinkronisasi.
4. Pastikan SDK Android terbaru telah terpasang dan pilih **API Level** yang sesuai.
5. Hubungkan perangkat Android atau gunakan emulator.
6. Jalankan aplikasi dengan klik tombol **Run** (Shift + F10).


### Penggunaan
1. Buka aplikasi setelah instalasi berhasil.
2. Navigasi melalui menu utama untuk memilih fitur yang diinginkan.
3. Tambahkan kategori atau barang baru sesuai kebutuhan.
4. Edit atau hapus data barang yang sudah ada jika diperlukan.
5. Lakukan penjualan barang dan pastikan stok terupdate dengan baik.

---

## 🎯 Cara Menggunakan
1. **Menambah Kategori:**
   - Klik tombol **+** di halaman utama.
   - Isi nama kategori dan simpan.
2. **Menghapus Kategori (beserta barang di dalamnya):**
   - Klik icon trash.
   - Dan konfirmasi penghapusan.
3. **Menambah Barang:**
   - Pilih kategori yang diinginkan.
   - Klik tombol **+**, isi data barang (nama, harga, jumlah stok), dan simpan.
4. **Mengedit Barang:**
   - Pilih barang dari daftar.
   - Klik tombol **Edit**, ubah data yang diinginkan, lalu simpan.
5. **Menghapus Barang:**
   - Pilih barang dari daftar.
   - Klik tombol **Hapus** dan konfirmasi penghapusan.
6. **Penjualan Barang:**
   - Pilih barang yang ingin dijual.
   - Klik tombol **Jual**, masukkan jumlah yang terjual, dan stok otomatis berkurang.

---

## 📱 Teknologi yang Digunakan
- **Java** sebagai bahasa pemrograman utama.
- **Room Database** untuk penyimpanan data secara lokal.
- **Android ViewModel** untuk pengelolaan data.
- **RecyclerView** untuk menampilkan daftar barang dan kategori.
- **LiveData** untuk pembaruan UI secara real-time.

---

## 📝 Catatan Tambahan
- Pastikan perangkat Anda memiliki minimal **Android 7.0 (API 24)**.
- Aplikasi ini mendukung mode offline dengan data yang disimpan secara lokal.

---

**Created By Apri Supandi Pasaribu**

---
Selamat mencoba! 🎉

