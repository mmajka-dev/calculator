package com.mmajka.calculator.basic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mmajka.calculator.R
import com.mmajka.calculator.databinding.FragmentBasicBinding
import kotlinx.android.synthetic.main.layout_keyboard.*
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder


class basic : Fragment() {

    private lateinit var bind: FragmentBasicBinding
    private lateinit var resultTextView: TextView
    private lateinit var operationTextView: TextView
    private lateinit var viewModel: BasicViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_basic, container, false)
        resultTextView = bind.score
        operationTextView = bind.operation

        viewModel = ViewModelProvider(this).get(BasicViewModel::class.java)
        viewModel.initStates()
        clickListeners()

        return bind.root
    }

    private fun clickListeners(){
        bind.include.one.setOnClickListener {
            onDecimal(it, resultTextView)
        }

        bind.include.two.setOnClickListener {
            onDecimal(it, resultTextView)
        }

        bind.include.three.setOnClickListener {
            onDecimal(it, resultTextView)
        }

        bind.include.four.setOnClickListener {
            onDecimal(it, resultTextView)
        }

        bind.include.five.setOnClickListener {
            onDecimal(it, resultTextView)
        }

        bind.include.six.setOnClickListener {
            onDecimal(it, resultTextView)
        }

        bind.include.seven.setOnClickListener {
            onDecimal(it, resultTextView)
        }

        bind.include.eight.setOnClickListener {
            onDecimal(it, resultTextView)
        }
        bind.include.nine.setOnClickListener {
            onDecimal(it, resultTextView)
        }

        bind.include.zero.setOnClickListener {
            onDecimal(it, resultTextView)
        }

        bind.include.actionClear.setOnClickListener {
            onResultClear(operationTextView, resultTextView )
        }

        bind.include.actionPlus.setOnClickListener {
            onOperator(resultTextView, it)
        }

        bind.include.actionEqual.setOnClickListener {
            onEqual(resultTextView, operationTextView)
        }

        bind.include.actionX.setOnClickListener {
            onOperator(resultTextView, it)

        }
        bind.include.actionMinus.setOnClickListener {
            onOperator(resultTextView, it)
        }

        bind.include.actionDivision.setOnClickListener {
            onOperator(resultTextView, it)
        }

        bind.include.dot.setOnClickListener {
            onDot(resultTextView)
        }

        bind.include.actionPercent.setOnClickListener {
            onOperator(resultTextView, it)
        }


        bind.include.actionBackspace.setOnClickListener{
            val text = resultTextView.text
            if (text.isNotEmpty()){
                resultTextView.text = text.dropLast(1)
            }else{
                resultTextView.text = ""
            }
        }
    }

    private fun onDecimal(button: View, result: TextView){
        viewModel.onDecimal(button, result)
    }

    private fun onEqual(result: TextView, operation: TextView){
        viewModel.onEqual(result, operation)
        operation.text = result.text
        viewModel.result.observe(viewLifecycleOwner, Observer { newResult ->
            result.text = newResult.toString()
        })
    }

    private fun onDot(view: TextView){
        viewModel.onDot(view)
    }

    private fun onOperator(view: TextView, button: View){
        viewModel.onOperator(view, button)
    }

    private fun onResultClear(operation: View, result: View){
        viewModel.onResultClear(operation, result)
    }

}