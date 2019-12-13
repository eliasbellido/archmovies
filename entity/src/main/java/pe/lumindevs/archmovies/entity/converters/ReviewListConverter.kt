package pe.lumindevs.archmovies.entity.converters

import pe.lumindevs.archmovies.entity.Review
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/** RevierwListConverter is a converter class for type converting a entity.*/
open class ReviewListConverter{

    @TypeConverter
    fun fromString(value: String): List<Review>?{
        val listType = object : TypeToken<List<Review>>(){}.type

        return Gson().fromJson<List<Review>>(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Review>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}