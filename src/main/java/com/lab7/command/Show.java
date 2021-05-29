package com.lab7.command;

import com.lab7.entity.Dragon;
import com.lab7.message.Message;
import com.lab7.service.DragonService;

import java.util.Comparator;
import java.util.Stack;
import java.util.stream.Collectors;

public class Show extends Command {

    public Show(Stack<Dragon> dragonList, DragonService dragonService) {
        super(dragonList, dragonService);
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
