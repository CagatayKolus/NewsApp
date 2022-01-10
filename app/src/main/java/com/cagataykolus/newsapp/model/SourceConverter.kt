package com.cagataykolus.newsapp.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SourceConverter {
    companion object {
        private val gson = Gson()

        @TypeConverter
        @JvmStatic
        fun toBoilSource(data: String?): Source {
            if (data == null) {
                return Source("empty", "empty")
            }
            val listType = object : TypeToken<Source>() {}.type
            return gson.fromJson(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun fromBoilSource(data: Source): String {
            return gson.toJson(data)
        }
    }
}