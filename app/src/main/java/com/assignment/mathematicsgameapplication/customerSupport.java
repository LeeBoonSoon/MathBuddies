package com.assignment.mathematicsgameapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class customerSupport extends AppCompatActivity {

    EditText emailEditText, titleEditText, contentEditText;
    Button sendButton, backhome;

    private static final String DEFAULT_EMAIL = "leeboonsoon@1utar.my";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_support);

        emailEditText = findViewById(R.id.center_email);
        titleEditText = findViewById(R.id.Title);
        contentEditText = findViewById(R.id.content_edit_text);
        sendButton = findViewById(R.id.send_email_button);
        backhome = findViewById(R.id.back_home123);

        emailEditText.setText(DEFAULT_EMAIL);

        backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(customerSupport.this, MainActivity.class);
                startActivity(intent);
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title, content, email;
                title = titleEditText.getText().toString();
                content = contentEditText.getText().toString();
                email = emailEditText.getText().toString();
                if(title.equals("") && content.equals("") && email.equals("")){
                    Toast.makeText(customerSupport.this,"ALl field is required!!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    sendEmail(title,content,email);
                }
            }
        });

    }

    private void sendEmail(String title, String content, String email){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        intent.putExtra(Intent.EXTRA_SUBJECT, title);
        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"Choose email to Client"));
    }

}