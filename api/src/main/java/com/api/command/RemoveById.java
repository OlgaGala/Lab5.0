package com.api.command;

import com.api.entity.Dragon;
import com.api.message.MessageReq;
import com.api.service.DragonService;
import lombok.Getter;
import lombok.Setter;

import java.util.Stack;

@Getter @Setter
public class RemoveById extends Command {

    public RemoveById(Stack<Dragon> dragonList, DragonService dragonService) {
        super(dragonList, dragonService);
    }

    @Override
    public Stack<Dragon> execute(MessageReq message) {

        getDragonList()
                .stream()
                .filter(d -> d.getId().equals(Integer.parseInt(message.getCommand().split(" ")[1])))
                .findFirst()
                .ifPresent(dragon -> getDragonService().delete(dragon, message.getUser()));

        return getDragonList();
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoRemoveById");
    }
}
