package com.techbros.traine
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AlphaAnimation
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.techbros.traine.databinding.ContentTrainingSimulation2Binding
import com.techbros.traine.viewModels.OrderViewModel
import model.Order
import model.UserAnswer

class TrainingSimulationActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_training_simulation2)

        // Access our singleton order.
        val orderView = OrderViewModel(Order)

        // Bind the order viewmodel to the view.
        val binding = DataBindingUtil.setContentView<ContentTrainingSimulation2Binding>(this, R.layout.content_training_simulation2)
        binding.order = orderView

        val sizes = arrayOf("Small", "Medium", "Large", "X-Large")
        binding.sizePicker.minValue = 0
        binding.sizePicker.maxValue = sizes.size - 1
        binding.sizePicker.wrapSelectorWheel = false
        binding.sizePicker.displayedValues = sizes
        binding.sizePicker.setOnValueChangedListener() { picker, oldVal, newVal ->
            UserAnswer.size = sizes[newVal]
        }

        binding.coldButton.setOnClickListener() { view ->
            UserAnswer.temp="Cold"
            view.startAnimation(AlphaAnimation(1f, 0.8f))
            val intent = Intent(this, TrainingSimulationActivity3::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        binding.hotButton.setOnClickListener { view ->
            UserAnswer.temp="Hot"
            view.startAnimation(AlphaAnimation(1f, 0.8f))
            val intent = Intent(this, TrainingSimulationActivity3::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
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

