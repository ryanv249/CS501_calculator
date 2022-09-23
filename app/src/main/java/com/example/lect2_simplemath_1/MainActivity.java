package com.example.lect2_simplemath_1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

//import CalculatorFunctions;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private TextView storage;
    private Button calc, clear, btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnDec;
    private RadioGroup set;
    private RadioButton add, sub, mult, div, mod;
    private Switch sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storage = (TextView) findViewById(R.id.storage);
        display = (EditText) findViewById(R.id.display);

        calc = (Button) findViewById(R.id.calc);
        clear = (Button) findViewById(R.id.clear);

        sign = (Switch) findViewById(R.id.signB);

        set = (RadioGroup)findViewById(R.id.set);
        add = (RadioButton)findViewById(R.id.add);
        sub = (RadioButton)findViewById(R.id.sub);
        mult = (RadioButton)findViewById(R.id.mult);
        div = (RadioButton)findViewById(R.id.div);
        mod = (RadioButton)findViewById(R.id.mod);

        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btnDec = (Button) findViewById(R.id.btnDec);




    calc.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(display.getText().toString().equals("") || storage.getText().toString().equals("~")){
                return;
            }
            else if(display.getText().toString().equals(".") || storage.getText().toString().equals(".")){
                display.setText("Err: Improper input");
                storage.setText("~");
                sign.setChecked(false);
                set.clearCheck();
                return;
            }
            else if(display.getText().toString().length() > 1 && display.getText().toString().substring(0,2).equals("-.")
                    || (storage.getText().toString().length() > 1 &&storage.getText().toString().substring(0,2).equals("-."))){
                display.setText("Err: Improper input");
                storage.setText("~");
                sign.setChecked(false);
                set.clearCheck();
                return;
            }

            String temp1 = display.getText().toString();
            String temp2 = storage.getText().toString();

            int decimal = 0;
            boolean result1 = true;
            for(int i = 0 ; i < temp1.length(); i++) {
                if(i == 0 && temp1.charAt(0) == '-'){
                    continue;
                }
                if (Character.isDigit(temp1.charAt(i)) == false) {
                    if (temp1.charAt(i) == '.') {
                        decimal += 1;
                        if (decimal <= 1)
                            continue;
                        display.setText("Err: Improper input");
                        storage.setText("~");
                        sign.setChecked(false);
                        set.clearCheck();
                        return;
                    }
                    result1 = false;
                    break;
                }
            }

            decimal = 0;
            boolean result2 = true;
            for(int i = 0 ; i < temp2.length(); i++) {
                if(i == 0 && temp1.charAt(0) == '-'){
                    continue;
                }
                if (Character.isDigit(temp2.charAt(i)) == false) {
                    if (temp2.charAt(i) == '.') {
                        decimal += 1;
                        if (decimal <= 1)
                            continue;
                        display.setText("Err: Improper input");
                        storage.setText("~");
                        sign.setChecked(false);
                        set.clearCheck();
                        return;
                    }
                    result2 = false;
                    break;
                }
            }

            if(result1 == false && result2 == false){
                display.setText("Err: Improper input");
                storage.setText("~");
                sign.setChecked(false);
                set.clearCheck();
            }

            else{
                Double val_1 = Double.parseDouble(storage.getText().toString());
                Double val_2 = Double.parseDouble(display.getText().toString());

                Double funct = 0.0;
                if (add.isChecked())
                    funct = CalculatorFunctions.addition(val_1, val_2);
                else if (sub.isChecked())
                    funct = CalculatorFunctions.subtraction(val_1, val_2);
                else if (mult.isChecked())
                    funct = CalculatorFunctions.mutliplication(val_1, val_2);
                else if (div.isChecked())
                    funct = CalculatorFunctions.division(val_1, val_2);
                else if (mod.isChecked())
                    funct = CalculatorFunctions.modula(val_1, val_2);
                else{
                    display.setText("Err: Select Operation");
                    sign.setChecked(false);
                    set.clearCheck();
                    return;
                }

                if (funct == null && !div.isChecked()){
                    display.setText("Err: NaN");
                    storage.setText("~");
                }
                else if(funct == null && (div.isChecked()) || mod.isChecked()){
                    display.setText("Err: Divide by Zero");
                    storage.setText("~");
                }

                else{
                    display.setText("");
                    if(funct.toString().length() > 11){
                        storage.setText(funct.toString().substring(0,11));
                    }else{
                        storage.setText(funct.toString());
                    }

                }
                sign.setChecked(false);
                set.clearCheck();
            }
        }
    });
    clear.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(display.getText().toString().equals(""))
                storage.setText("~");
            else
                display.setText("");

            sign.setChecked(false);
            set.clearCheck();
        }
    });

    sign.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String currNum = display.getText().toString();
            if(currNum.equals("")) {
                sign.setChecked(false);
                return;
            }
            else if(Character.isDigit(currNum.charAt(0)) == false){
                if(currNum.charAt(0) == '-'){
                    display.setText(currNum.substring(1));
                    sign.setChecked(false);
                    return;
                }
                else if(currNum.charAt(0) == '.' && currNum.length() == 1){
                    display.setText("-");
                    return;
                }
            }

            display.setText("-" + currNum);
        }
    });

    add.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String newNum = display.getText().toString();
            if(newNum.equals(""))
                return;
            else if(Character.isDigit(newNum.charAt(0)) == false && newNum.charAt(0) != '.' && newNum.charAt(0) != '-'){
                display.setText("");
            }
            else{
                storage.setText(newNum);
                display.setText("");
                sign.setChecked(false);
            }
        }
    });
    sub.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String newNum = display.getText().toString();
            if(newNum.equals(""))
                return;
            else if(Character.isDigit(newNum.charAt(0)) == false && newNum.charAt(0) != '.' && newNum.charAt(0) != '-'){
                display.setText("");
            }
            else{
                storage.setText(newNum);
                display.setText("");
                sign.setChecked(false);
            }
        }
    });
    mult.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String newNum = display.getText().toString();
            if(newNum.equals(""))
                return;
            else if(Character.isDigit(newNum.charAt(0)) == false && newNum.charAt(0) != '.' && newNum.charAt(0) != '-'){
                display.setText("");
            }
            else{
                storage.setText(newNum);
                display.setText("");
                sign.setChecked(false);
            }
        }
    });
    div.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String newNum = display.getText().toString();
            if(newNum.equals(""))
                return;
            else if(Character.isDigit(newNum.charAt(0)) == false && newNum.charAt(0) != '.' && newNum.charAt(0) != '-'){
                display.setText("");
            }
            else{
                storage.setText(newNum);
                display.setText("");
                sign.setChecked(false);
            }
        }
    });
    mod.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String newNum = display.getText().toString();
            if(newNum.equals(""))
                return;
            else if(Character.isDigit(newNum.charAt(0)) == false && newNum.charAt(0) != '.' && newNum.charAt(0) != '-'){
                display.setText("");
            }
            else{
                storage.setText(newNum);
                display.setText("");
                sign.setChecked(false);
            }
        }
    });

    btn0.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String currNum = display.getText().toString();
            if(currNum.length() > 0 && Character.isDigit(currNum.charAt(0)) == false && currNum.charAt(0) != '-' && currNum.charAt(0) != '.')
                display.setText("0");
            else{
                display.setText(currNum + "0");
            }

        }
    });
    btn1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String currNum = display.getText().toString();
            if(currNum.length() > 0 && Character.isDigit(currNum.charAt(0)) == false && currNum.charAt(0) != '-' && currNum.charAt(0) != '.')
                display.setText("1");
            else{
                display.setText(currNum + "1");
            }
        }
    });
    btn2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String currNum = display.getText().toString();
            if(currNum.length() > 0 && Character.isDigit(currNum.charAt(0)) == false && currNum.charAt(0) != '-' && currNum.charAt(0) != '.')
                display.setText("2");
            else{
                display.setText(currNum + "2");
            }
        }
    });
    btn3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String currNum = display.getText().toString();
            if(currNum.length() > 0 && Character.isDigit(currNum.charAt(0)) == false && currNum.charAt(0) != '-' && currNum.charAt(0) != '.')
                display.setText("3");
            else{
                display.setText(currNum + "3");
            }
        }
    });
    btn4.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String currNum = display.getText().toString();
            if(currNum.length() > 0 && Character.isDigit(currNum.charAt(0)) == false && currNum.charAt(0) != '-' && currNum.charAt(0) != '.')
                display.setText("4");
            else{
                display.setText(currNum + "4");
            }
        }
    });
    btn5.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String currNum = display.getText().toString();
            if(currNum.length() > 0 && Character.isDigit(currNum.charAt(0)) == false && currNum.charAt(0) != '-' && currNum.charAt(0) != '.')
                display.setText("5");
            else{
                display.setText(currNum + "5");
            }
        }
    });
    btn6.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String currNum = display.getText().toString();
            if(currNum.length() > 0 && Character.isDigit(currNum.charAt(0)) == false && currNum.charAt(0) != '-' && currNum.charAt(0) != '.')
                display.setText("6");
            else{
                display.setText(currNum + "6");
            }
        }
    });
    btn7.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String currNum = display.getText().toString();
            if(currNum.length() > 0 && Character.isDigit(currNum.charAt(0)) == false && currNum.charAt(0) != '-' && currNum.charAt(0) != '.')
                display.setText("7");
            else{
                display.setText(currNum + "7");
            }
        }
    });
    btn8.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String currNum = display.getText().toString();
            if(currNum.length() > 0 && Character.isDigit(currNum.charAt(0)) == false && currNum.charAt(0) != '-' && currNum.charAt(0) != '.')
                display.setText("8");
            else{
                display.setText(currNum + "8");
            }
        }
    });
    btn9.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String currNum = display.getText().toString();
            if(currNum.length() > 0 && Character.isDigit(currNum.charAt(0)) == false && currNum.charAt(0) != '-' && currNum.charAt(0) != '.')
                display.setText("9");
            else{
                display.setText(currNum + "9");
            }
        }
    });
    btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currNum = display.getText().toString();
                if(currNum.length() == 0){
                    display.setText(".");
                }
                else if(currNum.length() == 1 && currNum.charAt(0) == '-'){
                    sign.setChecked(false);
                    display.setText(".");
                }
                else{
                    display.setText(currNum + ".");
                }


            }
        });








    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}