package com.example.jvlite.MainPACK.HomePACK

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jvlite.R
import kotlinx.android.synthetic.main.activity_confirm.*
import kotlinx.android.synthetic.main.activity_periodically.*

class ConfirmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm)
        var tbuoi1 = intent.getStringExtra("buoi1")
        var tmoney1 = intent.getStringExtra("money1")
        var noti = intent.getStringExtra("noti")
        txtTimeConfirm.text= "$tbuoi1"
        txtMoneyConfirm.text=tmoney1
        txtNotiConfirm.text = noti
        btnConfirm.setOnClickListener {
            val intent = Intent(this, FinishActivity::class.java)
            startActivity(intent)
        }
    }
}
