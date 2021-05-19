package com.wcisang.jetpackcomposemovies.navigation.util

import com.google.gson.Gson

inline fun <reified T> String.toObject() : T {
    return Gson().fromJson(this, T::class.java)
}

fun Any.toJson() : String {
    return Gson().toJson(this)
}