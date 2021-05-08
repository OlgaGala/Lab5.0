package com.olga.command;

import com.olga.dragon.Dragon;
import com.olga.i18n.Messenger;
import com.olga.io.ConsoleUserInput;
import com.olga.io.UserInput;
import lombok.Getter;
import lombok.Setter;

import java.util.Stack;

@Getter @Setter
public class UpdateId extends Command {

    private UserInput userInput;

    public UpdateId(Stack<Dragon> dragonList, Messenger messenger) {
        super(dragonList, messenger);

        userInput = new ConsoleUserInput();
    }

    @Override
    public String execute(String id) {

        boolean result = false;

        for (Dragon d: getDragonList()) {
            if(d.getId().equals(Integer.parseInt(id))) {
                try {
                    d = userInput.enterElement();
                    result = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return getFormatter().formatBooleanOperation(result, getMessenger());
    }

    @Override
    public String toString() {
        return super.toString() + ": " + "Обновить значение элемента с заданным ID";
    }
}
