package com.olga.command;

import com.olga.dragon.Dragon;
import com.olga.dragon.DragonCave;
import com.olga.io.ConsoleUserInput;
import com.olga.io.UserInput;
import lombok.Getter;
import lombok.Setter;

import java.util.Stack;

@Getter @Setter
public class RemoveAllByCave extends Command {

    private UserInput userInput;

    public RemoveAllByCave(Stack<Dragon> dragonList) {
        super(dragonList);

        this.userInput = new ConsoleUserInput();
    }

    @Override
    public String execute(String cave) throws Exception {

        DragonCave dragonCave = userInput.enterCave();

        return getFormatter().formatBooleanOperation(getDragonList().removeIf(d -> d.getCave().equals(dragonCave)));
    }

    @Override
    public String toString() {
        return super.toString() + ": " + "Удалить элементы с полем cave равным заданному";
    }
}