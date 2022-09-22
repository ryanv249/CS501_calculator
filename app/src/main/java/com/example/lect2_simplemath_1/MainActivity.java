package com.example.lect2_simplemath_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

//import CalculatorFunctions;

public class MainActivity extends AppCompatActivity {

    private EditText edtOp1;
    private EditText edtOp2;
    private Button btnCalculate;
    private TextView tvAnswer;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  tvAnswer = new TextView(null);

       tvAnswer = (TextView) findViewById(R.id.tvAnswer);
       edtOp1 = (EditText) findViewById(R.id.edtOp1);
       edtOp2 = (EditText) findViewById(R.id.edtOp2);
       btnCalculate = (Button) findViewById(R.id.btnCalculate);
       radioButton = (RadioButton) findViewById(R.id.Add);


btnCalculate.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View view) {

        RadioButton Add = (RadioButton)findViewById(R.id.Add);
        RadioButton mult = (RadioButton)findViewById(R.id.mult);
        RadioButton sub = (RadioButton)findViewById(R.id.sub);
        RadioButton div = (RadioButton)findViewById(R.id.div);
        RadioButton mod = (RadioButton)findViewById(R.id.mod);

        Double val_2 = Double.parseDouble(edtOp2.getText().toString());
        Double val_1 = Double.parseDouble(edtOp1.getText().toString());
        Double funct = 0.0;
        if(Add.isChecked())
            funct = CalculatorFunctions.addition(val_1,val_2);
        else if (sub.isChecked())
            funct = CalculatorFunctions.subtraction(val_1,val_2);
        else if (mult.isChecked())
            funct = CalculatorFunctions.mutliplication(val_1,val_2);
        else if (div.isChecked())
            funct = CalculatorFunctions.division(val_1,val_2);
        else if (mod.isChecked())
            funct = CalculatorFunctions.modula(val_1,val_2);
        tvAnswer.setText(funct.toString());
    }
});

btnCalculate.setOnLongClickListener(new View.OnLongClickListener() {
    @Override
    public boolean onLongClick(View view) {
        Double result;
        result = Double.parseDouble(edtOp1.getText().toString()) -
                Double.parseDouble(edtOp2.getText().toString());
        tvAnswer.setText(result.toString());

        return true;
    }
});

    }

    public void onClick(View view){
        Double result;
        result = Double.parseDouble(edtOp1.getText().toString()) +
                 Double.parseDouble(edtOp2.getText().toString());
        tvAnswer.setText(result.toString());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();


    }
}