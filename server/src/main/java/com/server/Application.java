package com.server;

import com.api.command.manager.CommandManager;
import com.api.dao.DragonDao;
import com.api.dao.UserDao;
import com.api.print.implementation.FormatterImpl;
import com.api.print.implementation.PrinterImpl;
import com.api.service.DragonService;
import com.api.service.UserService;

public class Application {
    public static void main(String[] args) throws Exception {
        // Т.к. мы не используем Spring контейнер, мы должны сами создавать все классы и внедрять их в бизнес-логику
        DragonService dragonService = new DragonService(new DragonDao());
        UserService userService = new UserService(new UserDao());

        new Server(ServerHelper.builder()
                .mDataSet(dragonService.findAll())
                .commandManager(new CommandManager())
                .formatter(new FormatterImpl())
                .printer(new PrinterImpl())
                .dragonService(dragonService)
                .userService(userService)
                .build()
        ).start();
    }
}
