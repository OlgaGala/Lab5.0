package com.server;

import com.api.command.manager.CommandManager;
import com.api.entity.Dragon;
import com.api.i18n.Messenger;
import com.api.i18n.MessengerFactory;
import com.api.print.api.Formatter;
import com.api.print.api.Printer;
import com.api.service.DragonService;
import com.api.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Stack;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ServerHelper {

    // Константы, служащие флагами при регистрации/авторизации пользователя
    public static final String SL = "success_login";
    public static final String FL = "failure_login";
    public static final String SR = "success_registration";
    public static final String FR = "failure_registration";

    private static final Messenger messenger = MessengerFactory.getMessenger();

    private Stack<Dragon> mDataSet;
    private CommandManager commandManager;
    private Formatter formatter;
    private Printer printer;
    private UserService userService;
    private DragonService dragonService;

    public void init() {
        commandManager.setFormatter(formatter);
        commandManager.setPrinter(printer);
        commandManager.setMDataSet(mDataSet);
        commandManager.setDragonService(dragonService);
    }

}
