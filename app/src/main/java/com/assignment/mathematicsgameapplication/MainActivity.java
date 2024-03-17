package com.assignment.mathematicsgameapplication;
// https://github.com/LeeBoonSoon/MathBuddies

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    CardView card_view1, card_view2, card_view3, card_view4, card_view5, card_view6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card_view1 = findViewById(R.id.card_view1);
        card_view2 = findViewById(R.id.card_view2);
        card_view3 = findViewById(R.id.card_view3);
        card_view4 = findViewById(R.id.card_view4);
        card_view5 = findViewById(R.id.card_view5);
        card_view6 = findViewById(R.id.card_view6);

        card_view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,comparingNumber.class);
                startActivity(intent);
            }
        });

        card_view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showSelectionAsceOrDesc();
            }
        });

        card_view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ComposeNumberActivity.class);
                startActivity(intent);
            }
        });

        card_view4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });

        card_view5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,rules.class);
                startActivity(intent);
            }
        });

        card_view6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, customerSupport.class);
                startActivity(intent);
            }
        });
    }

    private void showSelectionAsceOrDesc(){
        ConstraintLayout selectionAsceOrDesc = findViewById(R.id.selection_orderinggame);
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.selection_ordering_game, selectionAsceOrDesc);
        Button asceButton = view.findViewById(R.id.select_accOrder);
        Button descButton = view.findViewById(R.id.select_descOrder);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();

        asceButton.findViewById(R.id.select_accOrder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                Intent intent = new Intent(MainActivity.this, orderingNumber.class);
                startActivity(intent);
            }
        });

        descButton.findViewById(R.id.select_descOrder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                Intent intent = new Intent(MainActivity.this, orderNumberDesc.class);
                startActivity(intent);
            }
        });

        if(alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();

    }
}