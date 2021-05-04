package com.olga.command;

import com.olga.dragon.Dragon;
import com.olga.io.XMLParser;
import lombok.Getter;
import lombok.Setter;

import java.util.Stack;

@Getter @Setter
public class Save extends Command<Void> {

    private String fileName;

    public Save(Stack<Dragon> dragonList, String fileName) {
        super(dragonList);

        this.fileName = fileName;
    }

    @Override
    public Void execute() {
        XMLParser.write(getDragonList(), fileName);

        return null;
    }

    @Override
    public String toString() {
        return super.toString() + ": " + "Сохранить коллекцию в файл";
    }
}
