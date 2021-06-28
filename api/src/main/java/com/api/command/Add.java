package com.api.command;

import com.api.command.annotation.AttachedObj;
import com.api.entity.Dragon;
import com.api.io.ConsoleUserInput;
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
public class Add extends Command {

    private UserInput consoleUserInput;

    public Add(Stack<Dragon> dragonList, DragonService dragonService) {
        super(dragonList, dragonService);

        consoleUserInput = new ConsoleUserInput();

    }

    @Override
    public Stack<Dragon> execute(MessageReq message) throws Exception {

        Dragon dragon = ((MessageReqObj) message).getDragon();

        Set<ConstraintViolation<Dragon>> violations = getValidator().validate(dragon);

        if(violations.isEmpty()) {

            if(getDragonService().save(dragon, message.getUser()) != null) {
                getDragonList().add(dragon);
                settleIds();
            }
        }

        violations.forEach(v -> System.err.println(v.getMessage()));
        return getDragonList();
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoAdd");
    }
}
