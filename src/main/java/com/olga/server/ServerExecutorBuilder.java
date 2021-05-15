package com.olga.server;

import com.olga.command.manager.CommandManager;
import com.olga.dragon.Dragon;
import com.olga.print.api.Formatter;
import com.olga.print.api.Printer;
import lombok.NoArgsConstructor;

import java.util.Stack;

@NoArgsConstructor
public class ServerExecutorBuilder {

    private Stack<Dragon> mDataSet;
    private CommandManager commandManager;
    private Formatter formatter;
    private Printer printer;

    public ServerExecutorBuilder setDataSet(Stack<Dragon> mDataSet) {
        this.mDataSet = mDataSet;
        return this;
    }

    public ServerExecutorBuilder setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
        return this;
    }

    public ServerExecutorBuilder setFormatter(Formatter formatter) {
        this.formatter = formatter;
        return this;
    }

    public ServerExecutorBuilder setPrinter(Printer printer) {
        this.printer = printer;
        return this;
    }

    public ServerHelper build() {
        return new ServerHelper(
                mDataSet,
                commandManager,
                formatter,
                printer
        );
    }
}
