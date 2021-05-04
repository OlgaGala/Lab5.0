package com.olga.command;

import com.olga.dragon.Dragon;
import com.olga.dragon.DragonCharacter;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

@Getter @Setter
public class FilterLessThanCharacter extends Command<List<Dragon>> {

    private DragonCharacter dragonCharacter;

    public FilterLessThanCharacter(Stack<Dragon> dragonList, DragonCharacter dragonCharacter) {
        super(dragonList);

        this.dragonCharacter = dragonCharacter;
    }

    @Override
    public List<Dragon> execute() {
        return getDragonList()
                .stream()
                .filter(d -> d.getCharacter().equals(dragonCharacter))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return super.toString() + ": " + "Вывести элементы, значение поля character которых меньше заданного";
    }
}
