package com.olga.command;

import com.olga.dragon.Dragon;

import java.util.Stack;

public class Clear extends Command {

    public Clear(Stack<Dragon> dragonList) {
        super(dragonList);
    }

    @Override
    public String execute(String ignore) {
        getDragonList().clear();
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + ": " + "Очистить коллекцию";
    }
}
