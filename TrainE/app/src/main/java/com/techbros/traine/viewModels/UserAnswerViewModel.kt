package com.techbros.traine.viewModels

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.techbros.traine.BR
import model.UserAnswer
import java.util.*

class UserAnswerViewModel(private val user: UserAnswer): Observer, BaseObservable() {
    init {
        user.addObserver(this)
    }

    // Notify the UI when change event emitting from Model is received.
    override fun update(p0: Observable?, p1: Any?) {
        if (p1 is String) {
            if  (p1 == "name") {
                notifyPropertyChanged(BR.name)
            }
            if  (p1 == "type") {
                notifyPropertyChanged(BR.type)
            }
            if  (p1 == "goodOrBad") {
                notifyPropertyChanged(BR.goodOrBad)
            }
            if  (p1 == "size") {
                notifyPropertyChanged(BR.size)
            }
            if  (p1 == "milk") {
                notifyPropertyChanged(BR.milk)
            }
            if  (p1 == "milkTemp") {
                notifyPropertyChanged(BR.milkTemp)
            }
            if  (p1 == "flavorPrimary") {
                notifyPropertyChanged(BR.flavorPrimary)
            }
            if  (p1 == "flavorSec") {
                notifyPropertyChanged(BR.flavorSecondary)
            }
            if  (p1 == "temp") {
                notifyPropertyChanged(BR.temp)
            }
        }
        if (p1 is Int) {
            if  (p1 == "shots") {
                notifyPropertyChanged(BR.shots)
            }
            if  (p1 == "pumpsPrimary") {
                notifyPropertyChanged(BR.pumpsPrimary)}
            if  (p1 == "pumpsSecondary")
            {
                notifyPropertyChanged(BR.pumpsSecondary)
            }

        }
    }

    val name: String
        @Bindable get() {
            return user.name
        }
    val type: String
        @Bindable get() {
            return user.type
        }
    val size: String
        @Bindable get() {
            return user.size
        }
    val shots: String
        @Bindable get() {
            return user.shots.toString()
        }
    val goodOrBad: String
        @Bindable get() {
            return user.goodOrBad
        }
    val milk: String
        @Bindable get() {
            return user.milk
        }
    val milkTemp: String
        @Bindable get() {
            return user.milkTemp
        }
    val flavorPrimary: String
        @Bindable get() {
            return user.flavorPrimary
        }
    val pumpsPrimary: String
        @Bindable get() {
            return user.pumpsPrimary.toString()
        }
    val flavorSecondary: String
        @Bindable get() {
            return user.flavorSecondary
        }
    val pumpsSecondary: String
        @Bindable get() {
            return user.pumpsSecondary.toString()
        }
    val temp: String
        @Bindable get() {
            return user.temp
        }
}