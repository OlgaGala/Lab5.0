package com.olga.command;

import com.olga.dragon.Dragon;

import java.util.Collections;
import java.util.Stack;

public class Shuffle extends Command {

    public Shuffle(Stack<Dragon> dragonList) {
        super(dragonList);
    }

    @Override
    public String execute(String ignore) {

        Collections.shuffle(getDragonList());

        return getFormatter().formatCollection(getDragonList());
    }

    @Override
    public String toString() {
        return super.toString() + ": " + "Перемешать элементы коллекции в случайном порядке";
    }
}
