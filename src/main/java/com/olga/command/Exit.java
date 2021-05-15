package com.olga.command;

import com.olga.dragon.Dragon;
import com.olga.i18n.Messenger;
import com.olga.message.Message;

import javax.security.sasl.SaslClient;
import java.util.Stack;

public class Exit extends Command {

    public Exit(Stack<Dragon> dragonList) {
        super(dragonList);
    }

    @Override
    public String execute(Message ignore) {

        // Сохраняем коллекция перед выходом
        new Save(getDragonList()).execute(null);

        System.exit(0);
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoExit");
    }
}
