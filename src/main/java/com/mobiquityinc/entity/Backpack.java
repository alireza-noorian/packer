package com.mobiquityinc.entity;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Backpack {
    private int weightLimit;
    private ArrayList<Integer> indexNumbers;
    private ArrayList<BigDecimal> weights;
    private ArrayList<Integer> costs;

    public Backpack(int weightLimit) {
        this.weightLimit = weightLimit;
        this.indexNumbers = new ArrayList<>();
        this.weights = new ArrayList<>();
        this.costs = new ArrayList<>();
    }

    public void addItem(BackpackItem backpackItem) {
        this.indexNumbers.add(backpackItem.getIndex());
        this.weights.add(backpackItem.getWeight());
        this.costs.add(backpackItem.getCost());
    }

    public int getWeightLimit() {
        return weightLimit;
    }

    public ArrayList<Integer> getIndexNumbers() {
        return indexNumbers;
    }

    public ArrayList<BigDecimal> getWeights() {
        return weights;
    }

    public ArrayList<Integer> getCosts() {
        return costs;
    }
}
