package com.olga.command;

import com.olga.dragon.Dragon;
import com.olga.dragon.DragonCave;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Stack;

@Getter @Setter
public class RemoveAllByCave extends Command<Boolean> {

    private DragonCave dragonCave;

    public RemoveAllByCave(Stack<Dragon> dragonList, DragonCave dragonCave) {
        super(dragonList);

        this.dragonCave = dragonCave;
    }

    @Override
    public Boolean execute() {
        return getDragonList().removeIf(d -> d.getCave().equals(dragonCave));
    }

    @Override
    public String toString() {
        return super.toString() + ": " + "Удалить элементы с полем cave равным заданному";
    }
}
