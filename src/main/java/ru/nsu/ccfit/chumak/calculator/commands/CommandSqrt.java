package ru.nsu.ccfit.chumak.calculator.commands;

import ru.nsu.ccfit.chumak.calculator.exceptions.ArgumentsCountException;
import ru.nsu.ccfit.chumak.calculator.exceptions.SmallStackSizeException;
import ru.nsu.ccfit.chumak.calculator.management.Context;

import java.util.ArrayList;
import java.util.EmptyStackException;

import static java.lang.Math.sqrt;

public class CommandSqrt extends Command{
    @Override
    public void execute(Context appContext, String[] arguments) {
        //check the number of arguments
        if (arguments.length != 0) {
            throw new ArgumentsCountException("SQRT", arguments.length, 0);
        }

        double component = 0.0;
        try {
            component = appContext.getArgumentStack().pop();
            if (component < 0) {
                appContext.getArgumentStack().push(component);
                throw new ArithmeticException("The square root is taken from a negative number");
            }
            appContext.getArgumentStack().push(sqrt(component));
        } catch(EmptyStackException e) {
            throw new SmallStackSizeException("SQRT", 0, 1);
        }
    }
}
