package com.lab7.command;

import com.lab7.entity.Dragon;
import com.lab7.message.Message;
import com.lab7.service.DragonService;

import java.util.Collections;
import java.util.Stack;

public class Shuffle extends Command {

    public Shuffle(Stack<Dragon> dragonList, DragonService dragonService) {
        super(dragonList, dragonService);
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
