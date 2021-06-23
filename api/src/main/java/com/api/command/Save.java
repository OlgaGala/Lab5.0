package com.api.command;

import com.api.entity.Dragon;
import com.api.message.MessageReq;
import com.api.service.DragonService;
import lombok.Getter;
import lombok.Setter;

import java.util.Stack;

@Getter @Setter
public class Save extends Command {

    public Save(Stack<Dragon> dragonList, DragonService dragonService) {
        super(dragonList, dragonService);
    }

    @Override
    public String execute(MessageReq ignore) {

        getDragonService().deleteAll();
        getDragonService().saveAll(getDragonList());

        return getFormatter().formatBooleanOperation(true);
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoSave");
    }
}
