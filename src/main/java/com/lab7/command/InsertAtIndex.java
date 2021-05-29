package com.lab7.command;

import com.lab7.command.annotation.AttachedObj;
import com.lab7.entity.Dragon;
import com.lab7.io.UserInput;
import com.lab7.message.Message;
import com.lab7.service.DragonService;
import lombok.Getter;
import lombok.Setter;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.Stack;

@Getter @Setter
@AttachedObj
public class InsertAtIndex extends Command {

    private UserInput consoleUserInput;

    public InsertAtIndex(Stack<Dragon> dragonList, DragonService dragonService) {
        super(dragonList, dragonService);
    }

    @Override
    public String execute(Message message) throws Exception {

        String index = getArg(message.getCommand());

        Dragon dragon = message.getDragon();

        Set<ConstraintViolation<Dragon>> violations = getValidator().validate(dragon);

        if(violations.isEmpty()) {

            Dragon dragon1 = getDragonList().set(Integer.parseInt(index), dragon);

            if(dragon1 != null && getDragonService().update(dragon1, message.getUser())) {
                return getFormatter().formatBooleanOperation(true);
            } else {
                return getFormatter().formatBooleanOperation(false);
            }
        }

        violations.forEach(v -> System.err.println(v.getMessage()));
        return getFormatter().formatBooleanOperation(false);
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoInsertAtIndex");
    }
}
