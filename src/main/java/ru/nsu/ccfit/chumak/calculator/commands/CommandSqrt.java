package ru.nsu.ccfit.chumak.calculator.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.ccfit.chumak.calculator.exceptions.ArgumentsCountException;
import ru.nsu.ccfit.chumak.calculator.exceptions.SmallStackSizeException;
import ru.nsu.ccfit.chumak.calculator.management.Context;
import java.util.EmptyStackException;

import static java.lang.Math.sqrt;

public class CommandSqrt extends Command{
    private static final Logger logger = LogManager.getLogger(CommandSqrt.class);
    @Override
    public void execute(Context appContext, String[] arguments) {
        //check the number of arguments
        if (arguments.length != 0) {
            logger.error("Wrong number of arguments: {} while 0 required",arguments.length);
            throw new ArgumentsCountException("SQRT", arguments.length, 0);
        }

        double component;
        try {
            component = appContext.getArgumentStack().pop();
            if (component < 0) {
                appContext.getArgumentStack().push(component);
                logger.error("The square root was taken from a negative number");
                throw new ArithmeticException("The square root is taken from a negative number");
            }
            appContext.getArgumentStack().push(sqrt(component));
        } catch(EmptyStackException e) {
            logger.error("Stack had got {} elements when 1 were needed", 0);
            throw new SmallStackSizeException("SQRT", 0, 1);
        }
    }
}
