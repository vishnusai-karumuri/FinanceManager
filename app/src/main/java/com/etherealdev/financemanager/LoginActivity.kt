package com.etherealdev.financemanager

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
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
import com.etherealdev.financemanager.databinding.LoginactivityBinding
import com.etherealdev.financemanager.ui.theme.FinanceManagerTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.auth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException


class LoginActivity : ComponentActivity() {

    private lateinit var binding: LoginactivityBinding
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.loginactivity)

        binding = LoginactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth


//        val toast = email + password

        binding.loginButton.setOnClickListener {

            val email = binding.loginEmail.text.toString()
//            val email = "k@k.com"
            val password = binding.loginPassword.text.toString()
            val view: View? = this.currentFocus
            val keyb = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            if (view != null) {
                keyb.hideSoftInputFromWindow(view.windowToken,0)
            }


            if(email.isEmpty())
            {
                Toast.makeText(this, "Email address empty", Toast.LENGTH_SHORT).show()

            }
            else if(password.isEmpty())
            {
                Toast.makeText(this, "Password empty", Toast.LENGTH_SHORT).show()
            }
            else if(email.isEmpty() && password.isEmpty())
            {
                Toast.makeText(this, "Email address & Password empty", Toast.LENGTH_SHORT).show()
            }
            else
            {
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this){task->
                    if(task.isSuccessful){
                        //Sign In Success, Update UI with the signed-in user's information
                        Log.d(TAG, "singInWIthEmail:Success")
                        val user = auth.currentUser
                        Toast.makeText(
                            baseContext,
                            "Authentication Success.",
                            Toast.LENGTH_SHORT,
                        ).show()
                        val i = Intent(this, MainActivity::class.java)
                        startActivity(i)
                        finish()
                    }
                    else{
//                        val errorCode = task.exception?.errorCode
                        val errorMessage = task.exception?.message
                        val errorCode = (task.exception as? FirebaseAuthException)?.errorCode.toString()
                        // If sign in fails, display a message to the user.
                        if (errorCode != null) {
                            Log.d(TAG, errorCode)
                        }
                        when(errorCode){
//                            "ERROR_INVALID_CREDENTIAL" -> Toast.makeText(this, "Wrong Password",Toast.LENGTH_LONG).show()
                            "ERROR_USER_NOT_FOUND" -> Toast.makeText(this, "User Does Not Exist",Toast.LENGTH_LONG).show()
                            else -> Toast.makeText(this, errorCode,Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
        binding.signupIntentButton.setOnClickListener {
            val email: String = binding.loginEmail.text.toString()
            val pwd: String = binding.loginPassword.text.toString()
//            Log.d(TAG,email)
            val intent = Intent(this, SignupActivity::class.java)
            intent.putExtra("Email", email)
            intent.putExtra("Password",pwd)
            startActivity(intent)
            finish()
        }
    }
}