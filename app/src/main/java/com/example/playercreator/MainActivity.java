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
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner playersSpinner, playerClassSpinner, playerGenderSpinner;
    Button createBtn, updateBtn, deleteBtn, refreshBtn;

    EditText nameText;

    DataBase dataBase = new DataBase();
    ArrayList<Player> playerList;
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

        String[] pClasses = new String[]{"Barbarian","Mage"};
        final ArrayAdapter<String> pClassAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, pClasses);
        playerClassSpinner.setAdapter(pClassAdapter);

        String[] pGenders = new String[]{"Male","Female"};
        final ArrayAdapter<String> pGenderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, pGenders);
        playerGenderSpinner.setAdapter(pGenderAdapter);

        String[] pNames = new String[]{"pista"};
        final ArrayAdapter<String> playerNameAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, pNames);
        playersSpinner.setAdapter(playerNameAdapter);

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Player player = new Player(nameText.getText().toString(),playerClassSpinner.getSelectedItem().toString(),playerGenderSpinner.getSelectedItem().toString());
                dataBase.playerUpload(player);
                Toast.makeText(getApplicationContext(),"szöveg",Toast.LENGTH_SHORT).show();*/
                String playerName = nameText.getText().toString();
                String playerClass = playerClassSpinner.getSelectedItem().toString();
                String playerGender = playerGenderSpinner.getSelectedItem().toString();

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String[] field = new String[6];
                        field[0] = "Name";
                        field[1] = "Class";
                        field[2] = "Gender";
                        field[3] = "Health";
                        field[4] = "Damage";
                        field[5] = "Defense";
                        //
                        String[] data = new String[6];
                        data[0] = playerName;
                        data[1] = playerClass;
                        data[2] = playerGender;
                        data[3] = "0";
                        data[4] = "0";
                        data[5] = "0";
                        //ITT ÁT KELL ÍRNI AZ IPCÍMET
                        PutData putData = new PutData("http://10.0.2.2/playercreator/createPlayer.php","POST",field,data);
                        if(putData.startPut()){
                            if(putData.onComplete()){
                                String result = putData.getResult();
                                if(result.equals("Player created")){
                                    Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                }
                                Log.i("PutData",result);
                            }
                        }
                    }
                });
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
    }
    private void getPlayersFromDB(){
        playerList = dataBase.getAllPlayer();
    }
}