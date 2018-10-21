package com.mobiquityinc.service;

import com.mobiquityinc.entity.Backpack;
import com.mobiquityinc.entity.KnapsackResult;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.OptionalInt;

/**
 * This class implemented 0/1 Knapsack using dynamic programming which resulted in finding the maximize fitting items cost.
 * If the cost of items is equal, then this class will find a solution with a lower weight.
 */
public class MaxCostMinWeightStrategy implements KnapsackStrategy {
    private final KnapsackResult[][] internalKnapsackResults;
    private final Backpack backpack;
    private final int scaleFactor;
    private final int scaledWeightLimit;

    public MaxCostMinWeightStrategy(Backpack backpack) {
        Objects.requireNonNull(backpack, "Backpack is null");
        this.backpack = backpack;
        this.scaleFactor = getScaleFactor();
        this.scaledWeightLimit = getScaledWeightLimit();
        this.internalKnapsackResults = initKnapsackResults();
    }

    private int getScaledWeightLimit() {
        return backpack.getWeightLimit() * this.scaleFactor;
    }

    private KnapsackResult[][] initKnapsackResults() {
        int rows = backpack.getIndexNumbers().size() + 1;
        int columns = scaledWeightLimit + 1;
        return new KnapsackResult[rows][columns];
    }

    private int getScaleFactor() {
        OptionalInt max = backpack.getWeights().stream()
                .mapToInt(BigDecimal::scale)
                .max();
        return max.isPresent() ? (int) Math.pow(10, max.getAsInt()) : 1;
    }

    @Override
    public KnapsackResult find() {
        int numberOfItems = backpack.getIndexNumbers().size();
        int weightLimit = scaledWeightLimit;
        return find(numberOfItems, weightLimit);
    }

    private KnapsackResult find(int n, int w) {
        if (n <= 0 || w <= 0) {
            return KnapsackResult.EMPTY;
        }
        int scaledWeight = getScaledWeight(backpack.getWeights().get(n - 1));
        Integer cost = backpack.getCosts().get(n - 1);
        Integer index = backpack.getIndexNumbers().get(n - 1);
        if (scaledWeight > w) {
            return find(n - 1, w);
        }

        KnapsackResult withoutN = withoutN(n, w);
        KnapsackResult withN = withN(n, w, scaledWeight, cost, index);
        KnapsackResult knapsackResult = max(withN, withoutN);

        internalKnapsackResults[n][w] = knapsackResult;
        return knapsackResult;
    }

    private KnapsackResult withN(int n, int w, int scaledWeight, Integer cost, Integer index) {
        KnapsackResult knapsackResult;
        if (internalKnapsackResults[n - 1][w - scaledWeight] != null) {
            knapsackResult = (KnapsackResult) internalKnapsackResults[n - 1][w - scaledWeight].clone();
        } else {
            knapsackResult = (KnapsackResult) find(n - 1, w - scaledWeight).clone();
        }
        knapsackResult.add(index, scaledWeight, cost);
        return knapsackResult;
    }

    private KnapsackResult withoutN(int n, int w) {
        KnapsackResult knapsackResult;
        if (internalKnapsackResults[n - 1][w] != null) {
            knapsackResult = internalKnapsackResults[n - 1][w];
        } else {
            knapsackResult = find(n - 1, w);
        }
        return knapsackResult;
    }

    private KnapsackResult max(KnapsackResult first, KnapsackResult second) {
        if (first == null) {
            return second;
        }
        if (second == null || first.getCurrentCost() > second.getCurrentCost()) {
            return first;
        }
        if (first.getCurrentCost() == second.getCurrentCost()) {
            return first.getCurrentScaledWeight() < second.getCurrentScaledWeight() ? first : second;
        }
        return second;
    }

    private int getScaledWeight(BigDecimal weight) {
        return weight.multiply(BigDecimal.valueOf(scaleFactor)).intValue();
    }

}
