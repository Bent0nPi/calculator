package ru.nsu.ccfit.chumak.calculator.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.ccfit.chumak.calculator.commands.Command;
import ru.nsu.ccfit.chumak.calculator.exceptions.IncorrectCommandNameException;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class Factory {
    private static final Logger logger = LogManager.getLogger(Factory.class);
    public Command createCommand(String commandName) {
        logger.info("Opening config file");
        try(InputStream sources = Factory.class.getResourceAsStream("factoryConfig.txt")) {
            if (sources != null) {
                try(InputStreamReader inputStreamReader = new InputStreamReader(sources);
                    BufferedReader reader = new BufferedReader(inputStreamReader)) {
                    logger.info("Searching for command '{}'", commandName);
                    String filePath = "";
                    while(reader.ready()){
                        String[] definition = reader.readLine().split(" ");
                        if(Objects.equals(definition[0], commandName)){
                            filePath = definition[1];
                            break;
                        }
                    }
                    if(filePath.isEmpty()){
                        logger.error("Command '{}' not found", commandName);
                        throw new IncorrectCommandNameException(commandName);
                    }
                    return (Command) Class.forName(filePath).getDeclaredConstructor().newInstance();

                } catch (ClassNotFoundException e) {
                    logger.error("Command '{}' not found", commandName);
                    System.err.println("Class of command was not found: " + e.getMessage());
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    logger.error("Command '{}' is invocation target", commandName);
                    System.err.println("Invocation target" + e.getMessage());
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    logger.error("Command '{}' can not instance", commandName);
                    System.err.println("Class can not instance" + e.getMessage());
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    logger.error("Command '{}' has no legal access ", commandName);
                    System.err.println("Illegal access to class " + e.getMessage());
                    throw new RuntimeException(e);
                } catch (NoSuchMethodException e) {
                    logger.error("Command '{}' has no construct method", commandName);
                    System.err.println("Class interface has no used method" + e.getMessage());
                    throw new RuntimeException(e);
                }
            }
        } catch(IOException e){

            System.err.println("Error while reading sources: " + e.getMessage());
        }
        return null;
    }
}
