package com.olga.command;

import com.olga.dragon.Dragon;

import java.util.List;
import java.util.Stack;

public class Show extends Command {

    public Show(Stack<Dragon> dragonList) {
        super(dragonList);
    }

    @Override
    public String execute(String ignore) {
        return getFormatter().formatCollection(getDragonList());
    }

    @Override
    public String toString() {
        return super.toString() + ": " + "Вывести коллекцию";
    }
}
