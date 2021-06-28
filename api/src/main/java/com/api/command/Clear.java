package com.api.command;

import com.api.entity.Dragon;
import com.api.message.MessageReq;
import com.api.service.DragonService;

import java.util.Stack;

public class Clear extends Command {

    public Clear(Stack<Dragon> dragonList, DragonService dragonService) {
        super(dragonList, dragonService);
    }

    @Override
    public Stack<Dragon> execute(MessageReq ignore) {
        getDragonList().clear();
        return getDragonList();
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoClear");
    }
}
