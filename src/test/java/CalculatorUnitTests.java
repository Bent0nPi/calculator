import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import ru.nsu.ccfit.chumak.calculator.commands.*;
import ru.nsu.ccfit.chumak.calculator.exceptions.ArgumentsCountException;
import ru.nsu.ccfit.chumak.calculator.exceptions.SmallStackSizeException;
import ru.nsu.ccfit.chumak.calculator.management.Context;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorUnitTests {
    private final Context appContext = new Context();


    @TestFactory
    Stream<DynamicTest> commandWithoutArgumentsTests() {
        List<Command> inputCommands = Arrays.asList(
                new CommandSum(), new CommandDifference(), new CommandProduct(), new CommandDivision(), new CommandSqrt()
        );
        List<String> inputCommandsNames = Arrays.asList(
                "sum", "difference", "product", "division", "sqrt"
        );
        List<Double> results = Arrays.asList(
                3.0, 5.0, -4.0, -4.0, 2.0
        );
        return inputCommands.stream().map(
            com -> {
                int returnedInd = inputCommands.indexOf(com);
                return DynamicTest.dynamicTest(
                    "Command executing without arguments: " + inputCommandsNames.get(returnedInd),
                    () -> {
                        appContext.getArgumentStack().push(-1.0);
                        appContext.getArgumentStack().push(4.0);
                        String[] arguments = new String[0];
                        com.execute(appContext,arguments);
                        assertEquals(results.get(returnedInd), appContext.getArgumentStack().peek());
                    }
                );
            }
        );
    }

    @Test
    public void sumWithArguments() {
        CommandSum commandSum = new CommandSum();
        appContext.getArgumentStack().push(1.0);
        appContext.getArgumentStack().push(-4.0);
        String[] arguments = {"1", "2", "3"};
        try{
            commandSum.execute(appContext,arguments);
        } catch (ArgumentsCountException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(-4.0, appContext.getArgumentStack().peek());
    }

    @Test
    public void sumWithSmallStack() {
        CommandSum commandSum = new CommandSum();
        appContext.getArgumentStack().push(1.0);
        String[] arguments = new String[0];
        try{
            commandSum.execute(appContext,arguments);
        } catch (SmallStackSizeException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(1.0, appContext.getArgumentStack().peek());
    }
}
