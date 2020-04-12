package com.example.jvlite.MainPACK.HomePACK

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.jvlite.R
import kotlinx.android.synthetic.main.listview_pay.*


class PayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)
        var sum = intent.getIntExtra("sum1",10)
        var noti = intent.getStringExtra("noti")
        var notif = noti
        val buoi1 = " ${sum*4} Buổi"
        val buoi2  = " ${sum*12} Buổi"
        val buoi3 = " ${sum*36} Buổi"
        val money1 = " ${sum*4*100000} đ"
        val money2 = " ${sum*12*100000} đ"
        val money3 = " ${sum*36*100000} đ"
        txtBuoi1.text= buoi1
        txtBuoi2.text= buoi2
        txtBuoi3.text= buoi3
        txtMoney1.text= money1
        txtMoney2.text= money2
        txtMoney3.text= money3


        rLayoutPay1.setOnClickListener {
            var intent = Intent(this, ConfirmActivity::class.java)
            intent.putExtra("buoi1",buoi1)
            intent.putExtra("money1",money1)
            intent.putExtra("noti",notif)
            startActivity(intent)
        }
        rLayoutPay2.setOnClickListener {
            var bundle = Bundle()
            var intent = Intent(this, ConfirmActivity::class.java)
            intent.putExtra("buoi1",buoi2)
            intent.putExtra("money1",money2)
            intent.putExtras(bundle)
            startActivity(intent)
        }
        rLayoutPay3.setOnClickListener {
            var bundle = Bundle()
            var intent = Intent(this, ConfirmActivity::class.java)
            intent.putExtra("buoi1",buoi3)
            intent.putExtra("money1",money3)
            intent.putExtras(bundle)
            startActivity(intent)
        }

    }


}
