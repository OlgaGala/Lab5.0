package com.lab7.command;

import com.lab7.dragon.Dragon;
import com.lab7.message.Message;
import com.lab7.service.DragonService;
import lombok.Getter;
import lombok.Setter;

import java.util.Stack;

@Getter @Setter
public class RemoveAtIndex extends Command {

    public RemoveAtIndex(Stack<Dragon> dragonList, DragonService dragonService) {
        super(dragonList, dragonService);
    }

    @Override
    public String execute(Message message) {

        int index = Integer.parseInt(getArg(message.getCommand()));

        for (int i = 0; i < getDragonList().size(); i++) {
            if(index == i && getDragonService().delete(getDragonList().get(i))) {
                getDragonList().remove(getDragonList().get(i));
                return getFormatter().formatBooleanOperation(true);
            }
        }

        return getFormatter().formatBooleanOperation(false);
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoRemoveAtIndex");
    }
}
