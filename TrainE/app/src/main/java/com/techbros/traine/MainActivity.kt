package com.techbros.traine

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val buttonClick = AlphaAnimation(1f, 0.8f)

    @SuppressLint("ResourceAsColor", "ClickableViewAccessibility", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)

        val button = findViewById<Button>(R.id.sign_in_button) as Button
        val ma = applicationContext as TrainEApp

        setKeyOnTouchListener(findViewById<Button>(R.id.num0))
        setKeyOnTouchListener(findViewById<Button>(R.id.num1))
        setKeyOnTouchListener(findViewById<Button>(R.id.num2))
        setKeyOnTouchListener(findViewById<Button>(R.id.num3))
        setKeyOnTouchListener(findViewById<Button>(R.id.num4))
        setKeyOnTouchListener(findViewById<Button>(R.id.num5))
        setKeyOnTouchListener(findViewById<Button>(R.id.num6))
        setKeyOnTouchListener(findViewById<Button>(R.id.num7))
        setKeyOnTouchListener(findViewById<Button>(R.id.num8))
        setKeyOnTouchListener(findViewById<Button>(R.id.num9))

        val loginStatus = findViewById<TextView>(R.id.login_status)

        button.setOnClickListener { view ->
            if (ma.passwordGuess == ma.password) {
                loginStatus.text = "Login Successful!"
                loginStatus.setTextColor(androidx.core.content.ContextCompat.getColor(this@MainActivity, com.techbros.traine.R.color.buttonBackground))

                // Load the next view.
                view.startAnimation(AlphaAnimation(1f, 0.8f))
                val intent = Intent(this, MainMenu::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
            else {
                // Reset the password if incorrect.
                ma.passwordGuess = ""
                loginStatus.text = "Sorry, incorrect password."
                loginStatus.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.wrongAnswer))
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    fun setKeyOnTouchListener(keyButton: Button) {
        keyButton.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        keyButton.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.wrongAnswer))
                    }
                    MotionEvent.ACTION_UP -> {
                        keyButton.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.keyBackground))
                        // Update the password guess.
                        val ma = applicationContext as TrainEApp
                        ma.passwordGuess += keyButton.text.toString()
                    }
                }

                return v?.onTouchEvent(event) ?: true
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}
