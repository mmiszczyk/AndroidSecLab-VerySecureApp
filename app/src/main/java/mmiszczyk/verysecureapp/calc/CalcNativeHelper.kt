package mmiszczyk.verysecureapp.calc

import java.lang.Character.isDigit

class CalcNativeHelper {

    companion object{
        init {
            System.loadLibrary("verysecureapp")
        }
    }

    var calcStr: String = ""
    private external fun performCalculations(calcString: String): Int

    fun calculate() {
        if(calcStr.isEmpty()) return
        if(!isDigit(calcStr.last())) calcStr = calcStr.dropLast(1)
        calcStr = performCalculations(calcStr).toString()
    }

    fun appendDigit(digit: Int){
        calcStr += digit
    }

    private fun appendOperation(operation: Char){
        if(calcStr.isEmpty()) return
        if(!isDigit(calcStr.last())) calcStr = calcStr.dropLast(1)
        calcStr += operation
    }

    fun add(){
        appendOperation('+')
    }

    fun sub(){
        appendOperation('-')
    }

    fun mul(){
        appendOperation('*')
    }

    fun div(){
        appendOperation('/')
    }

    fun clear() {
        calcStr = ""
    }
}