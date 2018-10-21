package com.mobiquityinc.service;

import com.mobiquityinc.entity.Backpack;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class MaxCostMinWeightStrategyTest {

    @Test
    public void findLowerWeightWhenEqualCost() {
        String input = "35 : (1,34.5,$8) (3,12,$8)";
        Backpack backpack = KnapsackFileParserImpl.parseLine(input);
        KnapsackStrategy strategy = new MaxCostMinWeightStrategy(backpack);
        Assert.assertEquals(Arrays.asList(3), strategy.find().getItemsIndex());
    }

    @Test
    public void notFindWhenCouldNotFit() {
        String input = "8 : (1,15.3,$34)";
        Backpack backpack = KnapsackFileParserImpl.parseLine(input);
        KnapsackStrategy strategy = new MaxCostMinWeightStrategy(backpack);
        Assert.assertEquals(Arrays.asList(), strategy.find().getItemsIndex());
    }

    @Test
    public void findMaxCost() {
        String input = "35 : (1,34.5,$20) (3,12,$8)";
        Backpack backpack = KnapsackFileParserImpl.parseLine(input);
        KnapsackStrategy strategy = new MaxCostMinWeightStrategy(backpack);
        Assert.assertEquals(Arrays.asList(1), strategy.find().getItemsIndex());
    }

}
