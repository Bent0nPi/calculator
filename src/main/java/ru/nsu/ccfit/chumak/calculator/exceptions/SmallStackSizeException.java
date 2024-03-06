package ru.nsu.ccfit.chumak.calculator.exceptions;

public class SmallStackSizeException extends ExecutionCommandException{
    public SmallStackSizeException() {
        super("Type: The stack size is too small to execute the command\n");
    }

    public SmallStackSizeException(String commandName) {
        super("Command: "+ commandName + "\nType: The stack size is too small to execute the command\n");
    }

    public SmallStackSizeException(String commandName, int stackSize, int requiredStackSize) {
        super("Command: "+ commandName + "\nType: The stack size is too small to execute the command\nStack size: " +
                stackSize + "\nRequired number of stack elements: " + requiredStackSize);
    }
}
