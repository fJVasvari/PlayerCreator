package com.example.playercreator;

import androidx.appcompat.app.AppCompatActivity;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner playersSpinner, playerClassSpinner, playerGenderSpinner;
    Button createBtn, updateBtn, deleteBtn, refreshBtn;
    TextView textviewHealth,textviewDamage,textviewDefense, textviewStatPoints;

    EditText nameText;

    ArrayList<Player> playerList = new ArrayList<>();
    ArrayList<String> playerNameList = new ArrayList<>();

    int statPoint = 10;
    int playerHealth = 1;
    int playerDamage = 1;
    int playerDefense = 1;

    Button healthPlusBtn, healthMinusBtn, damagePlusBtn, damageMinusBtn, defensePlusBtn, defenseMinusBtn;

    CreatePlayer createPlayer = new CreatePlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        StatPointManager statPointManager = new StatPointManager(textviewStatPoints,statPoint);
        Player player = new Player("Default","Default","Default");
        playerList.add(player);
        playerListToNameList();
        String[] pClasses = new String[]{"Barbarian","Mage"};
        final ArrayAdapter<String> pClassAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, pClasses);
        playerClassSpinner.setAdapter(pClassAdapter);

        String[] pGenders = new String[]{"Male","Female"};
        final ArrayAdapter<String> pGenderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, pGenders);
        playerGenderSpinner.setAdapter(pGenderAdapter);

        ArrayAdapter<String> playerNameAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, playerNameList);
        playersSpinner.setAdapter(playerNameAdapter);

        //IDEIGLENES

        textviewHealth.setText(String.valueOf(playerHealth));
        textviewDamage.setText(String.valueOf(playerDamage));
        textviewDefense.setText(String.valueOf(playerDefense));
        textviewStatPoints.setText(String.valueOf(statPoint));
        getJSON("http://10.0.14.100/playercreator/getdata.php");

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player player = new Player(nameText.getText().toString(),playerClassSpinner.getSelectedItem().toString(),playerGenderSpinner.getSelectedItem().toString());
                player.setPlayerHealth(playerHealth);
                player.setPlayerDamage(playerDamage);
                player.setPlayerDefense(playerDefense);
                playerList.add(player);
                playerListToNameList();
                playersSpinner.setAdapter(playerNameAdapter);
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

        playersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*String spinnerValue = playersSpinner.getSelectedItem().toString();
                if (spinnerValue.equals("Name")) {
                    for (int i = 0; i < playerList.size(); i++) {
                        if (playerList.get(i).getPlayerName().equals(spinnerValue)) {
                            textviewHealth.setText(playerList.get(i).getPlayerHealth());
                            textviewDamage.setText(playerList.get(i).getPlayerHealth());
                            textviewDefense.setText(playerList.get(i).getPlayerHealth());
                            textviewStatPoints.setText(playerList.get(i).getPlayerHealth());
                        }
                    }
                } else if (spinnerValue.equals("Name1")) {
                    for (int i = 0; i < playerList.size(); i++) {
                        if (playerList.get(i).getPlayerName().equals(spinnerValue)) {
                            textviewHealth.setText(playerList.get(i).getPlayerHealth());
                            textviewDamage.setText(playerList.get(i).getPlayerHealth());
                            textviewDefense.setText(playerList.get(i).getPlayerHealth());
                            textviewStatPoints.setText(playerList.get(i).getPlayerHealth());
                        }
                    }
                } else {
                    for (int i = 0; i < playerList.size(); i++) {
                        if (playerList.get(i).getPlayerName().equals(spinnerValue)) {
                            textviewHealth.setText("0");
                            textviewDamage.setText("0");
                            textviewDefense.setText("0");
                            textviewStatPoints.setText("0");
                        }
                    }
                }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void playerListToNameList(){
        playerNameList.clear();
        for (int i = 0; i < playerList.size(); i++) {
            playerNameList.add(playerList.get(i).getPlayerName());
        }
    }
    //this method is actually fetching the json string
    private void getJSON(final String urlWebService) {
        /*
         * As fetching the json string is a network operation
         * And we cannot perform a network operation in main thread
         * so we need an AsyncTask
         * The constrains defined here are
         * Void -> We are not passing anything
         * Void -> Nothing at progress update as well
         * String -> After completion it should return a string and it will be the json string
         * */
        class GetJSON extends AsyncTask<Void, Void, String> {

            //this method will be called before execution
            //you can display a progress bar or something
            //so that user can understand that he should wait
            //as network operation may take some time
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            //this method will be called after execution
            //so here we are displaying a toast with the json string
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }

            //in this method we are fetching the json string
            @Override
            protected String doInBackground(Void... voids) {



                try {
                    //creating a URL
                    URL url = new URL(urlWebService);

                    //Opening the URL using HttpURLConnection
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    //StringBuilder object to read the string from the service
                    StringBuilder sb = new StringBuilder();

                    //We will use a buffered reader to read the string from service
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    //A simple string to read values from each line
                    String json;

                    //reading until we don't find null
                    while ((json = bufferedReader.readLine()) != null) {

                        //appending it to string builder
                        sb.append(json + "\n");
                    }

                    //finally returning the read string
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }

            }
        }

        //creating asynctask object and executing it
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

}