package ru.kpfu.itis.android.news.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import ru.kpfu.itis.android.news.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpNavigation()
    }

    private fun setUpNavigation() {
        val navController = findNavController(R.id.fragment_host)
        setupActionBarWithNavController(navController)
        nav_view.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp() = findNavController(R.id.fragment_host).navigateUp()
}
