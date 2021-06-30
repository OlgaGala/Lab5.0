package com.api.command;

import com.api.entity.Dragon;
import com.api.message.MessageReq;
import com.api.service.DragonService;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.Set;
import java.util.Stack;

public class Help extends Command {

    public Help(Stack<Dragon> dragonList, DragonService dragonService) {
        super(dragonList, dragonService);
    }

    @Override
    public String execute(MessageReq ignore) {

        Reflections reflections = new Reflections("com.api");
        Set<Class<? extends Command>> classes = reflections.getSubTypesOf(Command.class);

        StringBuilder result = new StringBuilder("");

        classes.forEach(c -> {

            try {
                Constructor<? extends Command> constructor = c.getConstructor(Stack.class, DragonService.class);
                Command command = constructor.newInstance(getDragonList(), getDragonService());
                result.append(command).append("\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return result.toString();
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoHelp");
    }
}
