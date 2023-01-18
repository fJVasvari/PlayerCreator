package com.example.playercreator.Main;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.playercreator.R;
import com.example.playercreator.character.StatPointManager;

public class MainMenu extends AppCompatActivity {

    Button charactersBtn, createCharacterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        charactersBtn = findViewById(R.id.charactersBtn);
        createCharacterBtn = findViewById(R.id.createCharacterBtn);

        charactersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StatPointManager.Characters.class);
                startActivity(intent);
                finish();
            }
        });

        createCharacterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StatPointManager.CreateCharacter.class);
                startActivity(intent);
                finish();
            }
        });
    }
}