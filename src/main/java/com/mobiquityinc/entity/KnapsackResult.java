package com.mobiquityinc.entity;

import java.util.Arrays;
import java.util.LinkedList;

public class KnapsackResult implements Cloneable {
    public static final KnapsackResult EMPTY = new KnapsackResult();
    private int currentScaledWeight;
    private long currentCost;
    private LinkedList<Integer> itemsIndex;

    public KnapsackResult() {
        this.itemsIndex = new LinkedList<>();
    }

    public void add(int itemIndex, int scaledWeight, int cost) {
        currentScaledWeight += scaledWeight;
        currentCost += cost;
        itemsIndex.add(itemIndex);
    }

    public int getCurrentScaledWeight() {
        return currentScaledWeight;
    }

    public void setCurrentScaledWeight(int currentScaledWeight) {
        this.currentScaledWeight = currentScaledWeight;
    }

    public long getCurrentCost() {
        return currentCost;
    }

    public void setCurrentCost(long currentCost) {
        this.currentCost = currentCost;
    }

    public LinkedList<Integer> getItemsIndex() {
        return itemsIndex;
    }

    public void setItemsIndex(LinkedList<Integer> itemsIndex) {
        this.itemsIndex = itemsIndex;
    }

    @Override
    public String toString() {
        return String.format("KnapsackResult{currentScaledWeight=%d, currentCost=%d, itemsIndex=%s}", currentScaledWeight, currentCost, itemsIndex);
    }

    public String getSolution() {
        if (itemsIndex.size() == 0) {
            return "-";
        }
        return Arrays.toString(itemsIndex.toArray()).replaceAll("\\[", "").replaceAll("\\]", "");
    }

    @Override
    public Object clone() {
        KnapsackResult clone = new KnapsackResult();
        clone.setCurrentCost(currentCost);
        clone.setCurrentScaledWeight(currentScaledWeight);
        clone.setItemsIndex((LinkedList<Integer>) itemsIndex.clone());
        return clone;
    }
}
