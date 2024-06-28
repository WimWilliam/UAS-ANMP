package com.ubaya.project_uts_160420147.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAkun(vararg akun: Akun)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGame(vararg game: Game)

    @Query("SELECT * FROM Akun WHERE id = :akunId")
    fun selectAkunById(akunId: Int): LiveData<Akun?>

    @Query("SELECT * FROM Akun WHERE username = :username AND password = :password")
    fun cekLogin(username: String, password: String): Akun?

    @Query("SELECT * FROM Game")
    fun getAllGames(): List<Game>

    @Query("SELECT * FROM Game WHERE gameId = :gameId")
    fun selectGameById(gameId: Int): Game?

    @Query("SELECT * FROM detailGames WHERE newsGame = :gameId")
    fun selectNewsByGameId(gameId: Int): List<News>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(vararg news: News)
    @Query("SELECT * FROM detailGames")
    fun getAllNews(): List<News>

    @Query("UPDATE Akun SET first_name = :firstName, last_name = :lastName, password = :password WHERE id = :id")
    fun updateAkun(id: Int, firstName: String, lastName: String, password: String)

}
