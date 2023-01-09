package com.techbros.traine

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AlphaAnimation
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.techbros.traine.databinding.ContentMainMenuBinding
import com.techbros.traine.viewModels.OrderViewModel
import model.*

class MainMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main_menu)

        val orderView = OrderViewModel(Order)

        val binding = DataBindingUtil.setContentView<ContentMainMenuBinding>(this, R.layout.content_main_menu)
        binding.order = orderView
        binding.startButton.setOnClickListener { view ->
            view.startAnimation(AlphaAnimation(1f, 0.8f))
            val intent = Intent(this, TrainingSimulationActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        binding.logOut.setOnClickListener { view ->
            val ma = applicationContext as TrainEApp
            ma.passwordGuess = ""
            view.startAnimation(AlphaAnimation(1f, 0.8f))
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }
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
