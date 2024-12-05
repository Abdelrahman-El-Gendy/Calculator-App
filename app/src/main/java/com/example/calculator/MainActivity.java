package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    TextView resultTv;
    boolean isEqualClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
    }

    public void onDigitClick(View view) {
        if (view instanceof Button) {
            Button clickedBtn = ((Button) view);

            String digit = clickedBtn.getText().toString();
            if (isEqualClicked) {
                resultTv.setText(digit);
                isEqualClicked = false;
            } else {
                resultTv.append(digit);
            }
        }
    }

    String savedOperator = "";
    String savedResult = "";

    public void onOperatorClick(View view) {
        if (view instanceof Button) {
            Button clickedOperator = ((Button) view);
            if (savedOperator.isEmpty()) {
                savedOperator = clickedOperator.getText().toString();
                savedResult = resultTv.getText().toString();
                resultTv.setText("");
            } else {
                String RHS = resultTv.getText().toString();
                savedResult = Calc(savedResult, savedOperator, RHS);
                resultTv.setText("");
            }
        }
    }

    private String Calc(String lhs, String savedOperator, String rhs) {
        if (lhs.isEmpty() || savedOperator.isEmpty() || rhs.isEmpty()) {
            return "";
        }
        double number_1 = Double.parseDouble(lhs);
        double number_2 = Double.parseDouble(rhs);
        double result = 0.0;
        if (savedOperator.equals("*")) {
            result = number_1 * number_2;
        } else if (savedOperator.equals("+")) {
            result = number_1 + number_2;
        } else if (savedOperator.equals("/")) {
            result = number_1 / number_2;
        } else if (savedOperator.equals("-")) {
            result = number_1 - number_2;
        }
        return result + "";
    }

    public void onEqualClick(View view) {
        if (view instanceof Button) {
            String RHS = resultTv.getText().toString();
            savedResult = Calc(savedResult, savedOperator, RHS);
            resultTv.setText(savedResult);
            savedOperator = "";
            savedResult = "";
            isEqualClicked = true;
        }
    }
}
