package com.etherealdev.financemanager

import android.content.ContentValues.TAG
import android.content.Intent
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
import android.graphics.Color
//import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.etherealdev.financemanager.databinding.SignupactivityBinding
import com.etherealdev.financemanager.ui.theme.FinanceManagerTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@Suppress("DEPRECATION")
class SignupActivity : ComponentActivity() {
    private lateinit var binding: SignupactivityBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = SignupactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

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
            pwd = binding.signupPassword.text.toString()
            val cnfpwd = binding.signupConfirmPassword.text.toString()
            email = binding.signupEmail.text.toString()
            if(!email.isValidEmail()){
                Toast.makeText(this, "Email not Valid", Toast.LENGTH_SHORT).show()
                binding.SingupErrorText.setText("Email Address Not valid")
                binding.SingupErrorText.setTextColor(Color.RED)
            }
            else if(pwd.isEmpty() || cnfpwd.isEmpty())
            {
                binding.SingupErrorText.setText("Password is Empty")
            }
            else if(email.isEmpty())
            {
                binding.SingupErrorText.setText("Email Address Empty")
            }
            else if (!pwd.equals(cnfpwd))
            {
                binding.SingupErrorText.setText("Passwords do not match")
            }
            else if(pwd.length < 6 || cnfpwd.length < 6)
            {
                binding.SingupErrorText.setText("Password is shorter than 6 characters : $pwd + cnfpwd" )
            }
            else if(email.isValidEmail() && pwd.equals(cnfpwd) && pwd.length >= 6)
            {
//                Toast.makeText(this, "Sign up Success", Toast.LENGTH_SHORT).show()
                auth.createUserWithEmailAndPassword(email,pwd)
                    .addOnCompleteListener(this){
                        task ->
                        if(task.isSuccessful)
                        {
                            Log.d(TAG,"Create User Successful")
                            val user = auth.currentUser
                            startActivity(Intent(this,MainActivity::class.java))
                            finish()
                        }
                        else
                        {
                            Log.w(TAG, "Create User Failed")
                        }
                    }
            }
        }
    }
}
fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

