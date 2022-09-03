package mmiszczyk.verysecureapp.auth

import android.content.Context
import android.util.Log

class AuthenticationHelper(ctx: Context) {

    private val ctx = ctx
    private val authCommon = AuthCommon(ctx)
    private val TAG = this::class.simpleName

    fun isPasswordCorrect(pass: String): Boolean {
        Log.d(TAG, "isPasswordCorrect() called with the argument $pass")
        if(pass == getPassword()) return true
        return false
    }

    fun getPassword(): String? {
        return authCommon.getPassword()
    }

    fun setPassword(oldpass: String, newpass: String) {
        Log.d(TAG, "setPassword() called with the arguments $oldpass, $newpass")
        if(oldpass != getPassword()) return
        AuthService.setPassword(ctx, newpass)
    }
}