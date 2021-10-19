package com.example.carlosray4

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(context: Context) : ViewModelProvider.Factory {

    val a = context

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(context = a) as T
    }

}