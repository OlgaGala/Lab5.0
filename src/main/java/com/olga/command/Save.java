package com.olga.command;

import com.olga.dragon.Dragon;
import com.olga.io.XMLParser;
import lombok.Getter;
import lombok.Setter;

import java.util.Stack;

@Getter @Setter
public class Save extends Command {

    public Save(Stack<Dragon> dragonList) {
        super(dragonList);
    }

    @Override
    public String execute(String ignore) {

        XMLParser.write(getDragonList(), System.getenv("XML_FILE"));

        return null;
    }

    @Override
    public String toString() {
        return super.toString() + ": " + "Сохранить коллекцию в файл";
    }
}
