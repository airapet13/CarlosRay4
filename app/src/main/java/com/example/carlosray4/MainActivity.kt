package com.example.carlosray4


import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carlosray4.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {


    private lateinit var vm: MainViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(binding.root)

        Log.e("AAA","Activity created")

        vm = ViewModelProvider(this, MainViewModelFactory(this))
            .get(MainViewModel::class.java)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_web
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        val reload = findViewById<Button>(R.id.reload)
        val count = findViewById<TextView>(R.id.count)
        val jokes = findViewById<RecyclerView>(R.id.jokes)
        var data = mutableListOf<String>()
        jokes.layoutManager = LinearLayoutManager(this)
//        jokes.adapter = CustomRecyclerAdapter(data)


        vm.resultLive.observe(this, Observer {
//            jokes.text = it
            jokes.adapter = CustomRecyclerAdapter(it)
        })

        reload.setOnClickListener {
            vm.getJoke(count.text.toString().toInt())
//            jokes.adapter = CustomRecyclerAdapter(vm.getJoke(count.text.toString().toInt()))
        }
    }
}