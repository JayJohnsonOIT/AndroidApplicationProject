package com.techbros.traine

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AlphaAnimation
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.techbros.traine.databinding.ContentTrainingSimulationBinding
import com.techbros.traine.viewModels.OrderViewModel
import model.Drink
import model.Order
import model.UserAnswer


class TrainingSimulationActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_training_simulation)

        // Get our Array of Drink objects.
        val ma = applicationContext as TrainEApp
        val drinks = ma.loadDrinks()

        // Randomly select a drink to order.
        if (drinks != null) {
            getRandomOrder(drinks)
        }

        // Access our singleton order.
        val orderView = OrderViewModel(Order)
        
        // Bind the order viewmodel to the view.
        val binding = DataBindingUtil.setContentView<ContentTrainingSimulationBinding>(this, R.layout.content_training_simulation)
        binding.order = orderView

        // Randomize the shot pull time.
        val shotPullTime = (15..27).random()
        binding.shotPullTimeTextView.text = "Shot Pull Time: ${shotPullTime.toString()}"
        if (shotPullTime in 18..25){
            Order.goodOrBad= "Good"
        }
        else {
            Order.goodOrBad= "Bad"
        }

        // Handle the button click.
        binding.goodButton.setOnClickListener { view ->
            UserAnswer.goodOrBad = "Good"
            view.startAnimation(AlphaAnimation(1f, 0.8f))
            val intent = Intent(this, TrainingSimulationActivity2::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        binding.badButton.setOnClickListener { view ->
            UserAnswer.goodOrBad = "Bad"
            view.startAnimation(AlphaAnimation(1f, 0.8f))
            val intent = Intent(this, TrainingSimulationActivity2::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        binding.numberOfShotsPicker.minValue = 0
        binding.numberOfShotsPicker.maxValue = 10
        binding.numberOfShotsPicker.wrapSelectorWheel = false
        binding.numberOfShotsPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            binding.shotsTextView.text = "Shots : $newVal"
            UserAnswer.shots = newVal
        }
    }


    private fun getRandomOrder(drinks : Array<Drink>) {
        // Pick a random number and use that element of the drink array as our order
        // Indices returns an IntRange of the valid indices for the drinks.
        val num = (drinks.indices).random()
        //assign properties
        Order.name = drinks[num].name
        Order.type = drinks[num].type
        Order.size = drinks[num].size
        Order.shots = drinks[num].shots
        Order.milk = drinks[num].milk
        Order.flavorPrimary = drinks[num].flavorPrimary
        Order.pumpsPrimary = drinks[num].pumpsPrimary
        Order.flavorSecondary = drinks[num].flavorSecondary
        Order.pumpsSecondary = drinks[num].pumpsSecondary
        Order.temp = drinks[num].temp

        // Populate Order.milkTemp based on Order.temp's current value
        if (Order.temp == "Cold") {
            Order.milkTemp = "Chilled"
        }
        else {
            Order.milkTemp = "Steamed"
        }
    }

    private fun loadJsonData(fileName: String) : String {
        return applicationContext.assets.open(fileName).bufferedReader().use {
            it.readText()
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
