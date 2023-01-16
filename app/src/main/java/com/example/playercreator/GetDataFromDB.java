package com.example.playercreator;

import android.os.AsyncTask;
import com.example.playercreator.character.Player;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GetDataFromDB {

    ArrayList<Player> playerList;
    ArrayList<String> nameList;
    public GetDataFromDB(ArrayList<Player> playerList, ArrayList<String> nameList){
        playerList = this.playerList;
        nameList = this.nameList;
    }

    protected void getJSON(final String urlWebService) {
        class GetJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    createPlayers(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }
    private void createPlayers(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            Player player = new Player(obj.getString("name"),obj.getString("class"),obj.getString("gender"));
            player.setPlayerHealth(obj.getInt("health"));
            player.setPlayerDamage(obj.getInt("damage"));
            player.setPlayerDefense(obj.getInt("defense"));
            playerList.add(player);
        }
        playerListToNameList();
    }
    protected void playerListToNameList(){
        nameList.clear();
        for (int i = 0; i < playerList.size(); i++) {
            nameList.add(playerList.get(i).getPlayerName());
        }
    }
}

