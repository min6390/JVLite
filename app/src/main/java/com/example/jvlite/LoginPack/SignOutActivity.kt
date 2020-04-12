package com.example.jvlite.LoginPack

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.jvlite.MainActivity
import com.example.jvlite.MainPACK.HomePACK.InformationActivity
import com.example.jvlite.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_sign_out.*

class SignOutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_out)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
        ReadDataBase()
        txtEdit.setOnClickListener {
            val intent = Intent(this, InformationActivity::class.java)
            startActivity(intent)
        }
        txtLogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

    }

    private fun ReadDataBase() {
        val database = FirebaseDatabase.getInstance()
        val mRef = database.reference.child("Users")
        val myRefUsersName = mRef.child("Name")
        val myRefEmail = mRef.child("Email")
        myRefUsersName.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.value
                txtName.text = value.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Toast.makeText(this@SignOutActivity, "A", Toast.LENGTH_SHORT).show()
            }
        })
        myRefEmail.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.value
                txtEmail.text = value.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Toast.makeText(this@SignOutActivity, "A", Toast.LENGTH_SHORT).show()
            }
        })

    }
}