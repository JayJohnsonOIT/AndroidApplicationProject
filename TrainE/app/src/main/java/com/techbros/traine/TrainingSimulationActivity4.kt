package com.techbros.traine

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.techbros.traine.databinding.ContentTrainingSimulation4Binding
import com.techbros.traine.viewModels.OrderViewModel
import model.Order
import model.UserAnswer

class TrainingSimulationActivity4 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_training_simulation4)

        // Access our singleton order.
        val orderView = OrderViewModel(Order)

        // Bind the order viewmodel to the view.
        val binding = DataBindingUtil.setContentView<ContentTrainingSimulation4Binding>(this, R.layout.content_training_simulation4)
        binding.order = orderView

        val flavors = arrayOf("None", "Sweetener", "Chocolate", "White Chocolate", "Caramel", "Hazelnut", "Cinnamon", "Fudge", "Honey")
        binding.flavorPicker.minValue = 0
        binding.flavorPicker.maxValue = flavors.size - 1
        binding.flavorPicker.wrapSelectorWheel = false
        binding.flavorPicker.displayedValues = flavors
        binding.flavorPicker.setOnValueChangedListener { picker, oldVal, newVal ->

            // Save the flavor choice for primary and secondary flavors.
            if (binding.primaryButton.text == "Primary") {
                UserAnswer.flavorPrimary = flavors[newVal]
            }
            else if (binding.primaryButton.text == "Secondary") {
                UserAnswer.flavorSecondary = flavors[newVal]
            }

            //UserAnswer.type will default to Coffee
            UserAnswer.type = "Coffee"
        }

        binding.numberOfPumpsPicker.minValue = 0
        binding.numberOfPumpsPicker.maxValue = 10
        binding.numberOfPumpsPicker.wrapSelectorWheel = false
        binding.numberOfPumpsPicker.setOnValueChangedListener { picker, oldVal, newVal ->
           // binding.shotsTextView.text = "Shots : $newVal"
            if (binding.primaryButton.text == "Primary") {
                UserAnswer.pumpsPrimary = newVal
            }
            else if (binding.primaryButton.text == "Secondary") {
                UserAnswer.pumpsSecondary = newVal
            }
        }

        // Handle the button click.
        binding.primaryButton.setOnClickListener() { view ->

            // Update the button text to secondary.
            if (binding.primaryButton.text == "Primary") {
                binding.primaryButton.text = "Secondary"
            }
            // Load the next view.
            else if (binding.primaryButton.text == "Secondary") {
                val intent = Intent(this, TrainingSimulationActivity5::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
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
