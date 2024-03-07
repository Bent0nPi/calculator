package ru.nsu.ccfit.chumak.calculator.management;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class CLiParser implements Parser{
    private final BufferedReader reader;

    public CLiParser () {
        reader = new BufferedReader( new InputStreamReader(System.in));
    }

    @Override
    public ParsedRequest parse() {
        String input = null;
        try {
            input = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String[] info;
        if(input.replaceAll(" ", "").isEmpty()){
            info = new String[1];
            info[0] = "";
        } else {
            info = input.split(" ");
        }

        if(info.length == 1 && info[0].isEmpty() || info[0].charAt(0) == '#') {
            return new ParsedRequest();
        }

        ParsedRequest result = new ParsedRequest();
        result.setName(info[0]);
        String[] parsedArguments = new String[info.length - 1];
        System.arraycopy(info, 1, parsedArguments, 0, info.length - 1);
        result.setArguments(parsedArguments);
        result.setIsCommand(true);
        return result;
    }

    @Override
    public Boolean isReady() {
        return true;
    }


}
