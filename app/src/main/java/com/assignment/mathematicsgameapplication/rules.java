package com.assignment.mathematicsgameapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class rules extends AppCompatActivity {

    Button rule_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        rule_exit = findViewById(R.id.rules_exit);

        rule_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(rules.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}