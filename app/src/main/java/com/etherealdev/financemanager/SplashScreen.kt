package com.etherealdev.financemanager

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.viewbinding.ViewBinding
import com.etherealdev.financemanager.databinding.ActivityMainBinding
import com.etherealdev.financemanager.databinding.NewLayoutBinding
import com.etherealdev.financemanager.ui.theme.FinanceManagerTheme
//import kotlinx.android.synthetic.main.new_layout.splash_image

private val ViewBinding.splash_image: ImageView
    get() {
        TODO("Not yet implemented")
    }

@SuppressLint("CustomSplashScreen")
class SplashScreen: AppCompatActivity() {

    private lateinit var splashImage: ImageView
    private lateinit var binding: NewLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_layout)

        splashImage = findViewById(R.id.splash_image)
        splashImage.alpha = 0f
        splashImage.animate().setDuration(1500).alpha(1f).withEndAction{
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
//        binding = NewLayoutBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
//
//        binding.splash_image.alpha = 0f
//        binding.splash_image.animate().setDuration(1500).alpha(1f).withEndAction {
//            val i = Intent(this, MainActivity::class.java)
//            startActivity(i)
//            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
//            finish()
//        }


    }
}
