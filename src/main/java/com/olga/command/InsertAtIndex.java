package com.olga.command;

import com.olga.command.annotation.AttachedObj;
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
@AttachedObj
public class InsertAtIndex extends Command {

    private UserInput consoleUserInput;

    public InsertAtIndex(Stack<Dragon> dragonList, Messenger messenger) {
        super(dragonList, messenger);

        this.consoleUserInput = new ConsoleUserInput(messenger);
    }

    @Override
    public String execute(String index) throws Exception {

        Dragon dragon = consoleUserInput.enterElement();

        Set<ConstraintViolation<Dragon>> violations = getValidator().validate(dragon);

        if(violations.isEmpty()) {
            return getFormatter().formatBooleanOperation(
                    getDragonList().set(Integer.parseInt(index), dragon) != null,
                    getMessenger()
            );
        }

        violations.forEach(v -> System.err.println(v.getMessage()));
        return getFormatter().formatBooleanOperation(false, getMessenger());
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoInsertAtIndex");
    }
}
