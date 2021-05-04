package com.olga.command;

import com.olga.dragon.Dragon;

import java.util.Stack;

public class Clear extends Command<Void> {

    public Clear(Stack<Dragon> dragonList) {
        super(dragonList);
    }

    @Override
    public Void execute() {
        getDragonList().clear();
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + ": " + "Очистить коллекцию";
    }
}
