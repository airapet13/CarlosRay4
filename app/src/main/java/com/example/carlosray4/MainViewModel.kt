package com.example.carlosray4

import android.content.Context
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.InetAddress

class MainViewModel(private val context: Context) : ViewModel() {

    var resultLiveMutable = MutableLiveData<String>()
    var resultLive: LiveData<String> = resultLiveMutable
    var a: String = ""
    val url = "https://api.icndb.com/jokes/random?"

    fun getJoke(count: Int) {
//        var myArray = arrayOf("")
        resultLiveMutable.value = ""
        for (i in 1..count) {
            resultLiveMutable.value = resultLiveMutable.value + getJoke1()
        }



//        resultLiveMutable.value = jokes.replace("&quot;", "\"")
//
    }

    init {
        Log.e("AAA","VM created")
        getJoke(1)
    }

    override fun onCleared() {
        Log.e("AAA","VM cleared")
        super.onCleared()
    }
}
var a = ""
fun getJoke1(): String {
    var jokes = ""
    val okHttpClient = OkHttpClient()
    val request = Request.Builder()
        .url("https://api.icndb.com/jokes/random?")
        .build()

    okHttpClient.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {

        }

        override fun onResponse(call: Call, response: Response) {
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            for ((name, value) in response.headers) {
                println("$name: $value")
            }
            a = response.body!!.string()
            Log.e("AAA", "Response ->    $response")
            Log.e("AAA", "Хрень под принтлном $a")
        }
    })
    try {
        Log.e("AAA", "Шутка: $a")
        val jsonObject = JSONObject(a)
        jokes = jsonObject.getJSONObject("value").getString("joke") + "\n\n"
    } catch (e: JSONException) {
        e.printStackTrace()
    }
    return jokes
}