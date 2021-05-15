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
public class UpdateId extends Command {

    private UserInput userInput;

    public UpdateId(Stack<Dragon> dragonList) {
        super(dragonList);

        userInput = new ConsoleUserInput();
    }

    @Override
    public String execute(Message message) {

        String id = getArg(message.getCommand());

        for (Dragon d: getDragonList()) {
            if(d.getId().equals(Integer.parseInt(id))) {
                try {
                    Dragon dragon = message.getDragon();
                    Set<ConstraintViolation<Dragon>> violations = getValidator().validate(dragon);

                    if(violations.isEmpty()) {
                        d = dragon;
                        return getFormatter().formatBooleanOperation(true, getMessenger());
                    }
                    violations.forEach(v -> System.err.println(v.getMessage()));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return getFormatter().formatBooleanOperation(false, getMessenger());
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoUpdateId");
    }
}
