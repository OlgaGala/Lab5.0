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
public class UpdateId extends Command {

    private UserInput userInput;

    public UpdateId(Stack<Dragon> dragonList, Messenger messenger) {
        super(dragonList, messenger);

        userInput = new ConsoleUserInput(messenger);
    }

    @Override
    public String execute(String id) {

        for (Dragon d: getDragonList()) {
            if(d.getId().equals(Integer.parseInt(id))) {
                try {
                    Dragon dragon = userInput.enterElement();
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
