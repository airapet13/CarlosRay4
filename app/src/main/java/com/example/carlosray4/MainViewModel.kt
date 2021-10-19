package com.example.carlosray4

import android.content.Context
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class MainViewModel(private val context: Context) : ViewModel() {

    var resultLiveMutable = MutableLiveData<String>()
    var resultLive: LiveData<String> = resultLiveMutable
    var a: String = ""
    val url = "https://api.icndb.com/jokes/random?"

    fun getJoke(count: Int) {
        var jokes = ""
        val queue = Volley.newRequestQueue(context)
        for (i in 1..count) {
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                Response.Listener<String?> { response ->
                    a = response.toString()
                },
                Response.ErrorListener { a = "That didn't work!" })
            queue.add(stringRequest)
            try {
                Log.e("AAA", "Шутка: $a")
                val jsonObject = JSONObject(a)
                jokes = jokes + jsonObject.getJSONObject("value").getString("joke") + "\n\n"
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        resultLiveMutable.value = jokes.replace("&quot;", "\"")
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