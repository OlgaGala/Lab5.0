package com.api.command;

import com.api.entity.Dragon;
import com.api.entity.DragonCharacter;
import com.api.message.MessageReq;
import com.api.service.DragonService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

@Getter @Setter
public class FilterLessThanCharacter extends Command {

    public FilterLessThanCharacter(Stack<Dragon> dragonList, DragonService dragonService) {
        super(dragonList, dragonService);
    }

    @Override
    public Stack<Dragon> execute(MessageReq message) {

        String character = getArg(message.getCommand());

        List<Dragon> resultList = getDragonList()
                .stream()
                .filter(d -> d.getCharacter() != null && d.getCharacter().compareTo(DragonCharacter.valueOf(character)) < 0)
                .collect(Collectors.toList());

        return (Stack<Dragon>) resultList;
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoFilterLessThanCharacter");
    }
}
