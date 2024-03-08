package ru.nsu.ccfit.chumak.calculator;

import ru.nsu.ccfit.chumak.calculator.commands.Command;
import ru.nsu.ccfit.chumak.calculator.exceptions.ExecutionCommandException;
import ru.nsu.ccfit.chumak.calculator.exceptions.IncorrectCommandNameException;
import ru.nsu.ccfit.chumak.calculator.factory.Factory;
import ru.nsu.ccfit.chumak.calculator.management.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Calculator {
    private final static Logger logger = LogManager.getLogger(Calculator.class);

    public static void main(String[] args) {
        logger.info("=====Starting Calculator's main function=====");
        logger.info("Initializing Parser");
        Parser parser;
        if(args.length == 0){
            parser = new CLiParser();
        } else if (args.length == 1){
            parser = new FileParser(args[0]);
        } else {
            logger.error("There are {} arguments in program, while 1 required", args.length);
            System.out.println("Too many arguments were received");
            parser = new FileParser(args[0]);
        }
        logger.info("Initializing Context and Factory");
        Context appContext = new Context();
        Factory factory = new Factory();

        logger.info("Starting working cycle");
        while(parser.isReady()) {
            logger.info("Getting request");
            ParsedRequest request = parser.parse();
            if (!request.getIsCommand()) continue;
            try {
                logger.info("Creating command class with factory for name {}",request.getName());
                Command currentCommand = factory.createCommand(request.getName());
                logger.info("Executing command {} with arguments {}", request.getName(), request.getArguments());
                currentCommand.execute(appContext, request.getArguments());
            } catch(IncorrectCommandNameException | ExecutionCommandException | ArithmeticException e){
                System.out.println(e.getMessage());
            }
        }
        logger.info("======Ending Calculator's main function======");
    }
}