package com.olga.command;

import com.olga.dragon.Dragon;
import com.olga.i18n.Messenger;
import com.olga.io.ConsoleUserInput;
import com.olga.io.UserInput;
import lombok.Getter;
import lombok.Setter;

import java.util.Stack;

@Getter @Setter
public class InsertAtIndex extends Command {

    private UserInput consoleUserInput;

    public InsertAtIndex(Stack<Dragon> dragonList, Messenger messenger) {
        super(dragonList, messenger);

        this.consoleUserInput = new ConsoleUserInput(messenger);
    }

    @Override
    public String execute(String index) throws Exception {

        Dragon dragon = consoleUserInput.enterElement();

        return getFormatter().formatBooleanOperation(
                getDragonList().set(Integer.parseInt(index), dragon) != null,
                getMessenger()
        );
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoInsertAtIndex");
    }
}
