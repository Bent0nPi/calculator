package ru.nsu.ccfit.chumak.calculator.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.ccfit.chumak.calculator.exceptions.IncorrectArgumentTypeException;
import ru.nsu.ccfit.chumak.calculator.management.Context;

public class CommandPush extends Command{
    private static final Logger logger = LogManager.getLogger(CommandPush.class);
    @Override
    public void execute(Context appContext, String[] arguments) {
        // there is no required number of arguments
        for(int i = 0; i < arguments.length; ++i) {
            try {
                if (appContext.getDefinitions().containsKey(arguments[i])){
                    arguments[i] = "" + appContext.getDefinitions().get(arguments[i]);
                }
                appContext.getArgumentStack().push(Double.parseDouble(arguments[i]) );
            } catch (NumberFormatException e){
                logger.error("argument {} is not a double", arguments[i]);
                throw new IncorrectArgumentTypeException("PUSH", i, arguments[i]);
            }
        }
    }
}
