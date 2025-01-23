**FoodApp**

FoodApp adalah aplikasi Android yang menampilkan berbagai jenis makanan dari API eksternal. Aplikasi ini menggunakan Retrofit untuk mendapatkan data makanan dari API dan Room untuk menyimpan makanan favorit pengguna secara offline. Aplikasi ini menggunakan arsitektur MVVM untuk memisahkan logika aplikasi dan tampilan.

Fitur
Tampilkan Daftar Makanan: Aplikasi mengambil data makanan dari API eksternal dan menampilkannya dalam daftar.
Detail Makanan: Pengguna dapat melihat detail dari setiap makanan yang ditampilkan.
Simpan Makanan ke Favorit: Pengguna dapat menandai makanan favorit, yang akan disimpan secara lokal menggunakan Room Database.
Tampilkan Makanan Favorit: Pengguna dapat melihat daftar makanan yang telah disimpan ke favorit bahkan tanpa koneksi internet.
Teknologi yang Digunakan
1. Retrofit: Untuk mengambil data makanan dari API eksternal.
2. Room: Untuk menyimpan dan mengelola data makanan favorit secara lokal.
3. MVVM (Model-View-ViewModel): Untuk memisahkan logika aplikasi dari tampilan, membuat aplikasi lebih mudah diuji dan dipelihara.
4. ViewModel & LiveData: Untuk mengelola data secara reaktif dan memperbarui tampilan UI secara efisien.
5. Coroutines: Untuk menangani operasi asynchronous secara efisien.
6. RecyclerView: Untuk menampilkan daftar makanan.
