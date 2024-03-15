package com.assignment.mathematicsgameapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class orderingNumber extends AppCompatActivity {

    private TextView[] numberTextViews = new TextView[5];
    private Button backButton, nextButton, resultButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_number);

        numberTextViews[0] = findViewById(R.id.place_number1);
        numberTextViews[1] = findViewById(R.id.place_number2);
        numberTextViews[2] = findViewById(R.id.place_number3);
        numberTextViews[3] = findViewById(R.id.place_number4);
        numberTextViews[4] = findViewById(R.id.place_number5);

        backButton = findViewById(R.id.back);
        nextButton = findViewById(R.id.next_question_button);
        resultButton = findViewById(R.id.result_button);

        setRandomNumbers();

        for (TextView numberTextView : numberTextViews) {
            numberTextView.setOnLongClickListener(longClickListener);
        }

        for (TextView numberTextView : numberTextViews) {
            numberTextView.setOnDragListener(dragListener);
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(orderingNumber.this, MainActivity.class);
                startActivity(intent);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRandomNumbers(); // Call the method to randomize numbers again
            }
        });

        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showResultDialog(); // Call method to show result dialog
            }
        });

    }


    // Function to set random numbers to TextViews
    private void setRandomNumbers() {
        Random random = new Random();
        for (TextView numberTextView : numberTextViews) {
            int randomNumber = random.nextInt(50); // Change 100 to your desired range
            numberTextView.setText(String.valueOf(randomNumber));
        }
    }


    View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, shadowBuilder, v, 0);
            return false; // Return false to indicate that the long click event is not consumed
        }
    };

    View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            final View view = (View) event.getLocalState();

            switch (dragEvent) {
                case DragEvent.ACTION_DROP:
                    TextView dropped = (TextView) view;
                    TextView dropTarget = (TextView) v;
                    String tempText = dropTarget.getText().toString();
                    dropTarget.setText(dropped.getText());
                    dropped.setText(tempText);
                    break;
            }
            return true;
        }
    };

    private void showResultDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Result");

        boolean isAscending = checkAscendingOrder();

        String message = isAscending ? "Numbers are in ascending order" : "Numbers are not in ascending order";
        builder.setMessage(message);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss(); // Dismiss the dialog
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private boolean checkAscendingOrder() {
        for (int i = 0; i < numberTextViews.length - 1; i++) {
            int currentNumber = Integer.parseInt(numberTextViews[i].getText().toString());
            int nextNumber = Integer.parseInt(numberTextViews[i + 1].getText().toString());
            if (currentNumber > nextNumber) {
                return false;
            }
        }
        return true;
    }





}



