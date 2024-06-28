package com.ubaya.project_uts_160420147.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ubaya.project_uts_160420147.util.DB_NAME
import com.ubaya.project_uts_160420147.util.MIGRATION_1_2
import com.ubaya.project_uts_160420147.util.MIGRATION_2_3

@Database(entities = [Akun::class, Game::class, News::class], version = 4) // Sesuaikan versi menjadi 4
abstract class GameDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao

    companion object {
        @Volatile
        private var instance: GameDatabase? = null
        private val LOCK = Any()

        fun getDatabase(context: Context): GameDatabase {
            return instance ?: synchronized(LOCK) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): GameDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                GameDatabase::class.java,
                DB_NAME
            )
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3) // Tambahkan migrasi dari versi 3 ke 4 di sini
                .fallbackToDestructiveMigration() // Tambahkan ini untuk fallback ke migrasi merusak
                .build()
        }
    }
}
