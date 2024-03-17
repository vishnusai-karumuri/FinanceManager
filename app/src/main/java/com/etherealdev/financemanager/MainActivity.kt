package com.etherealdev.financemanager

import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import com.etherealdev.financemanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_layout)

        button = findViewById(R.id.button)

        button.text = getString(R.string.button_text)
        button.setTextColor(Color.GREEN)
        button.setBackgroundColor(Color.BLACK)
    }
}