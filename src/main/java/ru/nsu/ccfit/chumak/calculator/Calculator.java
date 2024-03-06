package ru.nsu.ccfit.chumak.calculator;

import ru.nsu.ccfit.chumak.calculator.commands.Command;
import ru.nsu.ccfit.chumak.calculator.exceptions.ExecutionCommandException;
import ru.nsu.ccfit.chumak.calculator.exceptions.IncorrectCommandNameException;
import ru.nsu.ccfit.chumak.calculator.factory.Factory;
import ru.nsu.ccfit.chumak.calculator.management.*;

public class Calculator {
    public static void main(String[] args) {
        Parser parser;
        if(args.length == 0){
            parser = new CLiParser();
        } else if (args.length == 1){
            parser = new FileParser(args[0]);
        } else {
            //TODO  напечатать ошибку, что слишком много аргументов
            parser = new FileParser(args[0]);
        }
        Context appContext = new Context();
        Factory factory = new Factory();

//        ParsedCommand request = parser.parse();
//        Command currentCommand = factory.createCommand(request.getName());
//        currentCommand.execute(appContext, request.getArguments());
      while(parser.isReady()) {
            ParsedRequest request = parser.parse();
            if (!request.getIsCommand()) continue;
            try {
                Command currentCommand = factory.createCommand(request.getName());
                currentCommand.execute(appContext, request.getArguments());
            } catch(IncorrectCommandNameException | ExecutionCommandException | ArithmeticException e){
                System.out.println(e.getMessage());
            }
      }
    }
}