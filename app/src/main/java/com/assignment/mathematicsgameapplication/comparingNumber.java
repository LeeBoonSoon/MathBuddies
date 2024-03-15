package com.assignment.mathematicsgameapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class comparingNumber extends AppCompatActivity {

    private TextView num1TextView, num2TextView;
    private ImageView lessThanImageView, equalToImageView, greaterThanImageView;
    private Button submitButton, nextButton, backHome;
    private int number1, number2;
    private int correctCount = 0;
    private int incorrectCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparing_number);

        // Initialize views
        num1TextView = findViewById(R.id.num1TextView);
        num2TextView = findViewById(R.id.num2TextView);
        lessThanImageView = findViewById(R.id.less_than);
        equalToImageView = findViewById(R.id.equal_to);
        greaterThanImageView = findViewById(R.id.greater_than);
        submitButton = findViewById(R.id.submitButton);
        nextButton = findViewById(R.id.next);
        backHome = findViewById(R.id.back_to_home);

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(comparingNumber.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Generate and display random numbers
        generateRandomNumbers();

        // Set click listeners for ImageView
        lessThanImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer("<");
            }
        });

        equalToImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer("=");
            }
        });

        greaterThanImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(">");
            }
        });

        // Set click listener for submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display the result
                displayResult();
            }
        });

        // Set click listener for next button
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                generateRandomNumbers();
            }
        });
    }

    // Method to generate random numbers and update TextViews
    private void generateRandomNumbers() {
        number1 = generateRandomNumber();
        number2 = generateRandomNumber();
        num1TextView.setText(String.valueOf(number1));
        num2TextView.setText(String.valueOf(number2));
    }

    // Method to generate random number
    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(100); // Change the bound as needed
    }

    // Method to check user's answer
    private void checkAnswer(String operator) {
        int result = Integer.compare(number1, number2);

        if (operator.equals("<") && result < 0) {
            correctCount++;
            showToast("Correct!");
        } else if (operator.equals("=") && result == 0) {
            correctCount++;
            showToast("Correct!");
        } else if (operator.equals(">") && result > 0) {
            correctCount++;
            showToast("Correct!");
        } else {
            incorrectCount++;
            showToast("Incorrect!");
        }

    }

    // Method to display the result
    private void displayResult() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Result");

        String message = "Correct: " + correctCount + "\nIncorrect: " + incorrectCount;
        builder.setMessage(message);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Reset counts
                correctCount = 0;
                incorrectCount = 0;
                // Generate next numbers
                generateRandomNumbers();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}
