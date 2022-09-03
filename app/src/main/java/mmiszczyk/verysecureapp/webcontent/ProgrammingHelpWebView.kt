package mmiszczyk.verysecureapp.webcontent

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import mmiszczyk.verysecureapp.BuildConfig
import mmiszczyk.verysecureapp.MainActivity
import mmiszczyk.verysecureapp.ProxyActivity
import java.net.URLEncoder

class ProgrammingHelpWebView(private val ctx: Context, private var query: String,
                             private var js: String?) {
    @SuppressLint("SetJavaScriptEnabled")
    fun setup(): WebView {
        val wv = WebView(ctx)
        wv.webViewClient = CustomClient(js)
        WebView.setWebContentsDebuggingEnabled(BuildConfig.DEBUG)
        wv.settings.javaScriptEnabled = true
        wv.settings.javaScriptCanOpenWindowsAutomatically = true
        wv.addJavascriptInterface(JSMethods(ctx), "verysecureapp")
        query = URLEncoder.encode(query, "utf-8")
        wv.loadUrl("https://google.com/search?q=site%3Astackoverflow.com+$query")
        return wv
    }

    class JSMethods(private val ctx: Context){
        @JavascriptInterface
        fun startAppComponent(componentType: String?, clsName: String?,
                              action: String?, extraKey: String?, extraVal: String?){
            val i1 = Intent()
            val i2 = Intent()
            if(clsName == null || componentType == null) return;
            if(action != null) i1.action = action
            i1.component = ComponentName("mmiszczyk.verysecureapp", clsName)
            i1.putExtra(extraKey, extraVal)
            i2.setClass(ctx, ProxyActivity::class.java)
            i2.putExtra("INNER_INTENT", i1)
            i2.putExtra("INNER_INTENT_TYPE", componentType)
            ctx.startActivity(i2)
        }

        @JavascriptInterface
        fun notifyUser(msg: String?){
            Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show()
        }

        @JavascriptInterface
        fun forceLogout(){
            val i = Intent()
            i.setClass(ctx, MainActivity::class.java)
            ctx.startActivity(i)
        }
    }

    class CustomClient(val js:String?): WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            js?.let {
                Log.d("CustomClient", "Executing js: $js")
                view?.evaluateJavascript(it, null)
            }
        }
    }
}