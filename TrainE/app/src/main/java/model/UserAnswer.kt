package model

import java.util.*

object UserAnswer : Observable()
{
    var name: String = ""
        set(value) {
            field = value
            setChangedAndNotify("name")
        }
    var type: String = "Coffee"
        set(value) {
            field = value
            setChangedAndNotify("type")
        }
    var size: String = "Small"
        set(value) {
            field = value
            setChangedAndNotify("size")
        }
    var shots: Int = 0
        set(value) {
            field = value
            setChangedAndNotify("shots")
        }
    var goodOrBad: String = ""
        set(value) {
            field = value
            setChangedAndNotify("name")
        }
    var milk: String = "None"
        set(value) {
            field = value
            setChangedAndNotify("milk")
        }
    var milkTemp: String = ""
        set(value) {
            field = value
            setChangedAndNotify("milkTemp")
        }
    var flavorPrimary: String = "None"
        set(value) {
            field = value
            setChangedAndNotify("flavorPrimary")
        }
    var pumpsPrimary: Int = 0
        set(value) {
            field = value
            setChangedAndNotify("pumpsPrimary")
        }
    var flavorSecondary: String = "None"
        set(value) {
            field = value
            setChangedAndNotify("flavorSecondary")
        }
    var pumpsSecondary: Int = 0
        set(value) {
            field = value
            setChangedAndNotify("pumpsSecondary")
        }
    var temp: String = ""
        set(value) {
            field = value
            setChangedAndNotify("temp")
        }

    private fun setChangedAndNotify(field: Any) {
        setChanged()
        notifyObservers(field)
    }
}