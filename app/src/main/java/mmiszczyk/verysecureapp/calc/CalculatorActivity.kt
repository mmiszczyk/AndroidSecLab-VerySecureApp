package mmiszczyk.verysecureapp.calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import mmiszczyk.verysecureapp.R
import kotlinx.android.synthetic.main.activity_calculator.*

class CalculatorActivity : AppCompatActivity(), View.OnClickListener{

    companion object{
        val backend = CalcNativeHelper()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

    }

    override fun onClick(v: View?) {
        if(v !is Button) return
        val b: Button = v
        val value = b.text.toString()[0]
        if(value.isDigit()) backend.appendDigit(value.digitToInt())
        else when(value){
            '+'  -> backend.add()
            '-'  -> backend.sub()
            '*'  -> backend.mul()
            '/'  -> backend.div()
            '='  -> backend.calculate()
            else -> backend.clear()
        }
        result.text = backend.calcStr

    }
}