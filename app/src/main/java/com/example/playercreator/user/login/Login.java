package com.example.playercreator.user.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.playercreator.R;
import com.example.playercreator.user.register.Register;
import com.example.playercreator.user.User;

public class Login extends AppCompatActivity {

    TextView registerText;
    EditText usernameEditText, passwordEditText;
    Button loginButton;
    LoginUser loginUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginUser = new LoginUser();
        registerText = findViewById(R.id.registerTextView);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username, password;
                username = String.valueOf(usernameEditText.getText());
                password = String.valueOf(passwordEditText.getText());
                User user = new User("","",username,password);
                loginUser.login(user,getApplicationContext());
            }
        });
    }
}
