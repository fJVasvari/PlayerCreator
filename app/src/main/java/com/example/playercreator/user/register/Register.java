package com.example.playercreator.user.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.playercreator.R;
import com.example.playercreator.user.User;
import com.example.playercreator.user.login.Login;

public class Register extends AppCompatActivity {

    TextView loginText;
    EditText fullnameEditText, emailEditText, usernameEditText, passwordEditText;
    Button registerButton;
    RegisterUser registerUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerUser = new RegisterUser();
        loginText = findViewById(R.id.loginTextView);

        fullnameEditText = findViewById(R.id.fullnameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(String.valueOf(fullnameEditText.getText())
                        ,String.valueOf(emailEditText.getText())
                        ,String.valueOf(usernameEditText.getText())
                        ,String.valueOf(passwordEditText.getText()));
                registerUser.putDataToDB(user,getApplicationContext());
            }
        });

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}