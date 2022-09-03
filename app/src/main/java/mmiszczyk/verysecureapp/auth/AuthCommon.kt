package mmiszczyk.verysecureapp.auth

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class AuthCommon(ctx: Context) {
    private val TAG = this::class.simpleName
    private var sp: SharedPreferences? = ctx.getSharedPreferences("password_store",
                                                                  Context.MODE_PRIVATE)
    fun getPassword(): String? {
        Log.d(TAG, "Retrieving password from shared prefs")
        return sp?.getString("password", "UKcxsUYb37")
    }

    fun setPassword(newpass: String?) {
        val editor = sp?.edit()
        editor?.putString("password", newpass)
        editor?.apply()
    }
}