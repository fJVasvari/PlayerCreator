package com.example.playercreator;

import android.os.AsyncTask;
import android.view.GestureDetector;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.playercreator.character.Player;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Characters extends AppCompatActivity {

    Spinner characterSpinner;

    TextView nameText, classText, genderText,healthText, damageText, defenseText;

    ArrayList<Player> playerList = new ArrayList<>();
    ArrayList<String> playerNameList = new ArrayList<>();
    GetDataFromDB getDataFromDB = new GetDataFromDB(playerList,playerNameList);
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

        getDataFromDB.getJSON("http://10.0.14.100/playercreator/getdata.php");
        getDataFromDB.playerListToNameList();

        Player player = new Player("Default","Default","Default");
        playerList.add(player);

        ArrayAdapter<String> playerNameAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, playerNameList);
        characterSpinner.setAdapter(playerNameAdapter);

        characterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerValue = characterSpinner.getSelectedItem().toString();

                for (int i = 0; i < playerList.size(); i++) {
                    if(spinnerValue.equals(playerList.get(i).getPlayerName())){
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
    }
}