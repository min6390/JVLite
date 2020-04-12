package com.example.jvlite.MainPACK.HomePACK

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

import com.example.jvlite.R
import kotlinx.android.synthetic.main.activity_periodically.*

import kotlinx.android.synthetic.main.include_alertdialog.view.*
import kotlinx.android.synthetic.main.include_calendar.*
import kotlinx.android.synthetic.main.include_wheelpicker.*


class PeriodicallyActivity : AppCompatActivity(), View.OnClickListener {
    private var sum : Int =0
    private var textCalendar : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_periodically)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        calendar()
        dataTime()
        checkboxT2.setOnClickListener(this)
        checkboxT3.setOnClickListener(this)
        checkboxT4.setOnClickListener(this)
        checkboxT5.setOnClickListener(this)
        checkboxT6.setOnClickListener(this)
        checkboxT7.setOnClickListener(this)
        checkboxCN.setOnClickListener(this)

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
        btnNext.setOnClickListener {
            var noti = etView.text
            val intent = Intent(this, PayActivity::class.java)
            intent.putExtra("sum1",sum)
            intent.putExtra("noti",noti)
            startActivity(intent)
            Log.d("CCC",noti.toString())
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
            val int1 : Int =main_wheel_left.currentItemPosition
            val ft = firstTime[int1]
            val int2 : Int =main_wheel_right.currentItemPosition
            val lt = lastTime[int2]
             textCalendar ="$ft - $lt"
            txtTime.text = textCalendar
            wheelPickerLinearLayout.visibility = View.GONE

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

    override fun onClick(v: View?) {

        if (v is CheckBox) {
            val checked: Boolean = v.isChecked

            when (v.id) {
                R.id.checkboxT2 -> {
                    if (checked) {
                        sum += 1
                    } else {
                        sum -= 1
                    }
                }
                R.id.checkboxT3 -> {
                    if (checked) {
                        sum += 1
                    } else {
                        sum -= 1
                    }
                }
                R.id.checkboxT4 -> {
                    if (checked) {
                        sum += 1
                    } else {
                        sum -= 1
                    }
                }
                R.id.checkboxT5 -> {
                    if (checked) {
                        sum += 1
                    } else {
                        sum -= 1
                    }
                }
                R.id.checkboxT6 -> {
                    if (checked) {
                        sum += 1
                    } else {
                        sum -= 1
                    }
                }
                R.id.checkboxT7 -> {
                    if (checked) {
                        sum += 1
                    } else {
                        sum -= 1
                    }
                }
                R.id.checkboxCN -> {
                    if (checked) {
                        sum += 1
                    } else {
                        sum -= 1
                    }
                }
            }
            Log.d("AAA",sum.toString())
        }
    }

}
