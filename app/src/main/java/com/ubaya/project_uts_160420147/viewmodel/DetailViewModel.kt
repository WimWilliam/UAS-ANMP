package com.ubaya.project_uts_160420147.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ubaya.project_uts_160420147.model.Game
import com.ubaya.project_uts_160420147.model.News
import com.ubaya.project_uts_160420147.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    private var job = Job()
    private val db = buildDb(getApplication())
    val gameLD = MutableLiveData<Game?>()
    val newsLD = MutableLiveData<List<News>>()

    override val coroutineContext
        get() = job + Dispatchers.IO

    fun fetch(gameId: Int) {
        launch {
            val game = db.gameDao().selectGameById(gameId.toInt())
            val newsList = db.gameDao().selectNewsByGameId(gameId.toInt())
            withContext(Dispatchers.Main) {
                gameLD.value = game
                newsLD.value = newsList
            }
        }
    }
    fun fetchNews(gameId: Int){
        launch {
            val newsList = db.gameDao().selectNewsByGameId(gameId)
            newsLD.postValue(newsList)
        }
    }

    fun createNews(list: List<News>) {
        launch {
            db.gameDao().insertNews(*list.toTypedArray())
            refreshNews()
        }
    }

    private fun refreshNews() {
        launch {
            val news = db.gameDao().getAllNews()
            withContext(Dispatchers.Main) {
                newsLD.value = news
            }
        }
    }
}
