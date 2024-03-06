package ru.nsu.ccfit.chumak.calculator.factory;

import ru.nsu.ccfit.chumak.calculator.commands.Command;
import ru.nsu.ccfit.chumak.calculator.exceptions.IncorrectCommandNameException;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class Factory {
    public Command createCommand(String commandName) {

        try(InputStream sources = Factory.class.getResourceAsStream("factoryConfig.txt")) {
            if (sources != null) {
                try(InputStreamReader inputStreamReader = new InputStreamReader(sources);
                    BufferedReader reader = new BufferedReader(inputStreamReader)) {
                    String filePath = "";
                    while(reader.ready()){
                        String[] definition = reader.readLine().split(" ");
                        if(Objects.equals(definition[0], commandName)){
                            filePath = definition[1];
                            break;
                        }
                    }
                    if(filePath.isEmpty()){
                        throw new IncorrectCommandNameException(commandName);
                    }
                    return (Command) Class.forName(filePath).getDeclaredConstructor().newInstance();

                } catch (ClassNotFoundException e) {
                    System.err.println("Class of command was not found: " + e.getMessage());
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    System.err.println("Invocation target" + e.getMessage());
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    System.err.println("Class can not instance" + e.getMessage());
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    System.err.println("Illegal access to class " + e.getMessage());
                    throw new RuntimeException(e);
                } catch (NoSuchMethodException e) {
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
