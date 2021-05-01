package com.example.dell.covidtweets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.dell.covidtweets.fragments.HomeFragment
import com.example.dell.covidtweets.fragments.ProfileFragment
import com.example.dell.covidtweets.fragments.SearchFragment
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    var btn_sign_up:Button?=null
    var login:Button?=null

    var tv_password:TextView?=null
    var tv_username:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)



        auth = FirebaseAuth.getInstance()
        tv_username = findViewById<View>(R.id.tv_username) as TextView
        tv_password = findViewById<View>(R.id.tv_password) as TextView
        btn_sign_up = findViewById<View>(R.id.btn_sign_up) as Button
        login = findViewById<View>(R.id.login) as Button

        login!!.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))

        }





        btn_sign_up!!.setOnClickListener {
            signUpUser()
        }

    }



    private fun signUpUser() {
        if (tv_username!!.text.toString().isEmpty()) {
            tv_username!!.error = "Please enter email"
            tv_username!!.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(tv_username!!.text.toString()).matches()) {
            tv_username!!.error = "Please enter valid email"
            tv_username!!.requestFocus()
            return
        }

        if (tv_password!!.text.toString().isEmpty()) {
            tv_password!!.error = "Please enter password"
            tv_password!!.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(tv_username!!.text.toString(), tv_password!!.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user!!.sendEmailVerification()
                    startActivity(Intent(this,LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(baseContext, "Sign Up failed. Try again after some time.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}