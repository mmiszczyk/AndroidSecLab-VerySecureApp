package mmiszczyk.verysecureapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_logged_in.calcButton
import kotlinx.android.synthetic.main.activity_logged_in.changePassButton
import kotlinx.android.synthetic.main.activity_logged_in.helpButton
import mmiszczyk.verysecureapp.auth.ChangePassActivity
import mmiszczyk.verysecureapp.calc.CalculatorActivity
import mmiszczyk.verysecureapp.webcontent.ProgrammingHelpActivity

class LoggedInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)
        calcButton.setOnClickListener {
            val i = Intent()
            i.setClass(applicationContext, CalculatorActivity::class.java)
            startActivity(i)
        }
        changePassButton.setOnClickListener {
            val i = Intent()
            i.setClass(applicationContext, ChangePassActivity::class.java)
            startActivity(i)
        }
        helpButton.setOnClickListener {
            val i = Intent()
            i.setClass(applicationContext, ProgrammingHelpActivity::class.java)
            startActivity(i)
        }
    }

}