package com.olga.command;

import com.olga.dragon.Dragon;
import com.olga.dragon.DragonCave;
import com.olga.i18n.Messenger;
import com.olga.io.ConsoleUserInput;
import com.olga.io.UserInput;
import lombok.Getter;
import lombok.Setter;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.Stack;

@Getter @Setter
public class RemoveAllByCave extends Command {

    private UserInput userInput;

    public RemoveAllByCave(Stack<Dragon> dragonList, Messenger messenger) {
        super(dragonList, messenger);

        this.userInput = new ConsoleUserInput(messenger);
    }

    @Override
    public String execute(String cave) throws Exception {

        DragonCave dragonCave = userInput.enterCave();

        Set<ConstraintViolation<DragonCave>> violations = getValidator().validate(dragonCave);

        if(violations.isEmpty()) {
            return getFormatter().formatBooleanOperation(
                    getDragonList().removeIf(d -> d.getCave().equals(dragonCave)),
                    getMessenger()
            );
        }

        violations.forEach(v -> System.err.println(v.getMessage()));
        return getFormatter().formatBooleanOperation(false, getMessenger());
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoRemoveAllByCave");
    }
}
