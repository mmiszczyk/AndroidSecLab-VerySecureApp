package mmiszczyk.verysecureapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ProxyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proxy)
        val i = intent
        val inner = i?.getParcelableExtra<Intent>("INNER_INTENT")
        val innerType = i?.getStringExtra("INNER_INTENT_TYPE")
        handleInnerIntent(inner, innerType)
    }

    private fun handleInnerIntent(i: Intent?, t: String?) {
        when(t){
            "act"  -> startActivity(i)
            "srv"  -> startService(i)
            "bcst" -> sendBroadcast(i)
            else -> throw RuntimeException("INNER_INTENT_TYPE must be either 'act', 'srv' or" +
                    "'bcst'")
        }
    }
}