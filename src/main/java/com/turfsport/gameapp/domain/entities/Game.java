package com.turfsport.gameapp.domain.entities;

import java.util.Map;

public abstract class Game {

    private String name;
    private String date;

    protected Game(String name, String date) {

        this.name = name;
        this.date= date;
    }

    public abstract void captureDraw(Integer[] winningDraw);

    public abstract String getDivision(Integer[] entry);

    public abstract Map<Integer, Integer> getWinningDraw();

    public String getName() { return name; }

    public String getDate() { return date; }

}
