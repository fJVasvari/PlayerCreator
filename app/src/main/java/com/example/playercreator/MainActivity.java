package com.example.playercreator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Spinner playerClassSpinner, playerGenderSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerClassSpinner = findViewById(R.id.classSpinner);
        playerGenderSpinner = findViewById(R.id.genderSpinner);

        String[] pClasses = new String[]{"Barbarian","Mage"};
        final ArrayAdapter<String> pClassAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, pClasses);
        playerClassSpinner.setAdapter(pClassAdapter);

        String[] pGenders = new String[]{"Male","Female"};
        final ArrayAdapter<String> pGenderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, pGenders);
        playerGenderSpinner.setAdapter(pGenderAdapter);
    }
}