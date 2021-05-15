package com.olga.command;

import com.olga.command.annotation.AttachedObj;
import com.olga.dragon.Dragon;
import com.olga.i18n.Messenger;
import com.olga.io.ConsoleUserInput;
import com.olga.io.UserInput;
import com.olga.message.Message;
import lombok.Getter;
import lombok.Setter;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.Stack;

@Getter @Setter
@AttachedObj
public class Add extends Command {

    private UserInput consoleUserInput;

    public Add(Stack<Dragon> dragonList) {
        super(dragonList);

        consoleUserInput = new ConsoleUserInput();

    }

    @Override
    public String execute(Message message) throws Exception {

        Dragon dragon = message.getDragon();

        Set<ConstraintViolation<Dragon>> violations = getValidator().validate(dragon);

        if(violations.isEmpty()) {
            getDragonList().add(dragon);
            settleIds();
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
