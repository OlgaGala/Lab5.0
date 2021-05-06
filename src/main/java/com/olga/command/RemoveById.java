package com.olga.command;

import com.olga.dragon.Dragon;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Stack;

@Getter @Setter
public class RemoveById extends Command {

    public RemoveById(Stack<Dragon> dragonList) {
        super(dragonList);
    }

    @Override
    public String execute(String id) {
        return getFormatter().formatBooleanOperation(getDragonList().removeIf(d -> d.getId().equals(Integer.parseInt(id))));
    }

    @Override
    public String toString() {
        return super.toString() + ": " + "Удалить элемент, ID которого равен заданному";
    }
}
