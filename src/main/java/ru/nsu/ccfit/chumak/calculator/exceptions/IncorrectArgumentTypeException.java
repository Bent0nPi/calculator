package ru.nsu.ccfit.chumak.calculator.exceptions;

public class IncorrectArgumentTypeException extends ExecutionCommandException{
    public IncorrectArgumentTypeException() {
        super("Type: The argument has an incorrect form\n");
    }

    public IncorrectArgumentTypeException(String commandName) {
        super("Command: "+ commandName + "\nType: The argument has an incorrect form\n");
    }

    public IncorrectArgumentTypeException(String commandName, int argumentPosition, String argument ) {
        super("Command: "+ commandName + "\nType: The argument has an incorrect form\nArgument's position: " +
                argumentPosition + "\nArgument: " + argument + "\n");
    }
}
