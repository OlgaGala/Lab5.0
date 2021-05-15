package com.olga.command;

import com.olga.dragon.Dragon;
import com.olga.i18n.Messenger;
import com.olga.message.Message;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Stack;

@Getter @Setter
public class RemoveById extends Command {

    public RemoveById(Stack<Dragon> dragonList) {
        super(dragonList);
    }

    @Override
    public String execute(Message message) {
        return getFormatter().formatBooleanOperation(
                getDragonList()
                        .removeIf(d -> d.getId().equals(Integer.parseInt(getArg(message.getCommand())))),
                getMessenger()
        );
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoRemoveById");
    }
}
