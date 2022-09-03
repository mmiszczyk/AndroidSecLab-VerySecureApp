package mmiszczyk.verysecureapp.dev

import android.content.Context
import android.content.Intent
import mmiszczyk.verysecureapp.BuildConfig
import mmiszczyk.verysecureapp.auth.AuthCommon
import mmiszczyk.verysecureapp.calc.CalcNativeHelper
import mmiszczyk.verysecureapp.webcontent.ProgrammingHelpWebViewActivity

class DebugHelper(val ctx: Context) {
    public fun getPassword(): String?{
        dontRunInProd()
        return AuthCommon(ctx).getPassword()
    }

    public fun setPassword(password: String){
        dontRunInProd()
        AuthCommon(ctx).setPassword(password)
    }
    public fun runWebViewWithAddedJS(query:String, js: String){
        dontRunInProd()
        val i = Intent()
        i.setClass(ctx, ProgrammingHelpWebViewActivity::class.java)
        i.putExtra("query", query)
        i.putExtra("js", js)
        ctx.startActivity(i)
    }

    public fun calculateRawCalcstring(calcstring: String): String{
        dontRunInProd()
        val calc = CalcNativeHelper()
        calc.calcStr = calcstring
        calc.calculate()
        return calc.calcStr
    }

    private fun dontRunInProd(){
        if(!BuildConfig.DEBUG) throw RuntimeException("Cannot run debug functions in prod build")
    }
}