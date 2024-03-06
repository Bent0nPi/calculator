package ru.nsu.ccfit.chumak.calculator.commands;

import ru.nsu.ccfit.chumak.calculator.exceptions.ArgumentsCountException;
import ru.nsu.ccfit.chumak.calculator.exceptions.IncorrectArgumentTypeException;
import ru.nsu.ccfit.chumak.calculator.exceptions.IncorrectCommandNameException;
import ru.nsu.ccfit.chumak.calculator.management.Context;

import java.util.ArrayList;

public class CommandDefine extends Command {
    @Override
    public void execute(Context appContext, String[] arguments) {
        //check the number of arguments
        if (arguments.length != 2) {
            throw new ArgumentsCountException("DEFINE", arguments.length, 2);
        }
        //check types of arguments
        for (int i = 0; i < arguments[0].length(); ++i){
              if(!Character.isLetter(arguments[0].charAt(i)))
                  throw new IncorrectArgumentTypeException("DEFINE", 0, arguments[0]);
        }

        try {
            double value = Double.parseDouble(arguments[arguments.length - 1]);
            appContext.getDefinitions().put(arguments[0], value);
        } catch (NumberFormatException e) {
            throw new IncorrectArgumentTypeException("DEFINE", 1, arguments[1]);
        }

    }
}
