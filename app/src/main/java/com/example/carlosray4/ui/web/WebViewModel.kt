package com.example.carlosray4.ui.web

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WebViewModel : ViewModel() {
    var resultLiveMutable = MutableLiveData<String>()
    fun getURL(){
        resultLiveMutable.value = "https://www.icndb.com/api/"
    }
}