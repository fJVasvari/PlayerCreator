package com.example.playercreator;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class RegisterUser {

    protected void putDataToDB(User user){
        if(!user.getFullname().equals("") &&!user.getEmail().equals("") &&!user.getUsername().equals("") &&!user.getPassword().equals("")){
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    String[] field = new String[4];
                    field[0] = "fullname";
                    field[1] = "email";
                    field[2] = "username";
                    field[3] = "password";
                    //
                    String[] data = new String[4];
                    data[0] = user.getFullname();
                    data[1] = user.getEmail();
                    data[2] = user.getUsername();
                    data[3] = user.getPassword();
                    //ITT ÁT KELL ÍRNI AZ IPCÍMET
                    PutData putData = new PutData("http://10.0.2.2/playercreator/signUp.php","POST",field,data);
                    if(putData.startPut()){
                        if(putData.onComplete()){
                            String result = putData.getResult();
                            if(result.equals("Sign Up Succes")){
                                //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                            }else{
                                //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                            }
                            Log.i("PutData",result);
                        }
                    }
                }
            });
        }
    }

}
