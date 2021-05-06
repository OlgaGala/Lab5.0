package com.olga.command;

import com.olga.dragon.Dragon;
import com.olga.print.api.Formatter;
import com.olga.print.implementation.FormatterImpl;
import lombok.*;

import java.util.Stack;

@AllArgsConstructor
@NoArgsConstructor
public abstract class Command {

    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    private Stack<Dragon> dragonList;

    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    private Formatter formatter;

    public Command(Stack<Dragon> dragonList) {
        this.dragonList = dragonList;
        this.formatter = new FormatterImpl();
    }

    public abstract String execute(String args) throws Exception;

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
