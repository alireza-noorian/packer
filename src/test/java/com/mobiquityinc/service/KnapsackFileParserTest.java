package com.mobiquityinc.service;

import com.mobiquityinc.entity.Backpack;
import com.mobiquityinc.exception.APIException;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class KnapsackFileParserTest {

    @Test
    public void parseLineSuccessful() {
        String input = "8: (1,12,$8) (3,34.5,$8)";
        Backpack backpack = KnapsackFileParserImpl.parseLine(input);
        Assert.assertEquals(8, backpack.getWeightLimit());
        Assert.assertEquals(2, backpack.getIndexNumbers().size());
        Assert.assertEquals(3, (int) backpack.getIndexNumbers().get(1));
        Assert.assertEquals(new BigDecimal(34.5), backpack.getWeights().get(1));
        Assert.assertEquals((double) 8, backpack.getCosts().get(1), 0);
    }

    @Test(expected = APIException.class)
    public void InvalidLineFormat() {
        String input = "8: :(1,12,$5) (3,34.5,$8)";
        KnapsackFileParserImpl.parseLine(input);
    }

    @Test
    public void couldNotFindItems() {
        String input = "83: 1,12.6,$5";
        Backpack backpack = KnapsackFileParserImpl.parseLine(input);
        Assert.assertEquals(0, backpack.getIndexNumbers().size());
    }

    @Test(expected = APIException.class)
    public void backpackItemIsIncomplete() {
        String input = "83: (1,12.6)";
        KnapsackFileParserImpl.parseLine(input);
    }

    @Test
    public void parseFile() {
        KnapsackFileParser fileParser = new KnapsackFileParserImpl();
        Backpack[] backpacks = fileParser.parseFile("sample.txt");
        Assert.assertEquals(4, backpacks.length);
    }
}
