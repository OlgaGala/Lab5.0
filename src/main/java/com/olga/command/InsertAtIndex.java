package com.olga.command;

import com.olga.dragon.Dragon;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Stack;

@Getter @Setter
public class InsertAtIndex extends Command<Dragon> {

    private Dragon dragon;
    private int index;

    public InsertAtIndex(Stack<Dragon> dragonList, int index, Dragon dragon) {
        super(dragonList);

        this.dragon = dragon;
        this.index = index;
    }

    @Override
    public Dragon execute() {
        return getDragonList().set(index, dragon);
    }

    @Override
    public String toString() {
        return super.toString() + ": " + "Добавить элемент в заданную позицию";
    }
}
