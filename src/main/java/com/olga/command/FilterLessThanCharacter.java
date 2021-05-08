package com.olga.command;

import com.olga.dragon.Dragon;
import com.olga.dragon.DragonCharacter;
import com.olga.i18n.Messenger;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

@Getter @Setter
public class FilterLessThanCharacter extends Command {

    public FilterLessThanCharacter(Stack<Dragon> dragonList, Messenger messenger) {
        super(dragonList, messenger);
    }

    @Override
    public String execute(String character) {

        List<Dragon> resultList = getDragonList()
                .stream()
                .filter(d -> d.getCharacter().equals(DragonCharacter.valueOf(character)))
                .collect(Collectors.toList());

        return getFormatter().formatCollection(resultList);
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoFilterLessThanCharacter");
    }
}
