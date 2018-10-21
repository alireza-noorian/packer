package com.mobiquityinc;


import com.mobiquityinc.packer.Packer;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Main {
    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        PropertyConfigurator.configure("log4j.properties");
        if (args.length == 0) {
            logger.error("You should specify a file name as a first argument");
            return;
        }
        try {
            String fileName = args[0];
            String pack = Packer.pack(fileName);
            logger.info("Solution:");
            logger.info(pack);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
