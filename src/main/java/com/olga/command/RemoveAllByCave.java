package com.olga.command;

import com.olga.command.annotation.AttachedObj;
import com.olga.dragon.Dragon;
import com.olga.dragon.DragonCave;
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
@AttachedObj(type = DragonCave.class)
public class RemoveAllByCave extends Command {

    private UserInput userInput;

    public RemoveAllByCave(Stack<Dragon> dragonList) {
        super(dragonList);

        this.userInput = new ConsoleUserInput();
    }

    @Override
    public String execute(Message message) throws Exception {

        DragonCave dragonCave = message.getDragon().getCave();

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
