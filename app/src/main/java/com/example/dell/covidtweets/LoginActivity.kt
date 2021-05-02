package com.example.dell.covidtweets

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    var btn_sign_up:Button?=null
    var btnlogin:Button?=null
    var tv_password:TextView?=null
    var tv_username:TextView?=null
    var forgotpassword:TextView?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        auth = FirebaseAuth.getInstance()
        btn_sign_up = findViewById<View>(R.id.btn_sign_up) as Button
        btnlogin = findViewById<View>(R.id.btn_log_in) as Button
        tv_username = findViewById<View>(R.id.tv_username) as TextView
        tv_password = findViewById<View>(R.id.tv_password) as TextView
        forgotpassword = findViewById<View>(R.id.forgotpassword) as TextView



        btn_sign_up!!.setOnClickListener {
            startActivity(Intent(this,SignupActivity::class.java))
            finish()
        }
        forgotpassword!!.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Forgot Password")
            val view=layoutInflater.inflate(R.layout.dialog_forgot_password, null)
            val username=view.findViewById<EditText>(R.id.et_username)
            builder.setView(view)
            builder.setPositiveButton("Reset", DialogInterface.OnClickListener { _, _ ->
                forgotpassword(username)
            })
            builder.setNegativeButton("Close", DialogInterface.OnClickListener { _, _ -> })
            builder.show()

            /*  fg_email!!.visibility=View.VISIBLE
              auth.sendPasswordResetEmail(fg_email!!.text.toString())
                  .addOnCompleteListener { task ->
                      if (task.isSuccessful) {
                          Toast.makeText(this, "Email sent", Toast.LENGTH_SHORT).show()
                      }
                  }*/
        }
        btnlogin!!.setOnClickListener{
            doLogin()
        }


    }

    private fun forgotpassword(username: EditText) {
        if (username.text.toString().isEmpty()) {

            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(username.text.toString()).matches()) {

            return
        }
        auth.sendPasswordResetEmail(username.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Email sent", Toast.LENGTH_SHORT).show()

                }
            }

    }

    private fun doLogin() {
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

        auth.signInWithEmailAndPassword(tv_username!!.text.toString(), tv_password!!.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val user = auth.currentUser
                    updateUI(user)
                } else {

                    updateUI(null)
                    // ...
                }

                // ...
            }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    fun updateUI(currentUser: FirebaseUser?) {
        if(currentUser!=null){
            if(currentUser.isEmailVerified){
                Toast.makeText(baseContext, "Login successfull",
                    Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))

                finish()
            }else{
                Toast.makeText(baseContext, "Please verify your email address",
                    Toast.LENGTH_SHORT).show()
            }
        }

    }


}
