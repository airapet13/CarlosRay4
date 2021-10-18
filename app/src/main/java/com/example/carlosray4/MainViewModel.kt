package com.example.carlosray4

import android.util.Log
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    init {
        Log.e("AAA","VM created")
    }

    override fun onCleared() {
        Log.e("AAA","VM cleated")
        super.onCleared()
    }

}