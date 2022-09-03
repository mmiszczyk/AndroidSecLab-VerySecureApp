package mmiszczyk.verysecureapp.webcontent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_programming_help.query
import kotlinx.android.synthetic.main.activity_programming_help.searchbutton
import mmiszczyk.verysecureapp.R

class ProgrammingHelpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_programming_help)
        searchbutton.setOnClickListener {
            val i = Intent()
            i.setClass(applicationContext, ProgrammingHelpWebViewActivity::class.java)
            i.putExtra("query", query.text.toString())
            startActivity(i)
        }
    }
}