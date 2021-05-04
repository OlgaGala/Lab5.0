package com.olga.command;

import com.olga.dragon.Color;
import com.olga.dragon.Dragon;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

@Getter @Setter
public class FilterGreaterThanColor extends Command<List<Dragon>> {

    private Color color;

    public FilterGreaterThanColor(Stack<Dragon> dragonList, Color color) {
        super(dragonList);

        this.color = color;
    }

    @Override
    public List<Dragon> execute() {
        return getDragonList()
                .stream()
                .filter(d -> d.getColor().equals(color))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return super.toString() + ": " + "Вывести элементы, значение поля color которых больше заданного";
    }
}
