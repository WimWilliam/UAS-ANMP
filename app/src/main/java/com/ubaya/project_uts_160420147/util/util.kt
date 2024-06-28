package com.ubaya.project_uts_160420147.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.project_uts_160420147.R
import com.ubaya.project_uts_160420147.model.GameDatabase

const val DB_NAME = "new_uas_db"

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE detailGames ADD COLUMN newsGame INTEGER NOT NULL DEFAULT 0")
    }
}
val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE Akun ADD COLUMN nohp")
    }
}



fun ImageView.loadImage(url: String?, progressBar: ProgressBar) {
    progressBar.visibility = View.VISIBLE
    Picasso.get()
        .load(url)
        .fit()
        .centerCrop()
        .into(this, object : Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }
            override fun onError(e: Exception?) {
                progressBar.visibility = View.GONE
            }
        })
}

fun buildDb(context: Context): GameDatabase {
    return Room.databaseBuilder(
        context.applicationContext,
        GameDatabase::class.java,
        DB_NAME
    )
        .addMigrations(MIGRATION_1_2)
        .build()
}
