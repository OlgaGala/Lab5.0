package com.olga.command;

import com.olga.dragon.Dragon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Stack;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public abstract class Command<ReturnType> {

    private Stack<Dragon> dragonList;

    abstract ReturnType execute();

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
