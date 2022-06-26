package com.example.calculator.ui;

import com.example.calculator.model.Calculator;
import com.example.calculator.model.Operator;

public class CalculatorPresenter {

    private Calculator calculator;
    private CalculatorView calculatorView;


    public CalculatorPresenter(CalculatorView calculatorView, Calculator calculator) {
        this.calculator = calculator;
        this.calculatorView = calculatorView;
    }

    // public void key_1_Pressed() {что-то делаем}

    // Общий метод принимающий нажатую кнопку ЧИСЛА
    public void onDigitPressed(Integer integer) {

    }

    // Общий метод принимающий нажатую кнопку ОПЕРАТОРА
    public void onOperatorPressed(Operator operator) {
    }

    // Нажата ТОЧКА
    public void onDotPressed() {
    }
}
