package com.olga.command;

import com.olga.dragon.Color;
import com.olga.dragon.Dragon;
import com.olga.i18n.Messenger;
import com.olga.message.Message;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

@Getter @Setter
public class FilterGreaterThanColor extends Command {

    public FilterGreaterThanColor(Stack<Dragon> dragonList) {
        super(dragonList);
    }

    @Override
    public String execute(Message message) {

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
