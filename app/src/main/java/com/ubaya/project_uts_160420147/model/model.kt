package com.ubaya.project_uts_160420147.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Akun(
    @ColumnInfo(name = "username")
    val username:String,
    @ColumnInfo(name = "password")
    var password:String,
    @ColumnInfo(name = "first_name")
    var firstName:String,
    @ColumnInfo(name = "last_name")
    var lastName:String,
    @ColumnInfo(name = "email")
    var email:String,
    @ColumnInfo(name = "noHp")
    var noHp:String,

) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
@Entity
data class Game(
    @ColumnInfo(name="title")
    val title: String,
    @ColumnInfo(name="developer")
    val developer: String,
    @ColumnInfo(name="genre")
    val genre: String,
    @ColumnInfo(name="deskripsi_singkat")
    val description: String,
    @ColumnInfo(name="image_url")
    val imageURL: String
) {
    @PrimaryKey(autoGenerate = true)
    var gameId: Int = 0
}
@Entity(tableName = "detailGames")
data class News(


    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "author")
    val author: String,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "deskripPanjang")
    val deskrip: String,

    @ColumnInfo(name = "newsGame")
    val idnewsGame: Int
){
    @PrimaryKey(autoGenerate = true)
    var newsId: Int =0
}




