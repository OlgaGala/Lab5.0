package com.olga.command;

import com.olga.dragon.Dragon;
import com.olga.i18n.Messenger;
import com.olga.message.Message;

import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Show extends Command {

    public Show(Stack<Dragon> dragonList) {
        super(dragonList);
    }

    @Override
    public String execute(Message ignore) {
        return getFormatter().formatCollection(
                getDragonList()
                        .stream()
                        .sorted((o1, o2) -> Comparator.comparing(Dragon::getName).compare(o1, o2))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoShow");
    }
}
