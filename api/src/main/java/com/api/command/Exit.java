package com.api.command;

import com.api.entity.Dragon;
import com.api.message.MessageReq;
import com.api.service.DragonService;

import java.util.Stack;

public class Exit extends Command {

    public Exit(Stack<Dragon> dragonList, DragonService dragonService) {
        super(dragonList, dragonService);
    }

    @Override
    public String execute(MessageReq ignore) {

        // Сохраняем коллекция перед выходом
        new Save(getDragonList(), getDragonService()).execute(null);

        System.exit(0);
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoExit");
    }
}
