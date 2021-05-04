package com.olga.command;

import com.olga.dragon.Dragon;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.Set;
import java.util.Stack;

public class Help extends Command<String> {

    public Help(Stack<Dragon> dragonList) {
        super(dragonList);
    }

    @Override
    public String execute() {

        Reflections reflections = new Reflections("com.olga.command");
        Set<Class<? extends Command>> classes = reflections.getSubTypesOf(Command.class);

        StringBuilder result = new StringBuilder("");

        classes.forEach(c -> {

            try {
                Constructor<? extends Command> constructor = c.getConstructor();
                Command command = constructor.newInstance();
                result.append(command.toString()).append("\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return result.toString();
    }

    @Override
    public String toString() {
        return super.toString() + ": " + "Вывести справку по доступным командам";
    }
}
