package com.api.command;

import com.api.entity.Dragon;
import com.api.message.MessageReq;
import com.api.service.DragonService;

import java.util.Comparator;
import java.util.Stack;
import java.util.stream.Collectors;

public class Show extends Command {

    public Show(Stack<Dragon> dragonList, DragonService dragonService) {
        super(dragonList, dragonService);
    }

    @Override
    public Stack<Dragon> execute(MessageReq ignore) {
        return (Stack<Dragon>) getDragonList()
                        .stream()
                        .sorted((o1, o2) -> Comparator.comparing(Dragon::getName).compare(o1, o2))
                        .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoShow");
    }
}
