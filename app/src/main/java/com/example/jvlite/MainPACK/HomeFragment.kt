package com.example.jvlite.MainPACK

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.Toast

import com.example.jvlite.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.include_alertdialog.*
import kotlinx.android.synthetic.main.include_alertdialog.view.*
import kotlinx.android.synthetic.main.include_calendar.*
import kotlinx.android.synthetic.main.include_calendar.view.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val monthPlus = month + 1
            var date = "$dayOfMonth/$monthPlus/$year"
            txtCalendar.text = date
            linearLayoutCalendar.visibility = View.GONE
        }
        txtCalendar.setOnClickListener { v ->
            linearLayoutCalendar.visibility = View.VISIBLE
        }
        txtAddress.setOnClickListener {
            diaLogAddress()
        }
        txtMap.setOnClickListener {
            //val intent = Intent(context, MapsActivity::class.java) // MapsActivity chưa có nè nha, kiem tra lại nhúng SDK đúng chưa, nếu đúng nó sẽ có tuwf bên SDK

        }
    }

    private fun diaLogAddress() {
        val dialog = LayoutInflater.from(context).inflate(R.layout.include_alertdialog, null)
        val mBuilder = AlertDialog.Builder(context)
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





