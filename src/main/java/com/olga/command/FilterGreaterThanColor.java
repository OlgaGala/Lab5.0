package com.olga.command;

import com.olga.dragon.Color;
import com.olga.dragon.Dragon;
import com.olga.i18n.Messenger;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

@Getter @Setter
public class FilterGreaterThanColor extends Command {

    public FilterGreaterThanColor(Stack<Dragon> dragonList, Messenger messenger) {
        super(dragonList, messenger);
    }

    @Override
    public String execute(String color) {

        List<Dragon> resultList = getDragonList()
                .stream()
                .filter(d -> d.getColor().equals(Color.valueOf(color)))
                .collect(Collectors.toList());

        return getFormatter().formatCollection(resultList);
    }

    @Override
    public String toString() {
        return super.toString() + ": " + "Вывести элементы, значение поля color которых больше заданного";
    }
}
