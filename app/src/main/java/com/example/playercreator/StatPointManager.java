package com.example.playercreator;

import android.widget.TextView;

public class StatPointManager {
    public void statPointPlus(int statPoint, int stat, TextView textViewStat, TextView textViewStatPoints){
        if(statPoint>0){
            stat++;
            statPoint--;
            textViewStat.setText(String.valueOf(stat));
            textViewStatPoints.setText(String.valueOf(statPoint));
        }
    }
    public void statPointMinus(int stat, TextView textView){

    }
}
