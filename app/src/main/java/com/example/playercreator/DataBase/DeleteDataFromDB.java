package com.example.playercreator.DataBase;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import com.example.playercreator.character.Player;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class DeleteDataFromDB {
    public void deleteCharacterFromDB(Player player, Context context) {
        android.os.Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[1];
                field[0] = "Id";

                String[] data = new String[] {
                        String.valueOf(player.getPlayerID())
                };

                PutData putData = new PutData(
                        "http://10.0.2.2/playercreator/deletePlayer.php",
                        "POST",
                        field,
                        data
                );

                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();

                        Log.i("PutData", result);
                    }
                }
            }
        });
    }
}
