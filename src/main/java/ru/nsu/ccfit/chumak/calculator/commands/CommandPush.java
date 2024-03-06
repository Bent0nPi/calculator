package ru.nsu.ccfit.chumak.calculator.commands;

import ru.nsu.ccfit.chumak.calculator.exceptions.ArgumentsCountException;
import ru.nsu.ccfit.chumak.calculator.exceptions.IncorrectArgumentTypeException;
import ru.nsu.ccfit.chumak.calculator.management.Context;

import java.util.ArrayList;

public class CommandPush extends Command{
    @Override
    public void execute(Context appContext, String[] arguments) {
        // there is no required number of arguments
        for(int i = 0; i < arguments.length; ++i) {
            try {
                appContext.getArgumentStack().push(Double.parseDouble(arguments[i]));
            } catch (NumberFormatException e){
                throw new IncorrectArgumentTypeException("PUSH", i, arguments[i]);
            }
        }
    }
}
