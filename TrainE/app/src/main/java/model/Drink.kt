package model
data class Drink //There are 10 drinks. Element 0 is test case
    (
    val name: String, //Name of the drink
    val type: String, //Coffee or Tea (Just Coffee for now)
    val size: String, //Small, Medium, Large. (Just Medium for now)
    val shots: Int, //number of shots of Coffee
    val milk: String, // Chocolate Milk, Regular, None, etc
    val flavorPrimary: String, // White Chocolate, Caramel, etc
    val pumpsPrimary: Int, // Number of pumps for primary flavor
    val flavorSecondary: String, // Cinnamon, etc
    val pumpsSecondary: Int, // Number of pumps for secondary flavor
    val temp: String  //Hot or Cold
)