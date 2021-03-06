package com.example.jvlite.MainPACK

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jvlite.LoginPack.SignOutActivity
import com.example.jvlite.MainPACK.HomePACK.PeriodicallyActivity
import com.example.jvlite.R
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_home.*


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

        ivAccount.setOnClickListener {
            val intent = Intent(context, SignOutActivity::class.java)
            startActivity(intent)
        }
        buttonPeriodically.setOnClickListener {
            val intent = Intent(context, PeriodicallyActivity::class.java)
            startActivity(intent)
        }

    }
}







