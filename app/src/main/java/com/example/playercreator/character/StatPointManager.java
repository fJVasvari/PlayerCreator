package com.example.playercreator.character;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.playercreator.DataBase.DeleteDataFromDB;
import com.example.playercreator.DataBase.PutDataToDB;
import com.example.playercreator.DataBase.GetDataFromDB;
import com.example.playercreator.Main.MainMenu;
import com.example.playercreator.R;

import java.util.ArrayList;

public class StatPointManager {

    private TextView textViewStatsPoints;
    private int statPoints;

    public StatPointManager(TextView textViewStat, int statPoints) {
        this.textViewStatsPoints = textViewStat;
        this.statPoints = statPoints;
    }

    public void setStatPoints(int statPoints) {
        this.statPoints = statPoints;
    }

    public int statPointPlus(int stat, TextView textViewStatPoint){
        if(this.statPoints > 0){
            stat++;
            this.statPoints--;
            textViewStatPoint.setText(String.valueOf(stat));
            textViewStatsPoints.setText(String.valueOf(this.statPoints));
        }

        return stat;
    }
    public int statPointMinus(int stat, TextView textViewStatPoint){
        if(stat > 1){
            stat--;
            this.statPoints++;
            textViewStatPoint.setText(String.valueOf(stat));
            textViewStatsPoints.setText(String.valueOf(this.statPoints));
        }
        return stat;
    }

    public static class Characters extends AppCompatActivity {

        Spinner characterSpinner;

        TextView nameText, classText, genderText,healthText, damageText, defenseText;

        ArrayList<Player> playerList = new ArrayList<>();
        ArrayList<String> playerNameList = new ArrayList<>();
        GetDataFromDB getDataFromDB = new GetDataFromDB(playerList,playerNameList);
        DeleteDataFromDB deleteDataFromDB = new DeleteDataFromDB();
        Button backBtn, deleteBtn;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_characters);

            characterSpinner = findViewById(R.id.characterSpinner);
            nameText = findViewById(R.id.nameText);
            classText = findViewById(R.id.classText);
            genderText = findViewById(R.id.genderText);
            healthText = findViewById(R.id.healthText);
            damageText = findViewById(R.id.damageText);
            defenseText = findViewById(R.id.defenseText);
            backBtn = findViewById(R.id.backToMainBtn);
            deleteBtn = findViewById(R.id.delete_btn);

            Player player = new Player("Default","Default","Default");
            playerList.add(player);
            //getDataFromDB.getJSON("http://10.0.14.100/playercreator/getdata.php");
            //getDataFromDB.playerListToNameList();

            ArrayAdapter<String> playerNameAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, playerNameList);
            characterSpinner.setAdapter(playerNameAdapter);

            characterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String spinnerValue = characterSpinner.getSelectedItem().toString();

                    for (int i = 0; i < playerList.size(); i++) {
                        if (spinnerValue.equals(playerList.get(i).getPlayerName())) {
                            //Kiiratás
                            nameText.setText(String.valueOf(playerList.get(i).getPlayerName()));
                            classText.setText(String.valueOf(playerList.get(i).getPlayerClass()));
                            genderText.setText(String.valueOf(playerList.get(i).getPlayerGender()));
                            healthText.setText(String.valueOf(playerList.get(i).getPlayerHealth()));
                            damageText.setText(String.valueOf(playerList.get(i).getPlayerDamage()));
                            defenseText.setText(String.valueOf(playerList.get(i).getPlayerDefense()));
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                    startActivity(intent);
                    finish();
                }
            });
            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Player foundPlayer = playerList.get(characterSpinner.getSelectedItemPosition());
                    Log.i("ID", String.valueOf(foundPlayer.getPlayerID()));
                    deleteDataFromDB.deleteCharacterFromDB(foundPlayer, getApplicationContext());
                    /*playerList.clear();
                    getDataFromDB.getJSON("http://10.0.2.2/playercreator/getPlayers.php");
                    getDataFromDB.playerListToNameList();*/
                }
            });
        }
    }

    public static class CreateCharacter extends AppCompatActivity {

        Spinner playerClassSpinner, playerGenderSpinner;
        Button createBtn, backBtn;
        EditText nameText;
        TextView textviewHealth,textviewDamage,textviewDefense, textviewStatPoints;
        int statPoint = 10;
        int playerHealth = 1;
        int playerDamage = 1;
        int playerDefense = 1;
        Button healthPlusBtn, healthMinusBtn, damagePlusBtn, damageMinusBtn, defensePlusBtn, defenseMinusBtn;

        PutDataToDB putDataToDB = new PutDataToDB();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_create_character);

            playerClassSpinner = findViewById(R.id.classSpinner);
            playerGenderSpinner = findViewById(R.id.genderSpinner);

            createBtn = findViewById(R.id.create_btn);
            backBtn = findViewById(R.id.backToMainBtn);

            nameText = findViewById(R.id.nameEditText);

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

            StatPointManager statPointManager = new StatPointManager(textviewStatPoints,statPoint);

            String[] pClasses = new String[]{"Barbarian","Mage"};
            final ArrayAdapter<String> pClassAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, pClasses);
            playerClassSpinner.setAdapter(pClassAdapter);

            String[] pGenders = new String[]{"Male","Female"};
            final ArrayAdapter<String> pGenderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, pGenders);
            playerGenderSpinner.setAdapter(pGenderAdapter);

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
                    putDataToDB.putCharacterToDB(player,getApplicationContext());
                    Intent intent = new Intent(getApplicationContext(), Characters.class);
                    startActivity(intent);
                    finish();
                }
            });

            backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                    startActivity(intent);
                    finish();
                }
            });
            healthPlusBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playerHealth = statPointManager.statPointPlus(playerHealth,textviewHealth);
                }
            });

            healthMinusBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playerHealth = statPointManager.statPointMinus(playerHealth,textviewHealth);
                }
            });

            damagePlusBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playerDamage = statPointManager.statPointPlus(playerDamage,textviewDamage);
                }
            });

            damageMinusBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playerDamage = statPointManager.statPointMinus(playerDamage,textviewDamage);
                }
            });

            defensePlusBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playerDefense = statPointManager.statPointPlus(playerDefense,textviewDefense);
                }
            });

            defenseMinusBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playerDefense = statPointManager.statPointMinus(playerDefense,textviewDefense);
                }
            });
        }
    }
}
