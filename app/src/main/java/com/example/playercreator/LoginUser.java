package com.example.playercreator;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class LoginUser {
    protected void login(User user){
        if (!user.getUsername().equals("") && !user.getPassword().equals("")) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                public void run() {
                    String[] field = new String[2];
                    field[0] = "username";
                    field[1] = "password";
                    String[] data = new String[2];
                    data[0] = user.getUsername();
                    data[1] = user.getPassword();
                    PutData putData = new PutData("http://10.0.2.2/playercreator/login.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Login Succes")) {
                                //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                            } else {
                                //
                            }
                            Log.i("PutData", result);
                        }
                    }
                }
            });
        }
    }
}
