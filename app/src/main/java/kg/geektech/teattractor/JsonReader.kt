package kg.geektech.teattractor

import android.content.Context
import android.util.Log
import org.json.JSONArray
import java.io.IOException

class JsonReader() {

    fun getJsonDataFromAsset(context: Context): String {
        val jsonString: String
        try {
            jsonString = context.assets.open("User.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return "null"
        }
        return jsonString
    }
}