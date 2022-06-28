package com.example.calculator.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.calculator.R;
import com.example.calculator.model.CalculatorImplementation;
import com.example.calculator.model.Operator;

import java.util.HashMap;
import java.util.Map;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView{


    private TextView resultView;
    // СВЯЗКА между МОДЕЛЬЮ и UI
    private CalculatorPresenter calculatorPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        resultView = findViewById(R.id.result);

        // передаем саму активити (View) т.к. она реализ. CalculatorView и модель (CalculatorImpl)
        calculatorPresenter = new CalculatorPresenter(this, new CalculatorImplementation());

        //Надо сказать Presenter-у, чот нажата какая-то кнопка, напр:
        /*findViewById(R.id.key_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculatorPresenter.key_1_Pressed();
            }
        });*/

        // НО мы сделаем не по одной, а загоним их все в MAP:
        Map<Integer, Integer> digits = new HashMap<>();
        digits.put(R.id.key_1, 1);
        //Log.d("TAG: this id we put in digits", String.valueOf(R.id.key_1));
        digits.put(R.id.key_2, 2);
        digits.put(R.id.key_3, 3);
        digits.put(R.id.key_4, 4);
        digits.put(R.id.key_5, 5);
        digits.put(R.id.key_6, 6);
        digits.put(R.id.key_7, 7);
        digits.put(R.id.key_8, 8);
        digits.put(R.id.key_9, 9);
        digits.put(R.id.key_0, 0);

        // и сделаем общий ClickListener на них
        View.OnClickListener digitClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // и передадим, то что нажато в Presenter:
                calculatorPresenter.onDigitPressed(digits.get(view.getId()));  // ???????
                //Log.d("TAG: this ID we want to find:", String.valueOf(view.getId()));
                //Log.d("TAG: this is the value of this ID:", String.valueOf(digits.get(view.getId())));
            }
        };
        // Выставляем для кнопок этот ClickListener
        findViewById(R.id.key_1).setOnClickListener(digitClickListener);
        findViewById(R.id.key_2).setOnClickListener(digitClickListener);
        findViewById(R.id.key_3).setOnClickListener(digitClickListener);
        findViewById(R.id.key_4).setOnClickListener(digitClickListener);
        findViewById(R.id.key_5).setOnClickListener(digitClickListener);
        findViewById(R.id.key_6).setOnClickListener(digitClickListener);
        findViewById(R.id.key_7).setOnClickListener(digitClickListener);
        findViewById(R.id.key_8).setOnClickListener(digitClickListener);
        findViewById(R.id.key_9).setOnClickListener(digitClickListener);
        findViewById(R.id.key_0).setOnClickListener(digitClickListener);

        // ТО же самое для кнопок ОПЕРАТОРА:
        Map<Integer, Operator> operators = new HashMap<>();
        operators.put(R.id.key_plus, Operator.ADD);
        operators.put(R.id.key_minus, Operator.SUB);
        operators.put(R.id.key_divide, Operator.DIV);
        operators.put(R.id.key_multiply, Operator.MULT);
        operators.put(R.id.key_equal, Operator.EQUAL);

        View.OnClickListener operatorClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculatorPresenter.onOperatorPressed(operators.get(view.getId()));
            }
        };

        findViewById(R.id.key_plus).setOnClickListener(operatorClickListener);
        findViewById(R.id.key_minus).setOnClickListener(operatorClickListener);
        findViewById(R.id.key_divide).setOnClickListener(operatorClickListener);
        findViewById(R.id.key_multiply).setOnClickListener(operatorClickListener);
        findViewById(R.id.key_equal).setOnClickListener(operatorClickListener);

        // ТО же самое для кнопки ТОЧКИ:
        findViewById(R.id.key_dot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculatorPresenter.onDotPressed();
            }
        });


    }

    @Override
    public void showResult(String result) {
        resultView.setText(result);
    }
}