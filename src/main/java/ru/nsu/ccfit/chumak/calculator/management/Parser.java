package ru.nsu.ccfit.chumak.calculator.management;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

abstract public class Parser {
    private final static Logger logger = LogManager.getLogger(Parser.class);
    public abstract ParsedRequest parse();
    public abstract Boolean isReady();

    static boolean parseString(String input, ParsedRequest result) {
        String[] info;
        logger.info("Parsing string: {}", input);
        if(input.replaceAll(" ", "").isEmpty()){
            info = new String[1];
            info[0] = "";
        } else {
            info = input.split(" ");
        }

        if(info.length == 1 && info[0].isEmpty() || info[0].charAt(0) == '#') {
            return true;
        }

        result.setName(info[0]);
        String[] parsedArguments = new String[info.length - 1];
        System.arraycopy(info, 1, parsedArguments, 0, info.length - 1);
        result.setArguments(parsedArguments);
        result.setIsCommand(true);
        return false;
    }

}
