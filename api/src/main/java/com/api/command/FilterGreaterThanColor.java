package com.api.command;

import com.api.entity.Color;
import com.api.entity.Dragon;
import com.api.message.MessageReq;
import com.api.service.DragonService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

@Getter @Setter
public class FilterGreaterThanColor extends Command {

    public FilterGreaterThanColor(Stack<Dragon> dragonList, DragonService dragonService) {
        super(dragonList, dragonService);
    }

    @Override
    public String execute(MessageReq message) {

        String color = getArg(message.getCommand());

        List<Dragon> resultList = getDragonList()
                .stream()
                .filter(d -> d.getColor() != null && d.getColor().compareTo(Color.valueOf(color)) > 0)
                .collect(Collectors.toList());

        return getFormatter().formatCollection(resultList);
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoFilterGreaterThanColor");
    }
}
