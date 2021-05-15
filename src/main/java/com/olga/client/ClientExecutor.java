package com.olga.client;

import com.olga.command.Command;
import com.olga.command.annotation.AttachedObj;
import com.olga.dragon.Dragon;
import com.olga.message.Message;
import com.olga.print.api.Printer;
import com.olga.print.implementation.PrinterImpl;
import lombok.AllArgsConstructor;

import java.util.Scanner;

@AllArgsConstructor
public class ClientExecutor {

    private final Client client;
    private final Printer printer;

    public void start() {

        System.out.println("Приветствие");

        Scanner sc = new Scanner(System.in);
        String response;
        while (!(response = sc.nextLine()).equals("exit")) {
            try {
                // Вызываем команду и выводим результат
                // В случае, если метод execute_command бросил исключение,
                // оно обрабатывается, а программа продолжает работу.
                if (response.equals("")) {
                    System.out.println("Пожалуйста, введите корректные данные");
                } else {
                    String[] array = response.split(" ");
                    String commandName = array[0];

                    Message message = client.prepareRequest(response, validateAnnotation(Command.validateCommand(commandName)));
                    Message result = client.sendRequest(message);

                    printer.printResult(result.getResult());
                }

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        client.stop("Программа завершилась успешно");
    }

    private Dragon validateAnnotation(Class<? extends Command> c) throws Exception {

        if(c.isAnnotationPresent(AttachedObj.class)) {
            AttachedObj attachedObj = c.getAnnotation(AttachedObj.class);
            return AttachedObjFactory.newInstance(attachedObj.type());
        }

        return null;
    }

    public static void main(String[] args) {
        new ClientExecutor(new Client(), new PrinterImpl()).start();
    }
}
