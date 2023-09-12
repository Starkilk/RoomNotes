package com.pasha.roomsqlworkbeatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.pasha.roomsqlworkbeatch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
     lateinit var navController: NavController//иничиализировали навигацию

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        APP = this//инициализировали переменнтую из constants
        navController = Navigation.findNavController(this,R.id.nav_fragment)//привязали НавКонтроллер к фрагменту в активити
    }
}