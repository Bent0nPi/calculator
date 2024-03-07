package ru.nsu.ccfit.chumak.calculator.management;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FileParser implements Parser{
    private BufferedReader reader;

    public FileParser(String fileName){
       try {
           FileReader fileReader = new FileReader(fileName);
           reader = new BufferedReader(fileReader);
       } catch(FileNotFoundException e){
           System.err.println("Input file not found: " + e.getMessage());
       }
    }

    @Override
    public ParsedRequest parse() {
        ParsedRequest result = new ParsedRequest();
        try{
            if(reader.ready()){
                String input = reader.readLine();
                String[] info;

                if(input.replaceAll(" ", "").isEmpty()){
                    info = new String[1];
                    info[0] = "";
                } else {
                    info = input.split(" ");
                }

                if( info.length == 1 && info[0].isEmpty() || info[0].charAt(0) == '#') {
                    return new ParsedRequest();
                }
                result.setName(info[0]);
                String[] parsedArguments = new String[info.length - 1];
                System.arraycopy(info, 1, parsedArguments, 0, info.length - 1);
                result.setArguments(parsedArguments);
                result.setIsCommand(true);
            } else{
                reader.close();
                return result;
            }

        } catch (IOException e){
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
