package mmiszczyk.verysecureapp.webcontent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mmiszczyk.verysecureapp.R

class ProgrammingHelpWebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_programming_help_web_view)
        intent.getStringExtra("query")?.let {
            setContentView(
                ProgrammingHelpWebView(
                    this,
                    it,
                    if (intent.hasExtra("js")) intent.getStringExtra("js") else ""
                ).setup()
            )
        }
    }
}