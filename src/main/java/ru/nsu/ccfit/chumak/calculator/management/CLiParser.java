package ru.nsu.ccfit.chumak.calculator.management;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class CLiParser extends Parser {
    private final static Logger logger = LogManager.getLogger(CLiParser.class);
    private final BufferedReader reader;

    public CLiParser () {
        logger.info("Creating BufferedReader for InputStreamReader for console input");
        reader = new BufferedReader( new InputStreamReader(System.in));
    }

    @Override
    public ParsedRequest parse() {
        String input;
        ParsedRequest result = new ParsedRequest();
        try {
            logger.info("Reading file");
            input = reader.readLine();
        } catch (IOException e) {
            logger.fatal("Error reading console input", e);
            throw new RuntimeException(e);
        }

        if (parseString(input, result)) return new ParsedRequest();
        return result;
    }

    @Override
    public Boolean isReady() {
        return true;
    }


}
