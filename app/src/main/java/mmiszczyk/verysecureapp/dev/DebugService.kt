package mmiszczyk.verysecureapp.dev

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.os.Bundle
import android.os.ResultReceiver

// TODO: Rename actions, choose action names that describe tasks that this
// IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
private const val ACTION_GETPASS = "mmiszczyk.verysecureapp.dev.action.GETPASS"
private const val ACTION_SETPASS = "mmiszczyk.verysecureapp.dev.action.SETPASS"
private const val ACTION_WEBVIEW = "mmiszczyk.verysecureapp.dev.action.WEBVIEW"
private const val ACTION_RAWCALC = "mmiszczyk.verysecureapp.dev.action.RAWCALC"

// TODO: Rename parameters
private const val EXTRA_PASWD = "mmiszczyk.verysecureapp.dev.extra.PASWD"
private const val EXTRA_QUERY = "mmiszczyk.verysecureapp.dev.extra.QUERY"
private const val EXTRA_JSCRI = "mmiszczyk.verysecureapp.dev.extra.JSCRI"
private const val EXTRA_RCALC = "mmiszczyk.verysecureapp.dev.extra.RCALC"
private const val EXTRA_RECIV = "mmiszczyk.verysecureapp.dev.extra.RECIV"

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.

 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.

 */
class DebugService : IntentService("DebugService") {

    val d = DebugHelper(this)

    override fun onHandleIntent(intent: Intent?) {
        when (intent?.action) {
            ACTION_GETPASS -> {
                val pass = d.getPassword()
                intent.getParcelableExtra<ResultReceiver>(EXTRA_RECIV)?.let {
                    val b = Bundle()
                    b.putString("pass", pass)
                    it.send(0, b)
                }
            }
            ACTION_SETPASS -> {
                intent.getStringExtra(EXTRA_PASWD)?.let { d.setPassword(it) }
            }
            ACTION_WEBVIEW -> {
                val query = intent.getStringExtra(EXTRA_QUERY)
                val js = intent.getStringExtra(EXTRA_JSCRI)
                query?.let { js?.let { it1 -> d.runWebViewWithAddedJS(it, it1) } }
            }
            ACTION_RAWCALC -> {
                intent.getStringExtra(EXTRA_RCALC)?.let {
                    val ret = d.calculateRawCalcstring(it)
                    val recv = intent.getParcelableExtra<ResultReceiver>(EXTRA_RECIV)
                    recv?.send(ret.toInt(), null)
                }
            }
        }
    }
}