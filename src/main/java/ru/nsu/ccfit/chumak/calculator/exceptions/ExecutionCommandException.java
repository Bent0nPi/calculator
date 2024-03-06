package ru.nsu.ccfit.chumak.calculator.exceptions;

public class ExecutionCommandException extends RuntimeException{
    public ExecutionCommandException() {
        super("Exception occurred while command was executing\n");
    }

    public ExecutionCommandException(String description) {
        super("Exception occurred while command was executing\nDescription:\n" + description);
    }
}
