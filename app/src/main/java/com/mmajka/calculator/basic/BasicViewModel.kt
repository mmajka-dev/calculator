package com.mmajka.calculator.basic

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.ArithmeticException
import kotlin.math.roundToInt

class BasicViewModel: ViewModel(){

    var _lastNumeric = MutableLiveData<Boolean>()
    val lastNumeric: LiveData<Boolean>
        get() = _lastNumeric

    var _lastDot = MutableLiveData<Boolean>()
    val lastDot: LiveData<Boolean>
        get() = _lastDot

    var _stateError = MutableLiveData<Boolean>()
    val stateError: LiveData<Boolean>
        get() = _stateError

    var _result = MutableLiveData<Double>()
    val result: LiveData<Double>
        get() = _result

    //region-------------------------Funs-----------------------------------------------------------
    fun initStates(){
        _lastNumeric.value = false
        _lastDot.value = false
        _stateError.value = false
    }

    fun onClear(view: View){
        (view as TextView).text = ""
        initStates()
    }


    fun onEqual(resultTV: View, operation: TextView){
        if (_lastNumeric.value!! && !_stateError.value!!){
            val textExpression = (resultTV as TextView).text.toString()
            val expression = ExpressionBuilder(textExpression).build()
            try {
                _result.value = expression.evaluate()
                _lastDot.value = true
            }catch (ex: ArithmeticException){
                Log.e("ERROR", "${ex.message}")
                _stateError.value = true
                _lastNumeric.value = false
            }
        }
    }



    fun onDot(view: TextView){
        if (_lastNumeric.value!! && !_stateError.value!! && !_lastDot.value!!) {
            view.append(".")
            _lastNumeric.value = false
            _lastDot.value = true
        }
    }

    fun onOperator(view: TextView, button: View){
        if (_lastNumeric.value!! && !_stateError.value!!) {
            val text = (button as Button).text.toString()
            view.append(text)
            _lastNumeric.value = false
            _lastDot.value = false

        }
    }

    fun onDecimal(button: View, resultTextView: TextView){
        if (_stateError.value!!){
            resultTextView.text = (button as Button).text
            _stateError.value = false
        }else{
            resultTextView.append((button as Button).text)
        }
        _lastNumeric.value = true
    }

    fun onResultClear(operation: View, result: View){
        (operation as TextView).text = ""
        (result as TextView).text = ""
        initStates()
    }



    //endregion
}