package com.olga.command;

import com.olga.dragon.Dragon;
import lombok.Getter;
import lombok.Setter;

import java.util.Stack;

@Getter @Setter
public class Add extends Command<Void> {

    private Dragon dragon;

    public Add(Stack<Dragon> dragonList, Dragon dragon) {
        super(dragonList);

        this.dragon = dragon;
    }

    @Override
    public Void execute() {
        getDragonList().add(dragon);
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + ": " + "Добавить новый элемент в коллекцию";
    }
}
