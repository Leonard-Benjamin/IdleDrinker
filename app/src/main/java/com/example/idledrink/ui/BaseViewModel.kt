package com.example.idledrink.ui

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.idledrink.Utils
import com.example.idledrink.database.firebase.FireBaseManager
import com.example.idledrink.database.firebase.User

abstract class BaseViewModel<T>(@NonNull application: Application): AndroidViewModel(application) {
    var user: User
    var mutableList: MutableLiveData<ArrayList<T>> = MutableLiveData()
    var dataList: ArrayList<T> = ArrayList()
    val mFireBaseProvider = FireBaseManager.instance

    init {
        val name = Utils.getSharedPrefString("playerName", this.getApplication())
        val userID = Utils.getSharedPrefString("userId", this.getApplication())
        this.user = User(userID, name)
        this.populateList()
        mutableList.value = dataList

    }

    abstract fun populateList()
}