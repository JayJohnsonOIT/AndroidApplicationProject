package com.techbros.traine.viewModels

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.techbros.traine.BR
import model.Order
import java.util.*

class OrderViewModel(private val order: Order): Observer, BaseObservable() {
    init {
        order.addObserver(this)
    }

    /// Notify the UI when change event emitting from Model is received.
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

    val specification: String
        @Bindable get() {
            return order.size + ", " + order.temp + ", " + order.milk
        }

    val name: String
        @Bindable get() {
            return order.name
        }
    val type: String
        @Bindable get() {
            return order.type
        }
    val size: String
        @Bindable get() {
            return order.size
        }
    val shots: String
        @Bindable get() {
            return order.shots.toString()
        }
    val goodOrBad: String
        @Bindable get() {
            return order.goodOrBad
        }
    val milk: String
        @Bindable get() {
            return order.milk
        }
    val milkTemp: String
        @Bindable get() {
            return order.milkTemp
        }
    val flavorPrimary: String
        @Bindable get() {
            return order.flavorPrimary
        }
    val pumpsPrimary: String
        @Bindable get() {
            return order.pumpsPrimary.toString()
        }
    val flavorSecondary: String
        @Bindable get() {
            return order.flavorSecondary
        }
    val pumpsSecondary: String
        @Bindable get() {
            return order.pumpsSecondary.toString()
        }
    val temp: String
        @Bindable get() {
            return order.temp
        }
}