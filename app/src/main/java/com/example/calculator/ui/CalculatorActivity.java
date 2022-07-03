package com.example.calculator.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.calculator.R;
import com.example.calculator.model.CalculatorImplementation;
import com.example.calculator.model.Operator;

import java.util.HashMap;
import java.util.Map;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    private EditText resultView;
    // СВЯЗКА между МОДЕЛЬЮ и UI
    private CalculatorPresenter calculatorPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ТЕМУ ДЛЯ АКТИВИТИ СТАВИМ ТУТ! ДО setContentView!!!
        // setTheme(R.style.Theme_Calculator_V2);

        // Тут хранятся настройки. ПЕРВОЕ - имя файла где хр-ся настройки, ВТОРОЕ - говорит о том,
        // что только само приложение будет иметь право читать содержимое данных настроек
        SharedPreferences preferences = getSharedPreferences("themes.xml", Context.MODE_PRIVATE);

        int theme = preferences.getInt("theme", R.style.Theme_Calculator);
        setTheme(theme);



        setContentView(R.layout.activity_calculator);

        resultView = findViewById(R.id.result);
        // запретим вводить символы с клавиатуры смартфона
        resultView.setShowSoftInputOnFocus(false);
        // очистим поле ВЫВОДА если кто-то захочет по нему кликнуть
        resultView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.display).equals(resultView.getText().toString())) {
                    resultView.setText("");
                }
            }
        });


        // передаем саму активити (View) т.к. она реализ. CalculatorView и модель (CalculatorImpl)
        calculatorPresenter = new CalculatorPresenter(this, new CalculatorImplementation());

        //Надо сказать Presenter-у, чот нажата какая-то кнопка, напр:
        /*findViewById(R.id.key_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculatorPresenter.key_1_Pressed();
            }
        });*/
        /** доступ к ресурсам как пример: **/
        String keyOne = getResources().getString(R.string.key_1);
        String keyOneShortWay = getString(R.string.key_1);
        float dimenOne = getResources().getDimension(R.dimen.result_size);
        int colorBTN = getResources().getColor(R.color.buttons_digit);
        int colorBTN1 = ContextCompat.getColor(this, R.color.buttons_digit);
        // доступ к View из layout
        View layout = getLayoutInflater().inflate(R.layout.activity_calculator, null);
        // доступ к drawable
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.field_for_result);
        /**********************************/

        /** Доступ к КОНФИГУРАЦИИ **/
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // что-то сделать!
        }
        /********************************/


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
                calculatorPresenter.onDigitPressed(digits.get(view.getId()));
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

        /** КНОПКИ ВЫБОРА ТЕМЫ **/

        Button themeOne = findViewById(R.id.choose_theme_1);
        Button themeTwo = findViewById(R.id.choose_theme_2);
        Button themeThree = findViewById(R.id.choose_theme_3);

        if (themeOne != null) {
            themeOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    preferences.edit() // Записываем в КЛЮЧ "theme" ЗНАЧЕНИЕ "R.style.Theme_Calculator"
                            .putInt("theme", R.style.Theme_Calculator)
                            .commit();  // - применяем немедленно
                    recreate();         // - пересоздаем активити

                }
            });
        }

        if (themeTwo != null) {
            themeTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    preferences.edit() // Записываем в КЛЮЧ "theme" ЗНАЧЕНИЕ "R.style.Theme_Calculator"
                            .putInt("theme", R.style.Theme_Calculator_V2)
                            .commit();  // - применяем немедленно
                    recreate();         // - пересоздаем активити

                }
            });
        }

        if (themeThree != null) {
            themeThree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    preferences.edit() // Записываем в КЛЮЧ "theme" ЗНАЧЕНИЕ "R.style.Theme_Calculator"
                            .putInt("theme", R.style.Theme_Calculator_V3)
                            .commit();  // - применяем немедленно
                    recreate();         // - пересоздаем активити

                }
            });
        }


    }

    @Override
    public void showResult(String result) {
        resultView.setText(result);
    }
}