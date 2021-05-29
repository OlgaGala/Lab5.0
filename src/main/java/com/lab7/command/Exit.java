package com.lab7.command;

import com.lab7.entity.Dragon;
import com.lab7.message.Message;
import com.lab7.service.DragonService;

import java.util.Stack;

public class Exit extends Command {

    public Exit(Stack<Dragon> dragonList, DragonService dragonService) {
        super(dragonList, dragonService);
    }

    @Override
    public String execute(Message ignore) {

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
