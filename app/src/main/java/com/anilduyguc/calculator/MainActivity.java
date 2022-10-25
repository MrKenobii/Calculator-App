package com.anilduyguc.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {
    private EditText displayText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayText = findViewById(R.id.input);
        displayText.setShowSoftInputOnFocus(false);
        displayText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getString(R.string.display).equals(displayText.getText().toString())){
                    displayText.setText("");
                }
            }
        });
    }
    private void updateText(String addString){
        String prevString = displayText.getText().toString();
        int cursorPosition = displayText.getSelectionStart();
        String leftString = prevString.substring(0, cursorPosition);
        String rightString = prevString.substring(cursorPosition);
        if(getString(R.string.display).equals(displayText.getText().toString()))
            displayText.setText(addString);
        else
            displayText.setText(String.format("%s%s%s", leftString, addString, rightString));

        displayText.setSelection(cursorPosition + 1);
    }
    public void zeroBtn(View view){
        this.updateText("0");
    }
    public void oneBtn(View view){
        this.updateText("1");
    }
    public void twoBtn(View view){
        this.updateText("2");
    }
    public void threeBtn(View view){
        this.updateText("3");
    }
    public void fourBtn(View view){
        this.updateText("4");
    }
    public void fiveBtn(View view){
        this.updateText("5");
    }
    public void sixBtn(View view){
        this.updateText("6");
    }
    public void sevenBtn(View view){
        this.updateText("7");
    }
    public void eightBtn(View view){
        this.updateText("8");
    }
    public void nineBtn(View view){
        this.updateText("9");
    }
    public void clearBtn(View view){
        displayText.setText("");
    }
    public void backspaceBtn(View view){
        int cursorPosition = displayText.getSelectionStart();
        int textLength = displayText.getText().length();

        if((cursorPosition != 0) && (textLength != 0)){
            SpannableStringBuilder selection = (SpannableStringBuilder) displayText.getText();
            selection.replace(cursorPosition -1, cursorPosition, "");
            displayText.setText(selection);
            displayText.setSelection(cursorPosition - 1);
        }
    }
    public void multiplyBtn(View view){
        this.updateText("x");
    }
    public void addBtn(View view){
        this.updateText("+");
    }
    public void divideBtn(View view){
        this.updateText("÷");
    }
    public void substractBtn(View view){
        this.updateText("-");
    }
    public void sinBtn(View view){
        String userExp = displayText.getText().toString();
        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("x", "*");

        Expression expression = new Expression(userExp);
        String result = String.valueOf(expression.calculate());
        if(Double.isNaN(Double.parseDouble(result))){
            Toast toast = Toast.makeText(getApplicationContext(), "The result is not valid", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
            displayText.setText("");
            displayText.setSelection(0);
        } else {
            Expression newExpression = new Expression("sin("+result+")");
            String finalResult = String.valueOf(newExpression.calculate());
            if(Double.isNaN(Double.parseDouble(finalResult))){
                Toast toast = Toast.makeText(getApplicationContext(), "The result is not valid", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
                displayText.setText("");
                displayText.setSelection(0);
            } else {
                displayText.setText(finalResult);
                displayText.setSelection(finalResult.length());
            }
        }

    }
    public void cosineBtn(View view){
        String userExp = displayText.getText().toString();
        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("x", "*");

        Expression expression = new Expression(userExp);
        String result = String.valueOf(expression.calculate());
        if(Double.isNaN(Double.parseDouble(result))){
            Toast toast = Toast.makeText(getApplicationContext(), "The result is not valid", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
            displayText.setText("");
            displayText.setSelection(0);
        } else {
            Expression newExpression = new Expression("cos("+result+")");
            String finalResult = String.valueOf(newExpression.calculate());
            if(Double.isNaN(Double.parseDouble(finalResult))){
                Toast toast = Toast.makeText(getApplicationContext(), "The result is not valid", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
                displayText.setText("");
                displayText.setSelection(0);
            } else {
                displayText.setText(finalResult);
                displayText.setSelection(finalResult.length());
            }
        }

    }
    public void equalBtn(View view){
        String userExp = displayText.getText().toString();
        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("x", "*");

        Expression expression = new Expression(userExp);
        String result = String.valueOf(expression.calculate());
        if(Double.isNaN(Double.parseDouble(result))){
            Toast toast = Toast.makeText(getApplicationContext(), "The result is not valid", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
            displayText.setText("");
            displayText.setSelection(0);
        } else {
            displayText.setText(result);
            displayText.setSelection(result.length());
        }
    }
    public void paranthesisBtn(View view){
        int cursorPosition = displayText.getSelectionStart();
        int openPar = 0;
        int closedPar = 0;
        int textLength = displayText.getText().length();
        for(int i=0; i< cursorPosition; i++){
            if(displayText.getText().toString().substring(i, i+1).equals("(")){
                openPar ++;
            }
            if(displayText.getText().toString().substring(i, i+1).equals(")")){
                closedPar ++;
            }
        }
        if(openPar == closedPar || displayText.getText().toString().substring(textLength -1, textLength).equals("(")){
            this.updateText("(");
        }
        else if(closedPar < openPar && !displayText.getText().toString().substring(textLength -1, textLength).equals("(")){
            this.updateText(")");
        }
            displayText.setSelection(cursorPosition + 1);

    }
    public void exponentBtn(View view){
        this.updateText("^");
    }
    public void plusMinusBtn(View view){
        this.updateText("-");
    }
    public void pointBtn(View view){
        this.updateText(".");
    }



}