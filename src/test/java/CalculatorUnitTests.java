import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.nsu.ccfit.chumak.calculator.commands.*;
import ru.nsu.ccfit.chumak.calculator.exceptions.ArgumentsCountException;
import ru.nsu.ccfit.chumak.calculator.exceptions.IncorrectArgumentTypeException;
import ru.nsu.ccfit.chumak.calculator.exceptions.SmallStackSizeException;
import ru.nsu.ccfit.chumak.calculator.factory.Factory;
import ru.nsu.ccfit.chumak.calculator.management.Context;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorUnitTests {
    private final Context appContext = new Context();


    @TestFactory
    Stream<DynamicTest> commandWithoutArgumentsTests() {
        List<Command> inputCommands = Arrays.asList(
                new CommandSum(), new CommandDifference(), new CommandProduct(), new CommandDivision(), new CommandSqrt(), new CommandPop(), new CommandPush()
        );
        List<String> inputCommandsNames = Arrays.asList(
                "sum", "difference", "product", "division", "sqrt", "pop", "push"
        );
        List<Double> results = Arrays.asList(
                3.0, 5.0, -4.0, -4.0, 2.0, -1.0, 4.0
        );
        return inputCommands.stream().map(
            command -> {
                int returnedInd = inputCommands.indexOf(command);
                return DynamicTest.dynamicTest(
                    "Command executing without arguments: " + inputCommandsNames.get(returnedInd),
                    () -> {
                        appContext.getArgumentStack().push(-1.0);
                        appContext.getArgumentStack().push(4.0);
                        String[] arguments = new String[0];
                        try {
                            command.execute(appContext,arguments);
                        } catch (ArgumentsCountException e) {
                            System.out.println(e.getMessage());
                        }
                        assertEquals(results.get(returnedInd), appContext.getArgumentStack().peek());
                    }
                );
            }
        );
    }


    @TestFactory
    Stream<DynamicTest> commandWithArgumentsTests() {
        List<Command> inputCommands = Arrays.asList(
                new CommandSum(), new CommandDifference(), new CommandProduct(), new CommandDivision(), new CommandSqrt(), new CommandPop(), new CommandPush()
        );
        List<String> inputCommandsNames = Arrays.asList(
                "sum", "difference", "product", "division", "sqrt", "pop", "push"
        );
        List<Double> results = Arrays.asList(
                4.0, 4.0, 4.0, 4.0, 4.0, 4.0, 1.0
        );
        return inputCommands.stream().map(
          command -> {
              int returnedInd = inputCommands.indexOf(command);
              return DynamicTest.dynamicTest(
                "Command executing with arguments: " + inputCommandsNames.get(returnedInd),
                () -> {
                    appContext.getArgumentStack().push(-1.0);
                    appContext.getArgumentStack().push(4.0);
                    String[] arguments = {"1.0"};
                    try {
                        command.execute(appContext,arguments);
                    } catch (ArgumentsCountException e) {
                        System.out.println(e.getMessage());
                    }
                    assertEquals(results.get(returnedInd), appContext.getArgumentStack().peek());
                }
              );
          }
        );
    }

    @TestFactory
    Stream<DynamicTest> commandWithSmallStackSizeTests() {
        List<Command> inputCommands = Arrays.asList(
                new CommandSum(), new CommandDifference(), new CommandProduct(), new CommandDivision(), new CommandSqrt(), new CommandPop(), new CommandPush()
        );
        List<String> inputCommandsNames = Arrays.asList(
                "sum", "difference", "product", "division", "sqrt", "pop", "push"
        );
        List<Double> results = Arrays.asList(
                4.0, 4.0, 4.0, 4.0, 2.0, 0.0, 4.0
        );
        return inputCommands.stream().map(
            command -> {
                int returnedInd = inputCommands.indexOf(command);
                return DynamicTest.dynamicTest(
                        "Command executing with small stack: " + inputCommandsNames.get(returnedInd),
                        () -> {
                            appContext.getArgumentStack().clear();
                            appContext.getArgumentStack().push(4.0);
                            String[] arguments = new String[0];
                            try {
                                command.execute(appContext,arguments);
                            } catch (ArgumentsCountException | SmallStackSizeException |
                                     IncorrectArgumentTypeException e) {
                                System.out.println(e.getMessage());
                            }
                            assertTrue(appContext.getArgumentStack().isEmpty() || appContext.getArgumentStack().peek().equals(results.get(returnedInd)));
                        }
                );
            }
        );
    }


    @ParameterizedTest(name = "{index} - {0} is a known command at the factory")
    @ValueSource(strings = {"+", "-", "*", "/", "SQRT", "PRINT", "DEFINE", "PUSH", "POP"})
    void testFactory(String command) {
        Factory factory = new Factory();
        assertNotNull(factory.createCommand(command));
    }

    @TestFactory
    Stream<DynamicTest> testFactoryWithSmallStackSizeTests() {
        List<String[]> inputArguments = Arrays.asList(
                new String[]{"a", "4.0"}, new String[]{"2.0", "4.0"}, new String[]{"c", "b"}, new String[]{"d", "a"}, new String[]{"d", "1.0", "2.0"}
        );
        List<Integer> results = Arrays.asList(
                1, 1, 1, 2, 2
        );
        return inputArguments.stream().map(
            pair -> {
                int returnedInd = inputArguments.indexOf(pair);
                return DynamicTest.dynamicTest(
                        "Check DEFINE command with: " + Arrays.toString(pair),
                        () -> {
                            Command command = new CommandDefine();
                            try {
                                command.execute(appContext,pair);
                            } catch (ArgumentsCountException | IncorrectArgumentTypeException e) {
                                System.out.println(e.getMessage());
                            }
                            assertEquals(results.get(returnedInd), appContext.getDefinitions().size());
                        }
                );
            }
        );

    }

}
