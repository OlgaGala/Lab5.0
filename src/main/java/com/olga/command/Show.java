package com.olga.command;

import com.olga.dragon.Dragon;

import java.util.List;
import java.util.Stack;

public class Show extends Command<List<Dragon>> {

    public Show(Stack<Dragon> dragonList) {
        super(dragonList);
    }

    @Override
    public List<Dragon> execute() {
        return getDragonList();
    }

    @Override
    public String toString() {
        return super.toString() + ": " + "Вывести коллекцию";
    }
}
