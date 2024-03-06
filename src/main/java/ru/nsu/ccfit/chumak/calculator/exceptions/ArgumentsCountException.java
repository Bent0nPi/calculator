package ru.nsu.ccfit.chumak.calculator.exceptions;

public class ArgumentsCountException extends ExecutionCommandException{
    public ArgumentsCountException (){
        super("Type: The number of arguments is not equal to the required one\n");
    }

    public ArgumentsCountException(String commandName){
        super("Command: " + commandName + "\nType: The number of arguments is not equal to the required one\n");
    }

    public ArgumentsCountException(String commandName, int argumentsCount, int requiredCount) {
        super("Command: " + commandName + "\nType: The number of arguments is not equal to the required one\n" +
                "The number of arguments received: " + argumentsCount + "\nRequired number of arguments: " +
                requiredCount + "\n");
    }
}
