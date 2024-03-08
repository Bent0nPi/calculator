package ru.nsu.ccfit.chumak.calculator.management;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileParser extends Parser {
    private final static Logger logger = LogManager.getLogger(FileParser.class);
    private BufferedReader reader;

    public FileParser(String fileName){
        try {
            logger.info("Creating FileReader object for file: {}", fileName);
            FileReader fileReader = new FileReader(fileName);
            logger.info("Creating BufferedReader");
            reader = new BufferedReader(fileReader);
        } catch(FileNotFoundException e){
            logger.error("File not found: {}", fileName);
            System.err.println("Input file not found: " + e.getMessage());
        }
    }

    @Override
    public ParsedRequest parse() {
        ParsedRequest result = new ParsedRequest();
        try{
            if(reader.ready()){
                logger.info("Reading file");
                String input = reader.readLine();
                if (parseString(input, result)) return new ParsedRequest();
            } else{
                logger.info("Input file was closed");
                reader.close();
                return result;
            }

        } catch (IOException e){
            logger.fatal("IOException while parsing file: {}", e.getMessage());
            System.err.println("I/O exception occurred while program was being executed: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Boolean isReady() {
        try {
            return reader != null && reader.ready();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
