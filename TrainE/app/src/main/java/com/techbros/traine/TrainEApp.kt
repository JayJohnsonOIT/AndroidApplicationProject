package com.techbros.traine

import android.app.Application
import model.Drink

internal class TrainEApp : Application() {
    // Create a property we can access anywhere in the apps via the loadDrinks method.
    // This makes it so we don't have to reparse the JSON file each time a new order comes in.
    var drinks: Array<Drink>? = null
    val password = "0010"
    var passwordGuess = ""

    override fun onCreate() {
        super.onCreate()
        // Parse our drink objects.
        drinks = jsonDirectory.DeserializeDrink(loadJsonData("drinks.json"))
    }

    // Return the saved array of drinks.
    fun loadDrinks(): Array<Drink>? {
        return drinks
    }

    private fun loadJsonData(fileName: String) : String {
        return applicationContext.assets.open(fileName).bufferedReader().use {
            it.readText()
        }
    }
}