package com.ubaya.project_uts_160420147.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ubaya.project_uts_160420147.model.News

class SharedViewModel : ViewModel() {
    val lastVisitedGame = MutableLiveData<News?>()

    fun setLastVisitedGame(news: List<News>) {
        lastVisitedGame.value = news.firstOrNull()
    }
}

