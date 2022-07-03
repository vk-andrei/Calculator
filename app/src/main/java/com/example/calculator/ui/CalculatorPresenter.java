package com.example.calculator.ui;

import android.util.Log;

import com.example.calculator.model.Calculator;
import com.example.calculator.model.Operator;

import java.text.DecimalFormat;

public class CalculatorPresenter {

    private final DecimalFormat formater = new DecimalFormat("#.###");

    private final Calculator calculator;
    private final CalculatorView calculatorView;

    private double argOne;
    private Double argTwo;
    private Operator selectedOperator;


    public CalculatorPresenter(CalculatorView calculatorView, Calculator calculator) {
        this.calculator = calculator;
        this.calculatorView = calculatorView;
    }

    // Общий метод принимающий нажатую кнопку ЧИСЛА
    public void onDigitPressed(Integer digit) {

        if (argTwo == null) {
            argOne = argOne * 10 + digit;
            //Log.d("TAG", "onDigitPressed. argTwo = null. argOne = " + argOne);

            showFormatted(argOne);
        } else {
            argTwo = argTwo * 10 + digit;
            //Log.d("TAG", "onDigitPressed. argTwo != null, argTwo = " + argTwo);

            showFormatted(argTwo);
        }
    }

    // Общий метод принимающий нажатую кнопку ОПЕРАТОРА
    public void onOperatorPressed(Operator operator) {
        if (selectedOperator != null) {
            //Log.d("TAG", "onOperatorPressed. selOperator != null, = " + selectedOperator);

            argOne = calculator.perform(argOne, argTwo, selectedOperator);
            //Log.d("TAG", "onOperatorPressed. argOne = " + argOne);
            showFormatted(argOne);
        }

        //Log.d("TAG", "onOperatorPressed. selOperator = " + selectedOperator);
        argTwo = 0.0;
        selectedOperator = operator;
        //Log.d("TAG", "onOperatorPressed. argTwo = " + argTwo + " selOperator = " + selectedOperator);

    }

    // Нажата ТОЧКА
    public void onDotPressed() {
        if (argTwo == null) {
            argOne = argOne * 0.1;
            //Log.d("TAG", "onDotPressed. argTwo = null, argOne = " + argOne);
            showFormatted(argOne);
        } else {
            argTwo = argTwo * 0.1;
            //Log.d("TAG", "onDotPressed. argTwo != null, argTwo = " + argTwo);
            showFormatted(argTwo);
        }
    }

    public void showFormatted(double value) {
        calculatorView.showResult(formater.format(value));
    }
}
