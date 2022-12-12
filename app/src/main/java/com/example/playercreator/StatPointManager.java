package com.example.playercreator;

import android.widget.TextView;

public class StatPointManager {

    private TextView textViewStatsPoints;
    private int statPoints;

    public StatPointManager(TextView textViewStat, int statPoints) {
        this.textViewStatsPoints = textViewStat;
        this.statPoints = statPoints;
    }

    public void setStatPoints(int statPoints) {
        this.statPoints = statPoints;
    }

    public int statPointPlus(int stat, TextView textViewStatPoint){
        if(this.statPoints > 0){
            stat++;
            this.statPoints--;
            textViewStatPoint.setText(String.valueOf(stat));
            textViewStatsPoints.setText(String.valueOf(this.statPoints));
        }

        return stat;
    }
    public int statPointMinus(int stat, TextView textViewStatPoint){
        if(stat > 1){
            stat--;
            this.statPoints++;
            textViewStatPoint.setText(String.valueOf(stat));
            textViewStatsPoints.setText(String.valueOf(this.statPoints));
        }
        return stat;
    }
}
