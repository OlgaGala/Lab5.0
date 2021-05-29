package com.lab7.command;

import com.lab7.command.annotation.AttachedObj;
import com.lab7.entity.Dragon;
import com.lab7.message.Message;
import com.lab7.service.DragonService;
import lombok.Getter;
import lombok.Setter;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.Stack;

@Getter @Setter
@AttachedObj
public class UpdateId extends Command {

    public UpdateId(Stack<Dragon> dragonList, DragonService dragonService) {
        super(dragonList, dragonService);
    }

    @Override
    public String execute(Message message) {

        String id = getArg(message.getCommand());

        for (Dragon d: getDragonList()) {
            if(d.getId().equals(Integer.parseInt(id))) {
                try {
                    Dragon dragon = message.getDragon();
                    Set<ConstraintViolation<Dragon>> violations = getValidator().validate(dragon);

                    if(violations.isEmpty() && getDragonService().update(dragon, message.getUser())) {
                        d = dragon;
                        return getFormatter().formatBooleanOperation(true);
                    }
                    violations.forEach(v -> System.err.println(v.getMessage()));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return getFormatter().formatBooleanOperation(false);
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoUpdateId");
    }
}
