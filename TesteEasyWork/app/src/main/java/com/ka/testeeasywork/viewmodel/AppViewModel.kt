package com.ka.testeeasywork.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.ka.testeeasywork.data.User
import java.io.IOException


class AppViewModel : ViewModel() {
    val TAG: String = "TAG"

    val user: MutableLiveData<User> by lazy {
        MutableLiveData<User>()
    }

    fun setUserData(json: String) {
        try {
            val g = Gson()
            user.value = g.fromJson(json, User::class.java)
        } catch (e: JsonSyntaxException) {
            e.message
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun isJSONValid(jsonInString: String?): Boolean {
        return try {
            val mapper = ObjectMapper()
            mapper.readTree(jsonInString)
            true
        } catch (e: IOException) {
            Log.d(TAG, e.message.toString())
            false
        }
    }
}