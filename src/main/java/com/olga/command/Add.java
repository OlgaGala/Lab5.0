package com.olga.command;

import com.olga.dragon.Dragon;
import com.olga.io.ConsoleUserInput;
import com.olga.io.UserInput;
import lombok.Getter;
import lombok.Setter;

import java.util.Stack;

@Getter @Setter
public class Add extends Command {

    private UserInput consoleUserInput;

    public Add(Stack<Dragon> dragonList) {
        super(dragonList);

        consoleUserInput = new ConsoleUserInput();

    }

    @Override
    public String execute(String ignore) throws Exception {

        Dragon dragon = consoleUserInput.enterElement();
        getDragonList().add(dragon);

        return null;
    }

    @Override
    public String toString() {
        return super.toString() + ": " + "Добавить новый элемент в коллекцию";
    }
}
