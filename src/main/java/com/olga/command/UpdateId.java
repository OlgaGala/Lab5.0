package com.olga.command;

import com.olga.dragon.Dragon;
import lombok.Getter;
import lombok.Setter;

import java.util.Stack;

@Getter @Setter
public class UpdateId extends Command<Void> {

    private Integer id;
    private Dragon dragon;

    public UpdateId(Stack<Dragon> dragonList, Integer id, Dragon dragon) {
        super(dragonList);

        this.id = id;
        this.dragon = dragon;
    }

    @Override
    public Void execute() {
        getDragonList().forEach(d -> {
            if(d.getId().equals(id)) {
                d = dragon;
            }
        });

        return null;
    }

    @Override
    public String toString() {
        return super.toString() + ": " + "Обновить значение элемента с заданным ID";
    }
}
