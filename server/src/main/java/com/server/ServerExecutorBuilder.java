package com.server;

import com.api.command.manager.CommandManager;
import com.api.entity.Dragon;
import com.api.print.api.Formatter;
import com.api.print.api.Printer;
import com.api.service.DragonService;
import com.api.service.UserService;
import lombok.NoArgsConstructor;

import java.util.Stack;

@NoArgsConstructor
public class ServerExecutorBuilder {

    private UserService userService;
    private DragonService dragonService;

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

    public ServerExecutorBuilder setDragonService(DragonService dragonService) {
        this.dragonService = dragonService;
        return this;
    }

    public ServerExecutorBuilder setUserDao(UserService userService) {
        this.userService = userService;
        return this;
    }

    public ServerHelper build() {
        return new ServerHelper(
                mDataSet,
                commandManager,
                formatter,
                printer,
                userService,
                dragonService
        );
    }
}
