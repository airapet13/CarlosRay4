package com.example.carlosray4.ui.home

import androidx.lifecycle.*
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class HomeViewModel() : ViewModel() {

    var resultLiveMutable = MutableLiveData<List<String>>()
    var resultLive: LiveData<List<String>> = resultLiveMutable

    fun getJoke(count: String) {
        if(count != ""){
            for (i in 1..count.toInt()) {
                getJoke1()
            }
            Thread.sleep(1_500)
            resultLiveMutable.value = data
            data = mutableListOf<String>()
        }
        else{
            resultLiveMutable.value = listOf("Count must not be empty")
        }
    }
}
var data = mutableListOf<String>()
val okHttpClient = OkHttpClient()
fun getJoke1() {
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
                    val jsonObject = JSONObject(response.body!!.string())
                    data.add(
                        jsonObject.getJSONObject("value").getString("joke").replace("&quot;", "\"")
                    )
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        }
    })
}