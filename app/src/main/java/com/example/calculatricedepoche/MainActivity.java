package com.example.calculatricedepoche;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView screen;
    private int op1 = 0;
    private int op2 = 0;
    private Ops operator = null;
    private boolean isOp1 = true;

    public enum Ops {
        PLUS,
        MOINS,
        FOIS,
        DIV
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen = findViewById(R.id.screen);
    }

    public void addNumber(View v) {
        try {
            int val = Integer.parseInt(((Button) v).getText().toString());
            if (isOp1) {
                op1 = op1 * 10 + val;
                updateDisplay();
            } else {
                op2 = op2 * 10 + val;
                updateDisplay();
            }
        } catch (NumberFormatException | ClassCastException e) {
            Toast.makeText(this, "Valeur erronée", Toast.LENGTH_LONG).show();
        }
    }

    public void setOperator(View v) {
        String tag = v.getTag().toString();
        switch (tag) {
            case "PLUS":
                operator = Ops.PLUS;
                break;
            case "MOINS":
                operator = Ops.MOINS;
                break;
            case "FOIS":
                operator = Ops.FOIS;
                break;
            case "DIV":
                operator = Ops.DIV;
                break;
            default:
                Toast.makeText(this, "Opérateur non reconnu", Toast.LENGTH_LONG).show();
                return;
        }
        isOp1 = false;
        screen.setText("");
    }

    public void compute(View v) {
        if (!isOp1) {
            switch (operator) {
                case PLUS:
                    op1 = op1 + op2;
                    break;
                case MOINS:
                    op1 = op1 - op2;
                    break;
                case FOIS:
                    op1 = op1 * op2;
                    break;
                case DIV:
                    if (op2 != 0) {
                        op1 = op1 / op2;
                    } else {
                        Toast.makeText(this, "Division par zéro", Toast.LENGTH_LONG).show();
                    }
                    break;
                default:
                    Toast.makeText(this, "Opérateur non reconnu", Toast.LENGTH_LONG).show();
                    return;
            }
            op2 = 0;
            operator = null;
            isOp1 = true;
            updateDisplay();
        }
    }

    public void clear(View v) {
        op1 = 0;
        op2 = 0;
        operator = null;
        isOp1 = true;
        updateDisplay();
    }

    private void updateDisplay() {
        String text = isOp1 ? String.valueOf(op1) : String.valueOf(op2);
        screen.setText(text);
    }
}
