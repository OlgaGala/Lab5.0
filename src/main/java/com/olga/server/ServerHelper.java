package com.olga.server;

import com.olga.command.manager.CommandManager;
import com.olga.dragon.Dragon;
import com.olga.i18n.Messenger;
import com.olga.i18n.MessengerFactory;
import com.olga.print.api.Formatter;
import com.olga.print.api.Printer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Stack;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ServerHelper {

    private static final Messenger messenger = MessengerFactory.getMessenger();

    private Stack<Dragon> mDataSet;
    private CommandManager commandManager;
    private Formatter formatter;
    private Printer printer;

    public void init() {
        commandManager.setFormatter(formatter);
        commandManager.setPrinter(printer);
        commandManager.setMDataSet(mDataSet);
    }

}
