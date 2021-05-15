package com.olga.command;

import com.olga.dragon.Dragon;
import com.olga.i18n.Messenger;
import com.olga.message.Message;

import java.util.Collections;
import java.util.Stack;

public class Shuffle extends Command {

    public Shuffle(Stack<Dragon> dragonList) {
        super(dragonList);
    }

    @Override
    public String execute(Message ignore) {

        Collections.shuffle(getDragonList());

        return getFormatter().formatCollection(getDragonList());
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoShuffle");
    }
}
