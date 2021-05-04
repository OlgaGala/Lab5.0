package com.olga.command;

import com.olga.dragon.Dragon;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Stack;

@Getter @Setter
public class RemoveById extends Command<Boolean> {

    private Integer id;

    public RemoveById(Stack<Dragon> dragonList, Integer id) {
        super(dragonList);

        this.id = id;
    }

    @Override
    public Boolean execute() {
        return getDragonList().removeIf(d -> d.getId().equals(id));
    }

    @Override
    public String toString() {
        return super.toString() + ": " + "Удалить элемент, ID которого равен заданному";
    }
}
