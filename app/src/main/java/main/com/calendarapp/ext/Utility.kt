package main.com.calendarapp.ext

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import com.fatboyindustrial.gsonjodatime.Converters
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.joda.time.DateTime

@SuppressWarnings
fun convertDateTimeToHeadline (dateTime: DateTime, language: Language = Language.DE) : String{
    val day = dateTime.dayOfMonth().get()
    val month = dateTime.monthOfYear().get()
    val dayMonth = "${if(day < 9) "0$day" else day}.${if(month < 9) "0$month" else month}."

    return when(language == Language.DE){
        true ->   "Training vom $dayMonth"
        false ->   "Training from $dayMonth"
    }
}

fun verifyAvailableNetwork(activity:AppCompatActivity):Boolean{
    val connectivityManager=activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo=connectivityManager.activeNetworkInfo
    return  networkInfo!=null && networkInfo.isConnected
}

object GsonObject {

    var gson: Gson
        private set

    init {
        gson = Converters.registerDateTime(GsonBuilder()).create()
    }
}

enum class Language {
    DE, EN
}

