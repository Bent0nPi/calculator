package ru.nsu.ccfit.chumak.calculator.management;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Context {
    private final Stack<Double> argumentStack;
    private final Map<String, Double> definitions;

    public Context (Stack<Double> arguments, Map<String, Double> constants) {
        argumentStack = arguments;
        definitions = constants;
    }

    public Context() {
        this(new Stack<Double>(), new HashMap<String, Double>());
    }

    public Stack<Double> getArgumentStack() {
        return argumentStack;
    }

    public Map<String, Double> getDefinitions(){
        return definitions;
    }
}
