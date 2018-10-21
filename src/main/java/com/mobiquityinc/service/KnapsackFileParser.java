package com.mobiquityinc.service;

import com.mobiquityinc.entity.Backpack;
import com.mobiquityinc.exception.APIException;

public interface KnapsackFileParser {
    Backpack[] parseFile(String path);
}
