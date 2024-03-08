package ru.nsu.ccfit.chumak.calculator.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.ccfit.chumak.calculator.exceptions.ArgumentsCountException;
import ru.nsu.ccfit.chumak.calculator.exceptions.SmallStackSizeException;
import ru.nsu.ccfit.chumak.calculator.management.Context;
import java.util.EmptyStackException;

public class CommandDivision extends Command {
    private static final Logger logger = LogManager.getLogger(CommandDivision.class);
    @Override
    public void execute(Context appContext, String[] arguments) {
        //check the number of arguments
        if (arguments.length != 0) {
            logger.error("Wrong number of arguments: {} while 0 required",arguments.length);
            throw new ArgumentsCountException("/", arguments.length, 0);
        }

        int countUsedArguments = 0;
        double[] components = {0,0};
        try {
            components[countUsedArguments] = appContext.getArgumentStack().pop();
            ++countUsedArguments;
            components[countUsedArguments] = appContext.getArgumentStack().pop();
            ++countUsedArguments;

            if(components[1] == 0) {
                for(int i = countUsedArguments - 1; i >=0; --i){
                    appContext.getArgumentStack().push(components[i]);
                }
                logger.error("In command division occurred division by zero");
                throw new ArithmeticException("Type: Division by zero");
            }

            double result = components[0] / components[1];
            appContext.getArgumentStack().push(result);
        } catch(EmptyStackException e) {
            logger.error("Stack had got {} elements when 2 were needed", countUsedArguments);
            for(int i = countUsedArguments - 1; i >=0; --i){
                appContext.getArgumentStack().push(components[i]);
            }
            throw new SmallStackSizeException("/", countUsedArguments, 2);
        }
    }
}
