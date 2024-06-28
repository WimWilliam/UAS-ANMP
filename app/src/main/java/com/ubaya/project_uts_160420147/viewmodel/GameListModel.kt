package com.ubaya.project_uts_160420147.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.ubaya.project_uts_160420147.model.Game
import com.ubaya.project_uts_160420147.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class GameListModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val gameLD = MutableLiveData<List<Game>>()
    val gameLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val db = buildDb(getApplication())
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun createGame(list: List<Game>) {
        launch {
            db.gameDao().insertGame(*list.toTypedArray())
        }
    }

    fun refresh() {
        launch {
            try {
                val games = db.gameDao().getAllGames() // Assume this returns LiveData<List<Game>>
                gameLD.postValue(games) // Assign LiveData value to MutableLiveData
                gameLoadErrorLD.postValue(false)
                loadingLD.postValue(false)
            } catch (e: Exception) {
                gameLoadErrorLD.postValue(true)
                loadingLD.postValue(true)
            }
        }
    }
}
