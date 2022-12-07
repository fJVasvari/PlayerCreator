package com.example.playercreator;

import androidx.appcompat.app.AppCompatActivity;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner playersSpinner, playerClassSpinner, playerGenderSpinner;
    Button createBtn, updateBtn, deleteBtn, refreshBtn;
    TextView textviewHealth,textviewDamage,textviewDefense, textviewStatPoints;

    EditText nameText;

    DataBase dataBase = new DataBase();
    ArrayList<Player> playerList;

    int statPoint = 10;
    int playerHealth = 1;
    int playerDamage = 1;
    int playerDefense = 1;

    Button healthPlusBtn, healthMinusBtn, damagePlusBtn, damageMinusBtn, defensePlusBtn, defenseMinusBtn;

    StatPointManager statPointManager = new StatPointManager();

    CreatePlayer createPlayer = new CreatePlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPlayersFromDB();

        playersSpinner = findViewById(R.id.playerSpinner);
        playerClassSpinner = findViewById(R.id.classSpinner);
        playerGenderSpinner = findViewById(R.id.genderSpinner);

        nameText = findViewById(R.id.nameEditText);

        createBtn = findViewById(R.id.create_btn);
        updateBtn = findViewById(R.id.update_btn);
        deleteBtn = findViewById(R.id.delete_btn);
        refreshBtn = findViewById(R.id.refresh_btn);

        textviewHealth = findViewById(R.id.textviewHealth);
        textviewDamage = findViewById(R.id.textviewDamage);
        textviewDefense = findViewById(R.id.textviewDefense);
        textviewStatPoints = findViewById(R.id.textviewStatPoint);

        healthPlusBtn = findViewById(R.id.healthPlus);
        healthMinusBtn = findViewById(R.id.healthMinus);
        damagePlusBtn = findViewById(R.id.damagePlus);
        damageMinusBtn = findViewById(R.id.damageMinus);
        defensePlusBtn = findViewById(R.id.defensePlus);
        defenseMinusBtn = findViewById(R.id.defenseMinus);

        String[] pClasses = new String[]{"Barbarian","Mage"};
        final ArrayAdapter<String> pClassAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, pClasses);
        playerClassSpinner.setAdapter(pClassAdapter);

        String[] pGenders = new String[]{"Male","Female"};
        final ArrayAdapter<String> pGenderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, pGenders);
        playerGenderSpinner.setAdapter(pGenderAdapter);

        String[] pNames = new String[]{"pista"};
        final ArrayAdapter<String> playerNameAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, pNames);
        playersSpinner.setAdapter(playerNameAdapter);

        //IDEIGLENES

        textviewHealth.setText(String.valueOf(playerHealth));
        textviewDamage.setText(String.valueOf(playerDamage));
        textviewDefense.setText(String.valueOf(playerDefense));
        textviewStatPoints.setText(String.valueOf(statPoint));

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player player = new Player(nameText.getText().toString(),playerClassSpinner.getSelectedItem().toString(),playerGenderSpinner.getSelectedItem().toString());
                player.setPlayerHealth(playerHealth);
                player.setPlayerDamage(playerDamage);
                player.setPlayerDefense(playerDefense);
                createPlayer.putPlayerToDB(player,getApplicationContext());
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        healthPlusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(statPoint>0){
                    playerHealth++;
                    statPoint--;
                    textviewHealth.setText(String.valueOf(playerHealth));
                    textviewStatPoints.setText(String.valueOf(statPoint));
                }
            }
        });

        healthMinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(playerHealth >1){
                    playerHealth--;
                    statPoint++;
                    textviewHealth.setText(String.valueOf(playerHealth));
                    textviewStatPoints.setText(String.valueOf(statPoint));
                }
            }
        });

        damagePlusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //statPointManager.statPointPlus(statPoint,playerDamage,textviewDamage,textviewStatPoints);
            }
        });

        damageMinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        defensePlusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        defenseMinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
    private void getPlayersFromDB(){
        playerList = dataBase.getAllPlayer();
    }
}