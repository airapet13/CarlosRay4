package com.example.carlosray4

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class MainViewModel(private val context: Context) : ViewModel() {

    var resultLiveMutable = MutableLiveData<List<String>>()
    var resultLive: LiveData<List<String>> = resultLiveMutable
    var a: String = ""
    val url = "https://api.icndb.com/jokes/random?"

    fun getJoke(count: Int) {
        for (i in 1..count) {
            getJoke1()
            }
        Thread.sleep(1_500)  // wait 1.5 second

        Log.e("AAA", "Data: $data")

        resultLiveMutable.value = data
        data = mutableListOf<String>()
    }

    init {
        Log.e("AAA","VM created")
    }

    override fun onCleared() {
        Log.e("AAA","VM cleared")
        super.onCleared()
    }
}
var  a = ""
var data = mutableListOf<String>()
val okHttpClient = OkHttpClient()
fun getJoke1(){
    val request = Request.Builder()
        .url("https://api.icndb.com/jokes/random?")
        .build()

    okHttpClient.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            response.use {
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                for ((name, value) in response.headers) {
                    println("$name: $value")
                }

                try {
                    Log.e("AAA", "Шутка: $response.body!!.string()")
                    val jsonObject = JSONObject(response.body!!.string())
                    data.add(jsonObject.getJSONObject("value").getString("joke").replace("&quot;", "\""))
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                Log.e("AAA", "Data in okhttp: $data")
            }
        }
    })
}