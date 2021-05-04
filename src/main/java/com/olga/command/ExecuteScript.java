package com.olga.command;

import com.olga.dragon.Dragon;
import lombok.Getter;
import lombok.Setter;

import java.util.Stack;

@Getter @Setter
public class ExecuteScript extends Command<Boolean> {

    private String fileName;

    public ExecuteScript(Stack<Dragon> dragonList, String fileName) {
        super(dragonList);

        this.fileName = fileName;
    }


    @Override
    public Boolean execute() {
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + ": " + "Считать и исполнить скрипт из файла";
    }
}
