package ru.nsu.ccfit.chumak.calculator.commands;

import ru.nsu.ccfit.chumak.calculator.exceptions.ArgumentsCountException;
import ru.nsu.ccfit.chumak.calculator.exceptions.SmallStackSizeException;
import ru.nsu.ccfit.chumak.calculator.management.Context;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class CommandPrint extends Command{

    @Override
    public void execute(Context appContext, String[] arguments) {
        //check the number of arguments
        if (arguments.length != 0) {
            throw new ArgumentsCountException("PRINT", arguments.length, 0);
        }

        try {
            System.out.println(appContext.getArgumentStack().peek());
        }catch (EmptyStackException e){
            throw new SmallStackSizeException("PRINT", 0, 1);
        }
    }
}
