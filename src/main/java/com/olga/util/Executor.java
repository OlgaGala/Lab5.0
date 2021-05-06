package com.olga.util;

import com.olga.command.Exit;
import com.olga.command.manager.CommandManager;
import com.olga.dragon.Dragon;
import com.olga.i18n.Messenger;
import com.olga.print.api.Formatter;
import com.olga.print.api.Printer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Scanner;
import java.util.Stack;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Executor {

    private Stack<Dragon> mDataSet;
    private CommandManager commandManager;
    private Formatter formatter;
    private Printer printer;
    private Messenger messenger;

    private void init() {
        commandManager.setMessenger(messenger);
        commandManager.setFormatter(formatter);
        commandManager.setPrinter(printer);
        commandManager.setMDataSet(mDataSet);
    }

    public void start() {

        init();

        System.out.println("Вас приветствует терминал управления списком пользователей.\n" +
                "Введите одну из команд для продолжения (help - список доступных команд)");

        Scanner sc = new Scanner(System.in);
        String response;
        while (!(response = sc.nextLine()).equals("exit")) {
            try {
                // Вызываем команду и выводим результат
                // В случае, если метод execute_command бросил исключение,
                // оно обрабатывается, а программа продолжает работу.
                commandManager.executeUserCommand(response);

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        // После того, как пользователь ввёл exit,
        // необходимо вызвать соответствующий метод Service и закончить выполнение программы
        new Exit(null).execute(null);
    }
}
