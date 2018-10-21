package com.mobiquityinc.packer;

import com.mobiquityinc.service.KnapsackFileParser;
import com.mobiquityinc.service.KnapsackFileParserImpl;
import com.mobiquityinc.service.MaxCostMinWeightStrategy;

import java.util.Arrays;
import java.util.Optional;

public class Packer {

    public static String pack(String path) {
        KnapsackFileParser knapsackFileParser = new KnapsackFileParserImpl();
        Optional<String> solution = Arrays.stream(knapsackFileParser.parseFile(path))
                .map(backpack -> new MaxCostMinWeightStrategy(backpack)
                        .find()
                        .getSolution())
                .map(s -> String.format("%s\r\n", s))
                .reduce((s1, s2) -> String.format("%s%s", s1, s2));
        return solution.orElse(null);
    }
}
