package ru.nsu.ccfit.chumak.calculator.commands;

import ru.nsu.ccfit.chumak.calculator.management.Context;

import java.util.ArrayList;

public abstract class Command {
    abstract public void execute(Context appContext, String[] arguments);
}
