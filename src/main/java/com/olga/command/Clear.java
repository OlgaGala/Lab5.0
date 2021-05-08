package com.olga.command;

import com.olga.dragon.Dragon;
import com.olga.i18n.Messenger;

import java.util.Stack;

public class Clear extends Command {

    public Clear(Stack<Dragon> dragonList, Messenger messenger) {
        super(dragonList, messenger);
    }

    @Override
    public String execute(String ignore) {
        getDragonList().clear();
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoClear");
    }
}
