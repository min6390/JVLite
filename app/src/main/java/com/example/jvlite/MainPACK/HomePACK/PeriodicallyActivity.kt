package com.example.jvlite.MainPACK.HomePACK

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity

import com.example.jvlite.R
import kotlinx.android.synthetic.main.activity_periodically.*

import kotlinx.android.synthetic.main.include_alertdialog.view.*
import kotlinx.android.synthetic.main.include_calendar.*
import kotlinx.android.synthetic.main.include_wheelpicker.*


class PeriodicallyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_periodically)
        calendar()
        dataTime()
        txtCalendar.setOnClickListener { v ->
            linearLayoutCalendar.visibility = View.VISIBLE
        }
        txtAddress.setOnClickListener {
            diaLogAddress()
        }
        txtMap.setOnClickListener {
            val intent = Intent(
                this,
                MapsActivity::class.java
            )
            startActivity(intent)
        }
        txtTime.setOnClickListener { v ->
            wheelPickerLinearLayout.visibility = View.VISIBLE
        }
    }

    private fun dataTime() {
        var firstTime = mutableListOf<String>("8:00", "8:30", "9:00", "9:30", "10:00")
        var lastTime = mutableListOf<String>("10:00", "10:30", "11:00", "11:30", "12:00")
        main_wheel_left.data = firstTime
        main_wheel_right.data = lastTime
        rbtnGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rbtnMorning -> {
                    firstTime.clear()
                    lastTime.clear()
                    firstTime.addAll(arrayListOf("8:00", "8:30", "9:00", "9:30", "10:00"))
                    lastTime.addAll(arrayListOf("10:00", "10:30", "11:00", "11:30", "12:00"))
                    main_wheel_left.data = firstTime
                    main_wheel_right.data = lastTime

                }
                R.id.rbtnAfternoon -> {
                    firstTime.clear()
                    lastTime.clear()
                    firstTime.addAll(arrayListOf("13:00", "13:30", "14:00", "14:30", "15:00"))
                    lastTime.addAll(arrayListOf("15:00", "15:30", "16:00", "16:30", "17:00"))
                    main_wheel_left.data = firstTime
                    main_wheel_right.data = lastTime

                }
                R.id.rbtnNight -> {
                    firstTime.clear()
                    lastTime.clear()
                    firstTime.addAll(arrayListOf("17:00", "17:30", "18:00", "18:30", "19:00"))
                    lastTime.addAll(arrayListOf("19:00", "19:30", "20:00", "20:30", "21:00"))
                    main_wheel_left.data = firstTime
                    main_wheel_right.data = lastTime
                }

            }

        }
        btnEnter.setOnClickListener {
            wheelPickerLinearLayout.visibility = View.GONE
//            lateinit var wheelPicker:WheelPicker
//            var index = wheelPicker.currentItemPosition
//            var arr1  = arrayListOf(main_wheel_left.data)
//            var arr2 = arrayListOf(main_wheel_right.data)
//            var str1 = arr1[index]
//            var str2 = arr2[index]
//            Log.d("BBB","$str1 - $str2")
        }
    }

    private fun calendar() {
        val startDay = calendarView.date
        calendarView.maxDate = startDay + 1209600000L
        calendarView.minDate = startDay
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val monthPlus = month + 1
            var date = "$dayOfMonth/$monthPlus/$year"
            txtCalendar.text = date
            linearLayoutCalendar.visibility = View.GONE
        }
    }

    private fun diaLogAddress() {
        val dialog = LayoutInflater.from(this).inflate(R.layout.include_alertdialog, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(dialog)
        val mAlertDialog = mBuilder.show()
        dialog.btnAlertDialog.setOnClickListener {
            mAlertDialog.dismiss()
            val txtDialog = dialog.etAlertDialog.text.toString()
            if (txtDialog != null) {
                txtAddress.text = txtDialog.trim()
            }
        }
    }

}
