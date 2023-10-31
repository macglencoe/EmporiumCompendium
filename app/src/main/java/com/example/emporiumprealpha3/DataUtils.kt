package com.example.emporiumprealpha3

import android.content.Context
import com.example.emporiumprealpha3.model.Cigar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

fun getJsonDataFromAsset(
    context: Context,
    fileName: String
): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use {
            it.readText()
        }
    } catch (exp: IOException) {
        exp.printStackTrace()
        return null
    }

    return jsonString
}

fun getCigarList(context: Context): MutableList<Cigar>{
    val jsonFileString = getJsonDataFromAsset(context = context, "demo_cigars.json")
    val type = object : TypeToken<List<Cigar>>(){}.type
    return Gson().fromJson(jsonFileString, type)
}