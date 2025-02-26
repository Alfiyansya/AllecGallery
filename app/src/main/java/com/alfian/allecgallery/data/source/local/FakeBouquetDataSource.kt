package com.alfian.allecgallery.data.source.local

import com.alfian.allecgallery.R
import com.alfian.allecgallery.domain.model.Bouquet


object FakeBouquetDataSource {
    val dummyBouquets = listOf(
        Bouquet(
            bouquetId = 1,
            name = "Bouquet Bunga Hijau Besar",
            image = R.drawable.bouquet1,
            description = "Bouquet Bunga Hijau Besar adalah sebuah buket berukuran besar" +
                    " yang berisi beberapa bunga berwarna hijau",
            bouquetPrice = 100000
        ),
        Bouquet(
            bouquetId = 2,
            name = "Bouquet Bunga Pink Putih Sedang",
            image = R.drawable.bouquet2,
            description = "Bouquet Bunga Pink Putih Sedang adalah sebuah buket berukuran sedang" +
                    " yang berisi beberapa bunga berwarna pink dan putih",
            bouquetPrice = 50000
        ),
        Bouquet(
            bouquetId = 3,
            name = "Bouquet Bunga Pink Putih Besar",
           image =  R.drawable.bouquet3,
            description = "Bouquet Bunga Pink Putih Besar adalah sebuah buket berukuran besar" +
                    " yang berisi beberapa bunga berwarna pink dan putih",
            bouquetPrice = 120000
        ),
        Bouquet(
            bouquetId = 4,
            name = "Bouquet Bunga Putih Besar",
            image = R.drawable.bouquet4,
            description = "Bouquet Bunga Putih Besar adalah sebuah buket berukuran besar" +
                    " yang berisi beberapa bunga berwarna putih",
            bouquetPrice = 150000
        ),
        Bouquet(
            bouquetId = 5,
            name = "Bouquet Snack Besar",
            image = R.drawable.bouquet5,
            description = "Bouquet Snack Besar adalah sebuah buket berukuran besar" +
                    " yang berisi beberapa snack yang enak",
            bouquetPrice = 110000
        ),
        Bouquet(
            bouquetId = 6,
            name = "Bouquet Bunga Pink besar",
            image = R.drawable.bouquet6,
            description = "Bouquet Bunga Pink Besar adalah sebuah buket berukuran besar" +
                    " yang berisi beberapa bunga berwarna pink",
            bouquetPrice = 90000
        ),
        Bouquet(
            bouquetId = 7,
            name = "Bouquet Bunga Warna-Warni Sedang",
            R.drawable.bouquet7,
            "Bouquet Bunga Warna-Warni Sedang adalah sebuah buket berukuran sedang" +
                    " yang berisi beberapa bunga berwarna-warni",
            70000
        ),
        Bouquet(
            bouquetId = 8,
            name = "Bouquet Bunga Boneka Putih Besar ",
           image = R.drawable.bouquet8,
           description = "Bouquet Bunga Putih Besar adalah sebuah buket berukuran besar" +
                   " yang berisi beberapa bunga berwarna putih dan boneka",
           bouquetPrice = 120000
        ),
        Bouquet(
            bouquetId = 9,
            name = "Bouquet Bunga Warna-Warni Kecil",
           image =  R.drawable.bouquet9,
           description =  "Bouquet Bunga Warna-Warni Kecil adalah sebuah buket berukuran kecil" +
                   " yang berisi beberapa bunga berwarna-warni",
           bouquetPrice =  85000
        ),
        Bouquet(
            bouquetId = 10,
            name = "Bouquet Bunga Wisuda Sedang",
           image =  R.drawable.bouquet10,
           description =  "Bouquet Bunga Wisuda Sedang adalah sebuah buket berukuran sedang" +
                   " yang berisi beberapa bunga dan miniatur orang wisuda",
           bouquetPrice =  150000
        ),

    )
}