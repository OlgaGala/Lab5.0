package com.olga.command;

import com.olga.dragon.Dragon;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Stack;

@Getter @Setter
public class RemoveAtIndex extends Command<Dragon> {

    private int index;

    public RemoveAtIndex(Stack<Dragon> dragonList, int index) {
        super(dragonList);

        this.index = index;
    }

    @Override
    public Dragon execute() {
        return getDragonList().remove(index);
    }

    @Override
    public String toString() {
        return super.toString() + ": " + "Удалить элемент в заданной позиции";
    }
}
