package kg.geektech.teattractor.ui.user_activity

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kg.geektech.teattractor.JsonReader
import kg.geektech.teattractor.User

class UserViewModel(app: Application) : AndroidViewModel(app) {

    private val jsonReader = JsonReader()
    private lateinit var user: User
    val data = MutableLiveData<User>()

    fun jsonToGson() {
        val jsonFileString = jsonReader.getJsonDataFromAsset(getApplication())
        Log.d("Ray", jsonFileString)
        val gson = Gson()
        val listUserType = object : TypeToken<List<User>>() {}.type
        var user: List<User> = gson.fromJson(jsonFileString, listUserType)
        this.user = user[0]
    }


    fun getUserInfo(): LiveData<User> {

        data.value = user
        return data
    }

}