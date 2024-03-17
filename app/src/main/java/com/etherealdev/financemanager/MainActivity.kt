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
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.etherealdev.financemanager.databinding.ActivityMainBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.Firebase
import com.google.firebase.analytics.analytics

class MainActivity : AppCompatActivity() {

    private lateinit var mybutton: Button
    private lateinit var myTextView: TextView
    private lateinit var onClickbutton: Button
    private lateinit var showNameButton: Button
    private lateinit var editText: EditText
    private lateinit var analytics: FirebaseAnalytics
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        setContentView(R.layout.activity_main)

        // Obtain the FirebaseAnalytics instance.
        analytics = Firebase.analytics


        mybutton = findViewById(R.id.main_button)
        myTextView = findViewById(R.id.main_textview)
        onClickbutton = findViewById(R.id.onclicklistener_button)
        editText = findViewById(R.id.user_name)
        showNameButton = findViewById(R.id.button_show_name)


        //mybutton.text = getString(R.string.button_text)
        mybutton.setTextColor(Color.GREEN)
        mybutton.setBackgroundColor(Color.BLACK)
        onClickbutton.setTextColor(Color.GREEN)
        onClickbutton.setBackgroundColor(Color.BLACK)
        showNameButton.setTextColor(Color.GREEN)
        showNameButton.setBackgroundColor(Color.BLACK)
        //mybutton.setText("Show")

        onClickbutton.setOnClickListener{
            myTextView.visibility = View.VISIBLE
            myTextView.setTextColor(Color.MAGENTA)
            myTextView.text = "On Click Event Listener"
        }
        binding.bindingButton.setOnClickListener {
            myTextView.visibility = View.VISIBLE
            myTextView.setTextColor(Color.MAGENTA)
            myTextView.text = "Binding Working"
        }

        showNameButton.setOnClickListener{
            if (editText.text.toString().isNotEmpty()){
                val input = editText.text.toString()
                myTextView.visibility = View.VISIBLE
                myTextView.setTextColor(Color.MAGENTA)
                myTextView.text = input
            }
            else{
                myTextView.visibility = View.VISIBLE
                myTextView.setTextColor(Color.RED)
                myTextView.text = "No Text Entered"
            }
        }

    }
    fun showMessage(view: View)
    {
        myTextView.visibility = View.VISIBLE
        myTextView.setTextColor(Color.MAGENTA)
        myTextView.text = "Button Function"
    }
}