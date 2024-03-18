package com.etherealdev.financemanager

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.etherealdev.financemanager.databinding.SignupactivityBinding
import com.etherealdev.financemanager.ui.theme.FinanceManagerTheme

@Suppress("DEPRECATION")
class SignupActivity : ComponentActivity() {
    private lateinit var binding: SignupactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = SignupactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var email: String = intent.getStringExtra("Email").toString()
        var pwd: String = intent.getStringExtra("Password").toString()
//        if (email != null) {
//            Log.d(TAG, email)
//        }

        if (email != null || pwd != null)
        {
//                Toast.makeText(this, email, Toast.LENGTH_SHORT).show()
                binding.signupEmail.setText(email)
                binding.signupPassword.setText(pwd)
                binding.signupConfirmPassword.setText(pwd)
        }

        binding.signupButton.setOnClickListener{
            email = binding.signupEmail.text.toString()
            if(!email.isValidEmail()){
                Toast.makeText(this, "Email not Valid", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

