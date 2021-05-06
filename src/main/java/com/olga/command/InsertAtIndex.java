package com.olga.command;

import com.olga.dragon.Dragon;
import com.olga.io.ConsoleUserInput;
import com.olga.io.UserInput;
import lombok.Getter;
import lombok.Setter;

import java.util.Stack;

@Getter @Setter
public class InsertAtIndex extends Command {

    private UserInput consoleUserInput;

    public InsertAtIndex(Stack<Dragon> dragonList) {
        super(dragonList);

        this.consoleUserInput = new ConsoleUserInput();
    }

    @Override
    public String execute(String index) throws Exception {

        Dragon dragon = consoleUserInput.enterElement();

        return getFormatter().formatSingleElement(getDragonList().set(Integer.parseInt(index), dragon));
    }

    @Override
    public String toString() {
        return super.toString() + ": " + "Добавить элемент в заданную позицию";
    }
}
