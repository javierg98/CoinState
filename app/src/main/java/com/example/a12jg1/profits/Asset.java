package com.example.a12jg1.profits;

import android.util.Pair;

import java.util.Date;

/**
 * Created by 12jg1 on 12/1/2017.
 */

public class Asset {
    private String id;
    private String name;
    private String symbol;
    private String imageUrl;
    private int rank;
    private int currentValue;
    private int coinsOwned;
    private int priceBought;
    private String dateBought;
    private String graphUrl;

    public Asset(String id, String name, String symbol, int rank, int currentValue, int coinsOwned, int priceBought, String dateBought){

        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.imageUrl = "";
        this.rank = rank;
        this.currentValue = currentValue;
        this.coinsOwned = coinsOwned;
        this.priceBought = priceBought;
        this.dateBought = dateBought;
        this.graphUrl = "https://files.coinmarketcap.com/generated/sparklines/"+rank+".png";

    }

    public String getId() {return id;}

    public String getName(){return name;}

    public String getSymbol() {return symbol;}

    public String getImageUrl() {return imageUrl;}

    public int getRank() {return rank;}

    public int getCurrentValue() {return currentValue;}

    public int getCoinsOwned() {return coinsOwned;}

    public int getPriceBought() {return priceBought;}

    public String getDateBought() {return dateBought;}

    public String getGraphUrl() {return graphUrl;}



}