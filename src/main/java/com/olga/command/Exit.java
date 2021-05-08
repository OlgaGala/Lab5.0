package com.olga.command;

import com.olga.dragon.Dragon;
import com.olga.i18n.Messenger;

import java.util.Stack;

public class Exit extends Command {

    public Exit(Stack<Dragon> dragonList, Messenger messenger) {
        super(dragonList, messenger);
    }

    @Override
    public String execute(String ignore) {
        System.exit(0);
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + ": " + "Завершить программу (без сохранения в файл)";
    }
}
