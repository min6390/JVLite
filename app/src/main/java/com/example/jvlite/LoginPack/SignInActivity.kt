package com.example.jvlite.LoginPack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.jvlite.HomeActivity
import com.example.jvlite.R
import com.google.android.gms.tasks.TaskExecutors
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_sign_in.*
import java.util.concurrent.TimeUnit

class SignInActivity : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth
    lateinit var verificationId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
        mAuth = FirebaseAuth.getInstance()
        val phonenumber = intent.getStringExtra("mobile")
        txtPhoneNumber.text = phonenumber
        sendVerificationCode(phonenumber)
        buttonSignIn.setOnClickListener(View.OnClickListener {
            val code = editTextCode.text.toString().trim()
            if (code.isEmpty() || code.length < 6) {
                editTextCode.error = "Enter code..."
                editTextCode.requestFocus()
                return@OnClickListener
            }
            verifyCode(code)
        })
    }

    private fun verifyCode(code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId, code)
        signInWithCredential(credential)
    }

    private fun signInWithCredential(credential: PhoneAuthCredential) {
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val intent =
                        Intent(this@SignInActivity, HomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this@SignInActivity,
                        task.exception!!.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    private fun sendVerificationCode(number: String) {
        progressbar.visibility = View.VISIBLE
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            "+84$number",
            60,
            TimeUnit.SECONDS,
            TaskExecutors.MAIN_THREAD,
            mCallBack
        )
    }


    private val mCallBack: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onCodeSent(
                s: String,
                forceResendingToken: PhoneAuthProvider.ForceResendingToken
            ) {
                super.onCodeSent(s, forceResendingToken)
                verificationId = s
            }

            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                val code = phoneAuthCredential.smsCode
                if (code != null) {
                    editTextCode!!.setText(code)
                    verifyCode(code)
                }
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(this@SignInActivity, e.message, Toast.LENGTH_LONG).show()
            }
        }
}
