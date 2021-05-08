package com.olga.util;

import com.olga.command.manager.CommandManager;
import com.olga.dragon.Dragon;
import com.olga.i18n.Language;
import com.olga.i18n.Messenger;
import com.olga.print.api.Formatter;
import com.olga.print.api.Printer;
import lombok.NoArgsConstructor;

import java.util.Stack;

@NoArgsConstructor
public class ExecutorBuilder {

    private Stack<Dragon> mDataSet;
    private CommandManager commandManager;
    private Formatter formatter;
    private Printer printer;
    private Messenger messenger;

    public ExecutorBuilder setDataSet(Stack<Dragon> mDataSet) {
        this.mDataSet = mDataSet;
        return this;
    }

    public ExecutorBuilder setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
        return this;
    }

    public ExecutorBuilder setFormatter(Formatter formatter) {
        this.formatter = formatter;
        return this;
    }

    public ExecutorBuilder setPrinter(Printer printer) {
        this.printer = printer;
        return this;
    }

    public ExecutorBuilder setMessenger(Messenger messenger) {
        this.messenger = messenger;
        return this;
    }

    public Executor build() {
        return new Executor(
                mDataSet,
                commandManager,
                formatter,
                printer,
                messenger
        );
    }

}
