package com.example.playercreator;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class CreatePlayer {
    protected void putPlayerToDB(Player player, Context context){
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
                data[0] = player.getPlayerName();
                data[1] = player.getPlayerClass();
                data[2] = player.getPlayerGender();
                data[3] = String.valueOf(player.getPlayerHealth());
                data[4] = String.valueOf(player.getPlayerDamage());
                data[5] = String.valueOf(player.getPlayerDefense());
                //ITT ÁT KELL ÍRNI AZ IPCÍMET
                PutData putData = new PutData("http://10.0.2.2/playercreator/createPlayer.php","POST",field,data);
                if(putData.startPut()){
                    if(putData.onComplete()){
                        String result = putData.getResult();
                        if(result.equals("Player created")){
                            Toast.makeText(context,result,Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context,result,Toast.LENGTH_SHORT).show();
                        }
                        Log.i("PutData",result);
                    }
                }
            }
        });
    }
}
