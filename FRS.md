_Functional Requirements Specifications_ e-Juklak
=================================================

Dokumen ini menjelaskan kebutuhan fungsional dari aplikasi e-Juklak yang akan
dibangun.

Pendahuluan
-----------

Saat ini Petunjuk Pelaksanaan FTIS Unpar tersedia dalam bentuk buku. Proyek ini
membuat aplikasi e-Juklak yang memungkinkan juklak diakses secara elektronik.

`FRS001` Aplikasi e-Juklak dibuat dalam salah satu dari bentuk berikut:

* Aplikasi Android
* Aplikasi iPhone
* Aplikasi Windows Phone
* Responsive Web Site (dapat dibaca dengan mudah di layar kecil)

Format Juklak
-------------

Juklak milik FTIS dibuat dalam format Microsoft Word, sehingga tidak cocok untuk
dibaca di layar yang kecil. Oleh karena itu, dokumen Juklak tersebut harus
dibersihkan dulu, dalam proyek ini menggunakan format Markdown, dengan kata
lain:

`FRS002` Dalam repository e-Juklak, harus tersedia Juklak FTIS dalam format
[Markdown](https://guides.github.com/features/mastering-markdown/) supaya
konten aman untuk ditampilkan di layar kecil. Pada aplikasi, format konten
bebas (HTML disarankan).

`FRS003` Pada aplikasi, konten disimpan dalam format HTML, yang dibangkitkan
secara otomatis dari file berformat Markdown.

Tips:

* Tidak ada cara otomatis untuk mengubah format Microsoft Word menjadi Markdown.
  Anda dapat mengexport menjadi `.txt` tetapi tetap harus diperbaiki secara
  manual.
* Untuk mengkonversi format Markdown menjadi HTML atau lainnya, Anda dapat
  memanfaatkan program [Pandoc](http://johnmacfarlane.net/pandoc/).

Fitur Aplikasi
--------------

`FRS004` Aplikasi dapat menampilkan isi Juklak pada ukuran layar yang
berbeda-beda.

`FRS005` Aplikasi tidak menampilkan daftar isi yang didapat dari file Microsoft
Word sumber.

`FRS006` Aplikasi membuat sendiri daftar isi berdasarkan konten HTMLnya, diambil
dari tag-tag `h1`, `h2`, dan seterusnya. Jika entri daftar isi ditekan,
layar langsung ter-scroll ke entri yang dituju.

`FRS010` Aplikasi baik web maupun mobile app harus dapat dijalankan secara offline
(tanpa koneksi internet).

Tips:

* Supaya HTML dapat secara otomatis ditampilkan dengan baik di layar yang
  berbeda-beda ukuran, dapat menambahkan baris berikut:
  `<meta name="viewport" content="width=device-width, initial-scale=1">`
  (penjelasan lengkap
  [di sini](https://developer.mozilla.org/en/docs/Mozilla/Mobile/Viewport_meta_tag))

Repositori
----------

`FRS007` Kode sumber dan file pendukung proyek harus disimpan di
[Github](https://github.com), yang diklon dari repositori
https://github.com/pascalalfadian/eJuklak

`FRS008` Struktur penyimpanan repositori mengikuti aturan berikut:

* `eJuklakApp/` berisi project Eclipse/XCode/Visual Studio untuk
  Android/iPhone/Windows Phone
* `eJuklakWeb/` berisi file-file PHP/HTML yang siap diupload pada web server,
  jika Anda membuat dalam bentuk web.
* `markdown/` berisi file-file markdown hasil konversi dari file Microsoft Word
  yang telah disediakan
* `word/` berisi file juklak dalam format Microsoft Word (sudah disediakan)
* `doc/` berisi dokumentasi proyek dan program
* `res/` dapat diisi dengan file-file lain yang mendukung proyek Anda, misalkan
  file `.psd`, `.sql`, dll
* `README.md` berisi deskripsi proyek Anda secara singkat.
* `FRS.md` (file ini) berisi keterangan kebutuhan program (sudah disediakan)

Milestones
==========

`FRS009` Aplikasi e-Juklak harus dirilis secara bertahap:

1. `v0.1-alpha`
    * Versi Markdown dari Juklak Bab 1 tersedia di repositori
    * Aplikasi sudah berjalan, berisi minimal 1 layar saja
2. `v0.2-alpha`
    * Versi Markdown dari Juklak Bab 1 & 2 tersedia di repositori
    * Sudah tersedia versi HTML Juklak hasil konversi dari Markdown
    * Aplikasi sudah berjalan dengan navigasi lengkap (isi tiap layar boleh
      _lorem ipsum_)
3. `v0.3-alpha`
    * Versi Markdown dan HTML dari Juklak Bab 1-3 tersedia di repositori
	* Aplikasi mampu menampilkan konten Juklak HTML hasil konversi Markdown
4. `v0.4-beta`
    * Versi Markdown dan HTML dari Juklak Bab 1-4 dan lampiran tersedia di
      repositori
    * Aplikasi mampu menampilkan daftar isi berdasarkan tag-tag header di HTML,
      dan dapat diklik untuk melompat ke halaman yang dituju
5. `v1.0` Versi final pertama dari aplikasi, tidak ada lagi fitur yang "belum
    diimplementasikan" atau "bug yang belum diperbaiki"
