package jsonDirectory
import com.google.gson.Gson
import model.Drink

fun DeserializeDrink(jsonData: String) : Array<Drink> //returns an array of Model.Drink objects
{
    val gson = Gson() // Gson is an extension/package I downloaded for parsing the java file
    return gson.fromJson(jsonData, Array<Drink>::class.java)
}



