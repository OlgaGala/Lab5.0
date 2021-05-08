package com.olga.command;

import com.olga.dragon.Dragon;
import com.olga.i18n.Messenger;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Stack;

@Getter @Setter
public class RemoveById extends Command {

    public RemoveById(Stack<Dragon> dragonList, Messenger messenger) {
        super(dragonList, messenger);
    }

    @Override
    public String execute(String id) {
        return getFormatter().formatBooleanOperation(
                getDragonList().removeIf(d -> d.getId().equals(Integer.parseInt(id))),
                getMessenger()
        );
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoRemoveById");
    }
}
