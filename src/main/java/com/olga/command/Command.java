package com.olga.command;

import com.olga.dragon.Dragon;
import com.olga.exception.NoSuchCommandException;
import com.olga.i18n.Messenger;
import com.olga.i18n.MessengerFactory;
import com.olga.message.Message;
import com.olga.print.api.Formatter;
import com.olga.print.implementation.FormatterImpl;

import lombok.*;
import org.reflections.Reflections;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.Stack;

@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
public abstract class Command {

    private Messenger messenger = MessengerFactory.getMessenger();

    private Stack<Dragon> dragonList;

    private Formatter formatter;

    private Validator validator;

    public Command(Stack<Dragon> dragonList) {
        this.dragonList = dragonList;
        this.formatter = new FormatterImpl();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public abstract String execute(Message message) throws Exception;

    protected String getArg(String command) {
        return command.split(" ")[1];
    }

    public static Class<? extends Command> validateCommand(String commandName) {

        Reflections reflections = new Reflections("com.olga.command");
        Set<Class<? extends Command>> classes = reflections.getSubTypesOf(Command.class);

        for (Class<? extends Command> c: classes) {
            if(c.getSimpleName().equalsIgnoreCase(commandName)) {
                return c;
            }
        }

        throw new NoSuchCommandException("Такой команды не существует");
    }

    protected void settleIds() {
        int id = 0;
        for (Dragon d : dragonList) {
            d.setId(++id);
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
