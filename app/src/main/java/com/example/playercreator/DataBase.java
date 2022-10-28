package com.example.playercreator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DataBase {
    public void playerUpload(Player player){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/player_creator","root","");
            String query = "INSERT INTO player values(" +
                    "'" + player.getPlayerID() + "'," +
                    "'" + player.getPlayerName() + "'," +
                    "'" + player.getPlayerClass() + "'," +
                    "'" + player.getPlayerGender() + "'," +
                    "'" + player.getPlayerHealth() + "'," +
                    "'" + player.getPlayerDamage() + "'," +
                    "'" + player.getPlayerDefense() + "')";
            Statement sta = connection.createStatement();
            int x = sta.executeUpdate(query);
            connection.close();
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
    public ArrayList<Player> getAllPlayer(){
        ArrayList<Player> playerList = new ArrayList<Player>();
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/player_creator","root","");
            Statement sta = connection.createStatement();
            String query = "Select * FROM player";
            ResultSet rst;
            rst = sta.executeQuery(query);
            while(rst.next()){
                Player player = new Player(rst.getString("Name"), rst.getString("Class"), rst.getString("Gender"));
                player.setPlayerHealth(rst.getInt("Health"));
                player.setPlayerDamage(rst.getInt("Damage"));
                player.setPlayerDefense(rst.getInt("Defense"));
                player.setPlayerID(rst.getInt("ID"));
                playerList.add(player);
            }
            rst.close();
            connection.close();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return playerList;
    }

    public void deletePlayer(int id) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/player_creator", "root", "");
            Statement sta = connection.createStatement();
            String query = "DELETE FROM player WHERE id = " + id;
            sta.executeUpdate(query);
            connection.close();
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public void updatePlayer(Player player){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/player_creator", "root", "");
            Statement sta = connection.createStatement();
            String query = "UPDATE player " +
                    "SET Name = '" + player.getPlayerName() + "'," +
                    " Class = '" + player.getPlayerClass() + "'," +
                    " Gender = '" + player.getPlayerGender() +"'," +
                    " Health = '" + player.getPlayerHealth() + "'," +
                    " Damage = '" + player.getPlayerDamage()+ "'," +
                    " Defense = '" + player.getPlayerDefense() + "' WHERE ID = " + player.getPlayerID();
            sta.executeUpdate(query);
            connection.close();
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
