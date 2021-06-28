package com.api.command;

import com.api.entity.Dragon;
import com.api.message.MessageReq;
import com.api.service.DragonService;

import java.util.Collections;
import java.util.Stack;

public class Shuffle extends Command {

    public Shuffle(Stack<Dragon> dragonList, DragonService dragonService) {
        super(dragonList, dragonService);
    }

    @Override
    public Stack<Dragon> execute(MessageReq ignore) {

        Collections.shuffle(getDragonList());

        return getDragonList();
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoShuffle");
    }
}
