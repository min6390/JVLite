package com.example.jvlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.example.jvlite.MainPACK.HistoryFragment
import com.example.jvlite.MainPACK.HomeFragment
import com.example.jvlite.MainPACK.NotificationFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

//        buttonLogout.setOnClickListener(View.OnClickListener {
//            FirebaseAuth.getInstance().signOut()
//            val intent = Intent(this@HomeActivity, MainActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            startActivity(intent)
//        })
        bottomNavi.setOnNavigationItemSelectedListener (mOnNavigationItemSelectedListener)
        replanceFrament(HomeFragment())
    }
    private val mOnNavigationItemSelectedListener
            = BottomNavigationView.OnNavigationItemSelectedListener setOnNavigationItemSelectedListener@{ item->
        var fragment : Fragment = Fragment()
        when (item.itemId) {
            R.id.ihome-> {
                replanceFrament(HomeFragment())
                return@setOnNavigationItemSelectedListener true
            }
            R.id.iHistory -> {
                replanceFrament(HistoryFragment())
                return@setOnNavigationItemSelectedListener true
            }
            R.id.iNotification -> {
                replanceFrament( NotificationFragment())
                return@setOnNavigationItemSelectedListener true
            }

            // ???
//        supportFragmentManager.beginTransaction().replace(R.id.frameLayout,fragment).commit()
//        ->supportFragmentManager.beginTransaction().replace(R.id.frameLayout,HomeFragment()).commit()
        }
        return@setOnNavigationItemSelectedListener false
    }
    private  fun replanceFrament(fragment : Fragment){
        val fragementTransaction = supportFragmentManager.beginTransaction()
        fragementTransaction.replace(R.id.frameLayout,fragment)
        fragementTransaction.commit()
    }
}