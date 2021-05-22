package com.lab7.command;

import com.lab7.command.annotation.AttachedObj;
import com.lab7.dragon.Dragon;
import com.lab7.dragon.DragonCave;
import com.lab7.io.UserInput;
import com.lab7.message.Message;
import com.lab7.service.DragonService;
import lombok.Getter;
import lombok.Setter;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.Stack;

@Getter @Setter
@AttachedObj(type = DragonCave.class)
public class RemoveAllByCave extends Command {

    private UserInput userInput;

    public RemoveAllByCave(Stack<Dragon> dragonList, DragonService dragonService) {
        super(dragonList, dragonService);
    }

    @Override
    public String execute(Message message) throws Exception {

        DragonCave dragonCave = message.getDragon().getCave();

        Set<ConstraintViolation<DragonCave>> violations = getValidator().validate(dragonCave);

        if(violations.isEmpty()) {

            for (int i = 0; i < getDragonList().size(); i++) {
                if(getDragonList().get(i).getCave().equals(dragonCave)
                        && getDragonService().delete(getDragonList().get(i))) {
                    getDragonList().remove(getDragonList().get(i));
                }
            }

            return getFormatter().formatBooleanOperation(true);
        }

        violations.forEach(v -> System.err.println(v.getMessage()));
        return getFormatter().formatBooleanOperation(false);
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoRemoveAllByCave");
    }
}
