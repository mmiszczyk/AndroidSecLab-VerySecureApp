package mmiszczyk.verysecureapp.dev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mmiszczyk.verysecureapp.R
import kotlinx.android.synthetic.main.activity_debug_menu.textView3
import kotlinx.android.synthetic.main.activity_debug_menu.editTextTextPersonName2
import kotlinx.android.synthetic.main.activity_debug_menu.editTextTextPersonName3
import kotlinx.android.synthetic.main.activity_debug_menu.editTextTextPersonName4
import kotlinx.android.synthetic.main.activity_debug_menu.editTextTextPersonName5
import kotlinx.android.synthetic.main.activity_debug_menu.button2
import kotlinx.android.synthetic.main.activity_debug_menu.button4
import kotlinx.android.synthetic.main.activity_debug_menu.button5
import kotlinx.android.synthetic.main.activity_debug_menu.button6

class DebugMenu : AppCompatActivity() {

    val d = DebugHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debug_menu)
        button2.setOnClickListener{getPass()}
        button4.setOnClickListener{setPass()}
        button5.setOnClickListener{webView()}
        button6.setOnClickListener{calc()}
    }

    private fun getPass(){
        textView3.text = d.getPassword()
    }

    private fun setPass(){
        d.setPassword(editTextTextPersonName2.text.toString())
    }

    private fun webView(){
        d.runWebViewWithAddedJS(editTextTextPersonName3.text.toString(),
                                editTextTextPersonName4.text.toString())
    }

    private fun calc(){
        val ret = d.calculateRawCalcstring(editTextTextPersonName5.text.toString())
        editTextTextPersonName5.setText(ret)
    }

}