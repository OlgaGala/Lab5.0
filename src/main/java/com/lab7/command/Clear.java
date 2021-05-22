package com.lab7.command;

import com.lab7.dragon.Dragon;
import com.lab7.message.Message;
import com.lab7.service.DragonService;

import java.util.Stack;

public class Clear extends Command {

    public Clear(Stack<Dragon> dragonList, DragonService dragonService) {
        super(dragonList, dragonService);
    }

    @Override
    public String execute(Message ignore) {
        getDragonList().clear();
        return getFormatter().formatBooleanOperation(true);
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoClear");
    }
}
