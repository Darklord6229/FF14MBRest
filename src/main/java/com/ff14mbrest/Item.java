package com.ff14mbrest;

import java.util.HashMap;
import java.util.Map;

public class Item {
    private int id;
    private int recID;
    private String name;
    private Map<Integer, String> altRecipes = new HashMap<>();

    //Constructors
    public Item(String name){
        this.name = name;
    }

    //adding alt recipes to the map for the item
    public void addAltRec(String name, int recID){
        altRecipes.put(recID,name);
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRecID() {
        return recID;
    }

    public void setRecID(int recID) {
        this.recID = recID;
    }

    public Map getAltRec(){
        return altRecipes;
    }
}
