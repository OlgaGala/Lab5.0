package com.api.command;

import com.api.command.annotation.AttachedObj;
import com.api.entity.Dragon;
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
@AttachedObj
public class InsertAtIndex extends Command {

    private UserInput consoleUserInput;

    public InsertAtIndex(Stack<Dragon> dragonList, DragonService dragonService) {
        super(dragonList, dragonService);
    }

    @Override
    public Stack<Dragon> execute(MessageReq message) throws Exception {

        String index = getArg(message.getCommand());

        Dragon dragon = ((MessageReqObj) message).getDragon();

        Set<ConstraintViolation<Dragon>> violations = getValidator().validate(dragon);

        if(violations.isEmpty()) {

            Dragon dragon1 = getDragonList().set(Integer.parseInt(index), dragon);
            getDragonService().update(dragon1, message.getUser());
        }

        violations.forEach(v -> System.err.println(v.getMessage()));
        return getDragonList();
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoInsertAtIndex");
    }
}
