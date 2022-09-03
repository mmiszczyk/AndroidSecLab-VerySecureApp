package mmiszczyk.verysecureapp.calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import mmiszczyk.verysecureapp.R
import kotlinx.android.synthetic.main.activity_calc_deep_link_handler.textView

class CalcDeepLinkHandler : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc_deep_link_handler)
        val TAG = this::class.simpleName
        val uri = intent?.data
        val ops = listOf('+','-','*','/')
        val backend = CalcNativeHelper()
        var wasLastSegmentANumber = false
        uri?.pathSegments?.forEach loop@{
            Log.d(TAG, "Parsed segment: $it")
            if(it.all{c->c.isDigit()}){
                backend.calcStr+=it
                wasLastSegmentANumber = true
                return@loop
            }
            if (!wasLastSegmentANumber) return@loop
            val op = it[0]
            if(!ops.contains(op)) return@loop
            wasLastSegmentANumber = false
            when(op){
                '+' -> backend.add()
                '-' -> backend.sub()
                '*' -> backend.mul()
                '/' -> backend.div()
            }
        }
        backend.calculate()
        textView.text = backend.calcStr
        backend.clear()
    }
}