package com.olga.command;

import com.olga.dragon.Dragon;
import com.olga.i18n.Messenger;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Stack;

@Getter @Setter
public class RemoveAtIndex extends Command {

    public RemoveAtIndex(Stack<Dragon> dragonList, Messenger messenger) {
        super(dragonList, messenger);
    }

    @Override
    public String execute(String index) {
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
