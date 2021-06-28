package com.api.command.manager;

import com.api.command.Command;
import com.api.command.ExecuteScript;
import com.api.command.annotation.AttachedObj;
import com.api.command.annotation.AttachedObjFactory;
import com.api.entity.Dragon;
import com.api.entity.User;
import com.api.i18n.Messenger;
import com.api.i18n.MessengerFactory;
import com.api.message.MessageReq;
import com.api.message.MessageResp;
import com.api.print.api.Formatter;
import com.api.print.api.Printer;
import com.api.service.DragonService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.Locale;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CommandManager {

    private final static Logger logger = LoggerFactory.getLogger(CommandManager.class);

    private static Messenger messenger = MessengerFactory.getMessenger();

    private Formatter formatter;
    private Printer printer;
    private Stack<Dragon> mDataSet;
    private DragonService dragonService;

    // Используем ReadWriteLock для обеспечения потокобезопасности использования коллекции users
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    public MessageResp executeCommand(MessageReq message) throws Exception {

        MessageResp messageResult = new MessageResp();

        Reflections reflections = new Reflections("com.api");
        Set<Class<? extends Command>> classes = reflections.getSubTypesOf(Command.class);

        logger.info("Execute command: " + message.getCommand());

        String commandName = message.getCommand().split(" ")[0];

        final Command[] command = new Command[1];
        classes.forEach(c -> {
            if(c.getSimpleName().equalsIgnoreCase(commandName)) {
                try {
                    if(commandName.equalsIgnoreCase("executescript")) {
                        Constructor<ExecuteScript> constructor = ExecuteScript.class.getConstructor(Stack.class, DragonService.class);

                        ExecuteScript executeScript = constructor.newInstance(mDataSet, dragonService);
                        executeScript.setCommandManager(this);

                        command[0] = executeScript;

                    } else {
                        Constructor<? extends Command> constructor = c.getConstructor(Stack.class, DragonService.class);
                        command[0] = constructor.newInstance(mDataSet, dragonService);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Блокируем операцию, чтобы другие потоки не могли получить доступ во время записи
        writeLock.lock();
        try {
            messageResult.setResult(
                    command[0] != null
                            ? command[0].execute(message)
                            : messenger.getMessage("noSuchCommand")
            );
        } finally {
            writeLock.unlock();
        }


        return messageResult;
    }

    public static String[] getCommandNames() {
        Reflections reflections = new Reflections("com.api");
        Set<Class<? extends Command>> classes = reflections.getSubTypesOf(Command.class);
        return classes.stream().map(c -> c.getSimpleName().toLowerCase(Locale.ROOT)).toArray(String[]::new);
    }

    public static Dragon validateAnnotation(Class<? extends Command> c, User user) throws Exception {

        if(c.isAnnotationPresent(AttachedObj.class)) {
            AttachedObj attachedObj = c.getAnnotation(AttachedObj.class);

            return AttachedObjFactory.newInstance(attachedObj.type(), user);
        }
        return null;
    }
}
