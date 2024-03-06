package ru.nsu.ccfit.chumak.calculator.exceptions;

public class UndefinedTermException extends ExecutionCommandException{
    public UndefinedTermException() {
        super("Type: There is undefined term in arguments\n");
    }
    public UndefinedTermException(String commandName) {
        super("Command: "+ commandName + "\nType: There is undefined term in arguments\n");
    }

    public UndefinedTermException(String commandName, int argumentPosition, String argument) {
        super("Command: "+ commandName + "\nType: There is undefined term in arguments\n Argument's position:" +
                argumentPosition + "\nArgument: " + argument + "\n");
    }
}
