package com.example.jvlite.MainPACK.HomePACK

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jvlite.LoginPack.SignOutActivity
import com.example.jvlite.R
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_information.*
import kotlinx.android.synthetic.main.activity_sign_out.*


class InformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        val database = FirebaseDatabase.getInstance()
        val mRef = database.reference.child("Users")

        btnInformation.setOnClickListener {
            val username = etName.text.toString()
            val email = etEmail.text.toString()
            if (username !=null || username != ""){
                mRef.child("Name").setValue(username)

            }
            if (email !=null || email != ""){
                mRef.child("Email").setValue(email)
            }
            val intent = Intent(this, SignOutActivity::class.java)
            startActivity(intent)
        }

    }

}

