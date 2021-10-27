package com.ff14mbrest;

import java.util.*;

public class Recipe {
    private List<Integer> amountIngredients = new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0));
    private List<Integer> ingredientIDS = new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0));
    private List<String> ingredientNames = new ArrayList<>(Arrays.asList("x","x","x","x","x","x","x","x","x"));
    private Integer resultID, resultAmount;
    private String resultName;

    public Recipe(Integer resultID, Integer resultAmount, String resultName){
        this.resultID = resultID;
        this.resultAmount = resultAmount;
        this.resultName = resultName;
    }

    public void addIngredient(int ingNum, Integer id, Integer amount, String name){
        ingredientIDS.set(ingNum,id);
        amountIngredients.set(ingNum, amount);
        ingredientNames.set(ingNum, name);
    }

    public List<Integer> getAmountIngredients() {
        return amountIngredients;
    }

    public List<Integer> getIngredientIDS() {
        return ingredientIDS;
    }

    public List<String> getIngredientNames() {
        return ingredientNames;
    }

    public Integer getResultID() {
        return resultID;
    }

    public Integer getResultAmount() {
        return resultAmount;
    }

    public String getResultName() {
        return resultName;
    }
}
