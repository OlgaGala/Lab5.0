package com.olga.command;

import com.olga.dragon.Dragon;

import java.util.List;
import java.util.Stack;

public class Exit extends Command<Void> {

    public Exit(Stack<Dragon> dragonList) {
        super(dragonList);
    }

    @Override
    public Void execute() {
        System.exit(0);
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + ": " + "Завершить программу (без сохранения в файл)";
    }
}
