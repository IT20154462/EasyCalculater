package com.example.easycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTV,solutionTV;
    MaterialButton buttonC,buttonBrackOpen,buttonBrackClose;
    MaterialButton buttonDivide,buttonMultiply,buttonPlus,buttonMinus,buttonEquals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTV = findViewById(R.id.result_tv);
        solutionTV = findViewById(R.id.solution_tv);

        assingId(buttonC,R.id.button_c);
        assingId(buttonBrackOpen,R.id.button_open_bracket);
        assingId(buttonBrackClose,R.id.button_close_bracket);
        assingId(buttonDivide,R.id.button_divide);
        assingId(buttonMultiply,R.id.button_multiply);
        assingId(buttonPlus,R.id.button_plus);
        assingId(buttonMinus,R.id.button_minus);
        assingId(buttonEquals,R.id.button_equals);
        assingId(button0,R.id.button_0);
        assingId(button1,R.id.button_1);
        assingId(button2,R.id.button_2);
        assingId(button3,R.id.button_3);
        assingId(button4,R.id.button_4);
        assingId(button5,R.id.button_5);
        assingId(button6,R.id.button_6);
        assingId(button7,R.id.button_7);
        assingId(button8,R.id.button_8);
        assingId(buttonAC,R.id.button_ac);
        assingId(buttonDot,R.id.button_dot);



    }

    void assingId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        MaterialButton button =(MaterialButton) view;
                String buttonText = button.getText().toString();
                String dataToCalculate = solutionTV.getText().toString();

                if(buttonText.equals("Ac")){
                    solutionTV.setText("");
                    resultTV.setText("0");
                    return;
                }
                if(buttonText.equals("=")){
                    solutionTV.setText(resultTV.getText());
                }
                if(buttonText.equals("C")){
                    dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
                }else{
                    dataToCalculate = dataToCalculate+buttonText;
                }

                solutionTV.setText(dataToCalculate);

                String finalResult = getResult(dataToCalculate);

                if(!finalResult.equals("Err")){
                    resultTV.setText(finalResult);
                }
    }
    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult=context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if (finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return  finalResult;
        }catch (Exception e){
            return "Err";
        }


    }
}