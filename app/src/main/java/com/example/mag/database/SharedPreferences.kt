package com.example.mag.database

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    private val spLogin: SharedPreferences =
        context.getSharedPreferences("MyPref", Context.MODE_PRIVATE)

    fun saveData(usernameValue: String, birthdayValue: String, genderValue: String, heightValue: Int, weightValue: Int, isFill:Boolean) {
        val editor = sharedPreferences.edit()
        editor.putString("username",usernameValue)
        editor.putString("gender",genderValue)
        editor.putInt("height",heightValue)
        editor.putInt("weight",weightValue)
        editor.putString("birthday",birthdayValue)
        editor.putBoolean("isFill", isFill)

        editor.apply()
    }

    fun getData(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }
    fun getIsFill(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)

    }


    fun loginData(loginValue: String, passwordValue: String, isLogged: Boolean){
        val loginDataEditor = spLogin.edit()

        loginDataEditor.putString("login", loginValue)
        loginDataEditor.putString("password", passwordValue)
        loginDataEditor.putBoolean("isLogged", isLogged)

        loginDataEditor.apply()

    }

    fun getLogin(defaultValue: Boolean): Boolean{
        return spLogin.getBoolean("isLogged", defaultValue)

    }

}