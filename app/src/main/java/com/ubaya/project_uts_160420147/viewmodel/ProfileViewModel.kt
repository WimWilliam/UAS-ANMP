package com.ubaya.project_uts_160420147.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ubaya.project_uts_160420147.model.Akun
import com.ubaya.project_uts_160420147.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ProfileViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

    private val db = buildDb(getApplication())
    val akunLD = MutableLiveData<Akun?>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun register(list: List<Akun>) {
        launch {
            db.gameDao().insertAkun(*list.toTypedArray())
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) { // Menggunakan Dispatchers.IO untuk akses database di thread yang sesuai
            val akun = db.gameDao().cekLogin(username, password)
            akunLD.postValue(akun)
            akun?.let {
                // Save the user ID to shared preferences
                val sharedPreferences = getApplication<Application>().getSharedPreferences("user_prefs", 0)
                with(sharedPreferences.edit()) {
                    putInt("user_id", it.id)
                    apply()
                }
            }
        }
    }

    fun updateProfile(akunId: Int, firstName: String, lastName: String, password: String) {
        launch {
            db.gameDao().updateAkun(akunId,firstName,lastName, password)
        }
    }

    fun getAkunById(akunId: Int) {
        viewModelScope.launch {
            val akunLiveData = db.gameDao().selectAkunById(akunId)
            akunLiveData.observeForever { akun ->
                akunLD.postValue(akun)
            }
        }
    }

}
