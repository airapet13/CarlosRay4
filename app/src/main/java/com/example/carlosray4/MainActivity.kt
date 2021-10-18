package com.example.carlosray4


import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.carlosray4.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.Exception
import org.json.JSONException

import org.json.JSONObject





class MainActivity : AppCompatActivity() {
    var a: String = ""

    private lateinit var vm: MainViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(binding.root)

        Log.e("AAA","Activity created")
        vm = ViewModelProvider(this).get(MainViewModel::class.java)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_web
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        val reload: Button = findViewById(R.id.reload)
        val count: TextView = findViewById(R.id.count)
        val jokes: TextView = findViewById(R.id.jokes)
        val URL = "https://api.icndb.com/jokes/random?"
        reload.setOnClickListener {
            jokes.text = ""
            getJoke(count.text.toString().toInt(), URL)
        }
    }

    fun getJoke(count: Int, url: String) {
        var x = 0
        val jokes: TextView = findViewById(R.id.jokes)
        while(x < count){
            x+=1
            val queue = Volley.newRequestQueue(this)
//        val url = "https://api.icndb.com/jokes/random?"
            val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
//                a = ("${response}")
                a = response.toString()
            },
            Response.ErrorListener { a = "That didn't work!"})
            queue.add(stringRequest)
            try {
                a = a.replace("&quot;", "\"")
                val jsonObject = JSONObject(a)
                jokes.text = jokes.text.toString() + jsonObject.getJSONObject("value").getString("joke") + "\n"
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }
}


