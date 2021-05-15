package com.olga.command.manager;

import com.olga.command.Command;
import com.olga.command.ExecuteScript;
import com.olga.dragon.Dragon;
import com.olga.i18n.Messenger;
import com.olga.i18n.MessengerFactory;
import com.olga.message.Message;
import com.olga.print.api.Formatter;
import com.olga.print.api.Printer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.Set;
import java.util.Stack;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CommandManager {

    private static Messenger messenger = MessengerFactory.getMessenger();

    private Formatter formatter;
    private Printer printer;
    private Stack<Dragon> mDataSet;

    public Message executeCommand(Message message) throws Exception {

        Reflections reflections = new Reflections("com.olga.command");
        Set<Class<? extends Command>> classes = reflections.getSubTypesOf(Command.class);


        String commandName = message.getCommand().split(" ")[0];

        final Command[] command = new Command[1];
        classes.forEach(c -> {
            if(c.getSimpleName().equalsIgnoreCase(commandName)) {
                try {
                    if(commandName.equalsIgnoreCase("executescript")) {
                        Constructor<ExecuteScript> constructor = ExecuteScript.class.getConstructor(Stack.class);

                        ExecuteScript executeScript = constructor.newInstance(mDataSet);
                        executeScript.setCommandManager(this);

                        command[0] = executeScript;

                    } else {
                        Constructor<? extends Command> constructor = c.getConstructor(Stack.class);
                        command[0] = constructor.newInstance(mDataSet);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        message.setResult(
                command[0] != null
                        ? command[0].execute(message)
                        : messenger.getMessage("noSuchCommand")
        );

        return message;
    }
}
