package com.olga.command;

import com.olga.command.manager.CommandManager;
import com.olga.dragon.Dragon;
import com.olga.i18n.Messenger;
import com.olga.io.FileUserInput;
import com.olga.io.UserInput;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

@Getter @Setter
public class ExecuteScript extends Command {

    private UserInput userInput;

    public ExecuteScript(Stack<Dragon> dragonList, Messenger messenger) {
        super(dragonList, messenger);
    }


    @Override
    public String execute(String fileName) throws Exception {
        return execute_script(fileName);
    }

    /**
     * Метод выполняет чтения файла-скрипта
     * Построчно считывает команды с файла и посылает их execute_command()
     * @param fileName - имя файла скрипта
     * @return - Результат работы всего скрипта
     * @throws Exception - В случае отсутствия файла или ошибок в скрипте
     */
    public String execute_script(String fileName) throws Exception {

        this.userInput = new FileUserInput(new Scanner(new File(fileName)));

        if(!new File(fileName).exists()) throw new IOException("Файл не найден");

        try(Scanner scanner = new Scanner(new File(fileName))) {

            // Перед выполнением скрипта меняем источник ввода объектов на файловый
            StringBuilder result = new StringBuilder();

            String response;
            while (scanner.hasNextLine()) {

                response = scanner.nextLine();

                // Обязательно нужно проверить условие зацикливания, чтобы скрипт не вызвал сам себя
                if(response.equals("execute_script " + fileName)) {
                    result.append("Нельзя вызывать файл из самого себя!" + "\n");
                } else {
                    result.append(new CommandManager().executeCommand(response)).append("\n");
                }
            }

            return result.toString();

        } catch (IOException e) {
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            throw new Exception("Скрипт некорректный");
        }
    }


    @Override
    public String toString() {
        return super.toString() + ": " + "Считать и исполнить скрипт из файла";
    }
}
