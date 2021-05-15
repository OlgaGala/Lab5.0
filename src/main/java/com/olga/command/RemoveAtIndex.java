package com.olga.command;

import com.olga.dragon.Dragon;
import com.olga.i18n.Messenger;
import com.olga.message.Message;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Stack;

@Getter @Setter
public class RemoveAtIndex extends Command {

    public RemoveAtIndex(Stack<Dragon> dragonList) {
        super(dragonList);
    }

    @Override
    public String execute(Message message) {

        String index = getArg(message.getCommand());

        return getFormatter().formatBooleanOperation(
                getDragonList().remove(Integer.parseInt(index)) != null,
                getMessenger()
        );
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoRemoveAtIndex");
    }
}
