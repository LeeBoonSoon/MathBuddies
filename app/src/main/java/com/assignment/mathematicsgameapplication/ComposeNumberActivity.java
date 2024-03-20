package com.assignment.mathematicsgameapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ComposeNumberActivity extends AppCompatActivity {

    private TextView random1, random2, randomSymbol, textViewNumber1, textViewNumber2;
    private EditText typeSymbol;
    private Button submitButton, nextButton, backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_number);

        random1 = findViewById(R.id.random1);
        random2 = findViewById(R.id.random2);
        randomSymbol = findViewById(R.id.random_symbol);
        textViewNumber1 = findViewById(R.id.textViewNumber1);
        textViewNumber2 = findViewById(R.id.textViewNumber2);
        typeSymbol = findViewById(R.id.type_symbol);
        submitButton = findViewById(R.id.submitButton);
        nextButton = findViewById(R.id.nextButton);
        backButton = findViewById(R.id.backButton);

        generateNumbersAndSymbol();

        // Set onClickListener for the submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateNumbersAndSymbol();
                typeSymbol.setText("");
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ComposeNumberActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void generateNumbersAndSymbol() {
        Random random = new Random();

        // Generate random numbers for the first row
        int num1 = random.nextInt(5);
        int num2 = random.nextInt(5);

        // Set random numbers to text views for the first row
        random1.setText(String.valueOf(num1));
        random2.setText(String.valueOf(num2));

        // Generate random symbol for the first row (+, -, ×, ÷)
        String[] symbols = {"+", "-", "*", "/"};
        String symbol = symbols[random.nextInt(symbols.length)];
        randomSymbol.setText(symbol);

        // Calculate the result of the first row operation
        int result = 0;
        switch (symbol) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                // Make sure num2 is not zero to avoid division by zero
                if (num2 != 0) {
                    result = num1 / num2;
                }
                break;
        }

        // Generate random numbers for the second row to match the result of the first row
        int num3 = random.nextInt(5);
        int num4 = result - num3; // Ensure that num3 + num4 = result
        if (num4 < 0) {
            num3 = result;
            num4 = 0;
        } else if (num4 > 5) {
            num3 = 0;
            num4 = result;
        }

    // Set random numbers to text views for the second row
        textViewNumber1.setText(String.valueOf(num3));
        textViewNumber2.setText(String.valueOf(num4));
    }

    private void checkAnswer() {
        // Get user input
        String userAnswerStr = typeSymbol.getText().toString().trim();

        // Check if user input is a valid symbol
        if (!isValidSymbol(userAnswerStr)) {
            showAlert("Invalid input!");
            return;
        }

        // Get the correct operation symbol for the current question
        String correctSymbol = getCorrectSymbol();

        // Check user's answer
        if (userAnswerStr.equals(correctSymbol)) {
            showAlert("Correct!");
        } else {
            showAlert("Incorrect!");
        }
    }

    private String getCorrectSymbol() {
        int num1 = Integer.parseInt(random1.getText().toString());
        int num2 = Integer.parseInt(random2.getText().toString());
        String symbol = randomSymbol.getText().toString();
        int result;

        switch (symbol) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                // Make sure num2 is not zero to avoid division by zero
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    result = 0; // Division by zero, return 0 as placeholder
                }
                break;
            default:
                result = 0; // Return 0 if symbol is invalid
                break;
        }

        // Determine the correct symbol based on the arithmetic operation result
        if (result == Integer.parseInt(textViewNumber1.getText().toString()) +
                Integer.parseInt(textViewNumber2.getText().toString())) {
            return "+";
        } else if (result == Integer.parseInt(textViewNumber1.getText().toString()) -
                Integer.parseInt(textViewNumber2.getText().toString())) {
            return "-";
        } else if (result == Integer.parseInt(textViewNumber1.getText().toString()) *
                Integer.parseInt(textViewNumber2.getText().toString())) {
            return "*";
        } else if (result == Integer.parseInt(textViewNumber1.getText().toString()) /
                Integer.parseInt(textViewNumber2.getText().toString())) {
            return "/";
        } else {
            return ""; // Invalid operation or incorrect result
        }
    }

    private boolean isValidSymbol(String input) {
        return input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/");
    }

    private void showAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Close the dialog
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}