package com.olga.command;

import com.olga.dragon.Dragon;

import java.util.Collections;
import java.util.Stack;

public class Shuffle extends Command<Stack<Dragon>> {

    public Shuffle(Stack<Dragon> dragonList) {
        super(dragonList);
    }

    @Override
    public Stack<Dragon> execute() {
        Collections.shuffle(getDragonList());

        return getDragonList();
    }

    @Override
    public String toString() {
        return super.toString() + ": " + "Перемешать элементы коллекции в случайном порядке";
    }
}
