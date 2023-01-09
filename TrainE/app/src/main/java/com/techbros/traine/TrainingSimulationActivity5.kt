package com.techbros.traine

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AlphaAnimation
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.techbros.traine.R.*
import com.techbros.traine.databinding.ContentTrainingSimulation5Binding
import com.techbros.traine.viewModels.OrderViewModel
import com.techbros.traine.viewModels.UserAnswerViewModel
import model.Order
import model.UserAnswer
import java.math.RoundingMode
import java.text.DecimalFormat

class TrainingSimulationActivity5 : AppCompatActivity() {

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_training_simulation5)

        // Access our singleton order.
        val orderView = OrderViewModel(Order)
        val userView = UserAnswerViewModel(UserAnswer)

        // Bind the order viewmodel to the view.
        val binding = DataBindingUtil.setContentView<ContentTrainingSimulation5Binding>(this, layout.content_training_simulation5)
        binding.order = orderView
        binding.userAnswer = userView

        // Calculate and display the user's score.
        val percentCorrect = calculateScore(binding)
        val df = DecimalFormat("##")
        df.roundingMode = RoundingMode.CEILING
        binding.resultsMessegeTextView.text =  "You got " + df.format(percentCorrect).toString() + "% correct."

        // Handle the button click.
        binding.endButton.setOnClickListener() { view ->
            view.startAnimation(AlphaAnimation(1f, 0.8f))
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
            overridePendingTransition(anim.slide_in_right, anim.slide_out_left)
        }

        binding.nextButton.setOnClickListener() { view ->
            view.startAnimation(AlphaAnimation(1f, 0.8f))
            val intent = Intent(this, TrainingSimulationActivity::class.java)
            startActivity(intent)
            overridePendingTransition(anim.slide_in_right, anim.slide_out_left)
        }
    }

    @SuppressLint("ResourceAsColor")
    fun calculateScore(binding: ContentTrainingSimulation5Binding) : Double {
        // Define the colors representing correct/wrong answers.
        val correctAnswerColor = ContextCompat.getColor(this, color.correctAnswer)
        val wrongAnswerColor = ContextCompat.getColor(this, color.wrongAnswer)

        // I decided to use arrays to prevent a bunch of duplicate code.
        val orderAnswers:Array<String> = arrayOf(
                Order.shots.toString(), Order.goodOrBad, Order.size, Order.type, Order.temp, Order.milk, Order.milkTemp,
                Order.flavorPrimary, Order.pumpsPrimary.toString(), Order.flavorSecondary, Order.pumpsSecondary.toString())
        val userAnswers:Array<String> = arrayOf(
                UserAnswer.shots.toString(), UserAnswer.goodOrBad, UserAnswer.size, UserAnswer.type, UserAnswer.temp, UserAnswer.milk,
                UserAnswer.milkTemp,  UserAnswer.flavorPrimary, UserAnswer.pumpsPrimary.toString(), UserAnswer.flavorSecondary, UserAnswer.pumpsSecondary.toString())
        val userAnswerTextViews:Array<TextView> = arrayOf(
                binding.userShotTextView, binding.userPullTextView, binding.userSizeTextView, binding.userTypeTextView,
                binding.userTempTextView, binding.userMilkTextView, binding.userMilkTempTextView, binding.userFlavorPrimaryTextView,
                binding.userPumpsPrimaryTextView, binding.userFlavorSecondaryTextView, binding.userPumpsSecondaryTextView)

        var numberOfCorrectAnswers = 0.0

        // Determine if the user's answers are correct.
        userAnswers.forEachIndexed { index, element ->
           if (element.toLowerCase() == orderAnswers[index].toLowerCase()) {
               userAnswerTextViews[index].setBackgroundColor(correctAnswerColor)
               numberOfCorrectAnswers++
           }
           else userAnswerTextViews[index].setBackgroundColor(wrongAnswerColor)
        }

        val percentRight = numberOfCorrectAnswers / orderAnswers.size * 100
        return percentRight
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(anim.slide_in_left, anim.slide_out_right)
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
            id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

