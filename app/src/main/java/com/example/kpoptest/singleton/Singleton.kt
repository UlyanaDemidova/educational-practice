package com.example.kpoptest.singleton

import android.content.Context
import com.example.kpoptest.json.JsonReader
import com.example.kpoptest.model.Test
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Singleton {
    companion object {
        fun getTests(applicationContext : Context) : ArrayList<Test> =
            Gson().fromJson(
                JsonReader.getJsonDataFromAsset(applicationContext, "tests.json"),
                object : TypeToken<ArrayList<Test>>() {}.type)
    }
}