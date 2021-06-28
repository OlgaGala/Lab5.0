package com.api.command;

import com.api.command.annotation.AttachedObj;
import com.api.entity.Dragon;
import com.api.entity.DragonCave;
import com.api.io.UserInput;
import com.api.message.MessageReq;
import com.api.message.MessageReqObj;
import com.api.service.DragonService;
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
    public Stack<Dragon> execute(MessageReq message) throws Exception {

        DragonCave dragonCave = ((MessageReqObj) message).getDragon().getCave();

        Set<ConstraintViolation<DragonCave>> violations = getValidator().validate(dragonCave);

        if(violations.isEmpty()) {

            for (int i = 0; i < getDragonList().size(); i++) {
                if(getDragonList().get(i).getCave().equals(dragonCave)
                        && getDragonService().delete(getDragonList().get(i), message.getUser())) {
                    getDragonList().remove(getDragonList().get(i));
                }
            }
        }

        violations.forEach(v -> System.err.println(v.getMessage()));
        return getDragonList();
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoRemoveAllByCave");
    }
}
