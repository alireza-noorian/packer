package com.mobiquityinc.service;

import com.mobiquityinc.entity.Backpack;
import com.mobiquityinc.entity.BackpackItem;
import com.mobiquityinc.exception.APIException;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class KnapsackFileParserImpl implements KnapsackFileParser {

    public static Backpack parseLine(String line) {
        String[] split = checkAndSplit(line);
        int weightLimit = getWeightLimit(split[0]);
        Backpack backpack = new Backpack(weightLimit);
        addItemsToBackpack(split[1], backpack);
        return backpack;
    }

    private static void addItemsToBackpack(String input, Backpack backpack) {
        Pattern pattern = Pattern.compile("\\((.*?)\\)");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String group = matcher.group(1);
            backpack.addItem(getItem(group));
        }
    }

    private static BackpackItem getItem(String group) {
        if (group == null || group.isEmpty()) {
            throw new APIException("BackpackItem is null or empty");
        }
        String[] split = group.split(",");
        try {
            BackpackItem backpackItem = new BackpackItem();
            backpackItem.setIndex(Integer.valueOf(split[0].trim()));
            backpackItem.setWeight(new BigDecimal(split[1].trim()));
            backpackItem.setCost(Integer.valueOf(split[2].trim().substring(1)));
            return backpackItem;
        } catch (Exception e) {
            throw new APIException("BackpackItem has invalid format");
        }
    }

    private static String[] checkAndSplit(String line) {
        if (line == null || line.isEmpty()) {
            throw new APIException("line is empty");
        }
        String[] split = line.split(":");
        if (split.length != 2) {
            throw new APIException("Invalid line format");
        }
        return split;
    }

    private static int getWeightLimit(String input) {
        try {
            return Integer.valueOf(input.trim());
        } catch (Exception e) {
            throw new APIException("Weight limit not found");
        }
    }

    @Override
    public Backpack[] parseFile(String path) {
        try {
            return Files.readAllLines(Paths.get(path)).stream()
                    .map(KnapsackFileParserImpl::parseLine)
                    .collect(Collectors.toList())
                    .toArray(new Backpack[0]);
        } catch (IOException e) {
            throw new APIException(String.format("File Not Found or IO Error: %s", e.getMessage()));
        }
    }
}
