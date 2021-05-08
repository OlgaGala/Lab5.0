package com.olga.command;

import com.olga.dragon.Dragon;
import com.olga.i18n.Messenger;
import com.olga.io.ConsoleUserInput;
import com.olga.io.UserInput;
import lombok.Getter;
import lombok.Setter;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.Stack;

@Getter @Setter
public class Add extends Command {

    private UserInput consoleUserInput;

    public Add(Stack<Dragon> dragonList, Messenger messenger) {
        super(dragonList, messenger);

        consoleUserInput = new ConsoleUserInput(messenger);

    }

    @Override
    public String execute(String ignore) throws Exception {

        Dragon dragon = consoleUserInput.enterElement();

        Set<ConstraintViolation<Dragon>> violations = getValidator().validate(dragon);

        if(violations.isEmpty()) {
            getDragonList().add(dragon);
            return getFormatter().formatBooleanOperation(true, getMessenger());
        }

        violations.forEach(v -> System.err.println(v.getMessage()));
        return getFormatter().formatBooleanOperation(false, getMessenger());
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoAdd");
    }
}
