package com.example.jvlite

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.jvlite.LoginPack.SignInActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
        buttonContinue.setOnClickListener {
            val phoneNumber: String = editTextPhone.text.toString().trim()
            if(phoneNumber.isEmpty() || phoneNumber.length < 10){
                editTextPhone.error = "Enter a valid mobile";
                editTextPhone.requestFocus();
                return@setOnClickListener;
            }
            val intent = Intent(this@MainActivity, SignInActivity::class.java)
            intent.putExtra("mobile", phoneNumber)
            startActivity(intent)
        }
    }
    override fun onStart() {
        super.onStart()
        if (FirebaseAuth.getInstance().currentUser != null) {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

}

