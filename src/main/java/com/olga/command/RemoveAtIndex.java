package com.olga.command;

import com.olga.dragon.Dragon;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Stack;

@Getter @Setter
public class RemoveAtIndex extends Command {

    public RemoveAtIndex(Stack<Dragon> dragonList) {
        super(dragonList);
    }

    @Override
    public String execute(String index) {
        return getFormatter().formatSingleElement(getDragonList().remove(Integer.parseInt(index)));
    }

    @Override
    public String toString() {
        return super.toString() + ": " + "Удалить элемент в заданной позиции";
    }
}
