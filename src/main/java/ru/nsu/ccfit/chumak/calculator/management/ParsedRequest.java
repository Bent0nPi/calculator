package ru.nsu.ccfit.chumak.calculator.management;

import java.util.ArrayList;

public class ParsedRequest {
    private Boolean isCommand;
    private String name;
    private String[] arguments;

    public ParsedRequest() {
        isCommand = false;
        name = "";
        arguments = new String[1];
    }

    public void setName (String newName) {
        name = newName;
    }

    public void setArguments(String[] args) {
        arguments = args;
    }

    public void setIsCommand(Boolean value) {
        isCommand = value;
    }

    public String getName () {
        return name;
    }

    public String[] getArguments() {
        return arguments;
    }

    public Boolean getIsCommand() {
        return  isCommand;
    }
}
