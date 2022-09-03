package mmiszczyk.verysecureapp.auth

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.ResultReceiver
import android.util.Log

private const val ACTION_GET_PASSWORD = "mmiszczyk.verysecureapp.auth.action.GET_PASSWORD"
private const val ACTION_SET_PASSWORD = "mmiszczyk.verysecureapp.auth.action.SET_PASSWORD"

private const val EXTRA_RECV    = "mmiszczyk.verysecureapp.auth.extra.RECV"
private const val EXTRA_NEWPASS = "mmiszczyk.verysecureapp.auth.extra.NEWPASS"


private var sp: SharedPreferences? = null
private var auth: AuthCommon? = null

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.

 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.

 */
class AuthService : IntentService("AuthService") {

    private val TAG = this::class.simpleName

    override fun onHandleIntent(intent: Intent?) {
        if(auth == null) auth = AuthCommon(applicationContext)
        when (intent?.action) {
            ACTION_GET_PASSWORD -> {
                val recv = intent.getParcelableExtra<ResultReceiver>(EXTRA_RECV) ?: return
                val b = Bundle()
                b.putString("password", getPassword())
                recv.send(0, b)
            }
            ACTION_SET_PASSWORD -> {
                val newpass = intent.getStringExtra(EXTRA_NEWPASS)
                setPassword(newpass)
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private fun getPassword(): String? {
        return auth?.getPassword()
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private fun setPassword(newpass: String?) {
        auth?.setPassword(newpass)
    }

    companion object {
        /**
         * Starts this service to perform action Foo with the given parameters. If
         * the service is already performing a task this action will be queued.
         *
         * @see IntentService
         */

        private val TAG = this::class.simpleName

        // TODO: Customize helper method
        @JvmStatic
        fun getPassword(context: Context, recv: ResultReceiver) {
            val intent = Intent(context, AuthService::class.java).apply {
                action = ACTION_GET_PASSWORD
                putExtra(EXTRA_RECV, recv)
            }
            Log.d(TAG, "Calling AuthService for password")
            context.startService(intent)
        }

        /**
         * Starts this service to perform action Baz with the given parameters. If
         * the service is already performing a task this action will be queued.
         *
         * @see IntentService
         */
        // TODO: Customize helper method
        @JvmStatic
        fun setPassword(context: Context, newpass: String) {
            val intent = Intent(context, AuthService::class.java).apply {
                action = ACTION_SET_PASSWORD
                putExtra(EXTRA_NEWPASS, newpass)
            }
            context.startService(intent)
        }
    }
}