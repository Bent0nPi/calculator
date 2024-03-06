package ru.nsu.ccfit.chumak.calculator.management;

public interface Parser {
    ParsedRequest parse();
    Boolean isReady();
}
