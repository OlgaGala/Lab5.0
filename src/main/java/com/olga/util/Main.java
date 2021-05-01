package com.olga.util;

import com.olga.dragon.Color;
import com.olga.dragon.DragonCharacter;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Класс, предоставляющий пользовательский терминал для работы с коллекцией (через Service)
 * Все пользовательские команды поступают сюда, а затем вызывается соответствующи метод Service
 */
public class Main {

    /**
     * Класс управления коллекцией, которым мы будем управлять в терминале
     */
    private final CollectionService collectionService;

    /**
     * Источник ввода, от него зависит откуда такие методы как add, update и др. будут считывать элементы
     */
    private UserInput userInput;

    public Main(CollectionService collectionService) {
        this.collectionService = collectionService;
        this.userInput = new ConsoleUserInput();
    }

    /**
     * Метод, запускающий терминал
     * Непрерывно слушает пользовательский ввод, вызывает соответствующую команду и выводит результат
     */
    public void start() {
        System.out.println("Вас приветствует терминал управления списком пользователей.\n" +
                "Введите одну из команд для продолжения (help - список доступных команд)");

        Scanner sc = new Scanner(System.in);
        String response;
        while (!(response = sc.nextLine()).equals("exit")) {
            try {
                // Вызываем команду и выводим результат
                // В случае, если метод execute_command бросил исключение,
                // оно обрабатывается, а программа продолжает работу.
                String result = execute_command(response);
                System.out.println(result);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        // После того, как пользователь ввёл exit,
        // необходимо вызвать соответствующий метод Service и закончить выполнение программы
        collectionService.exit();
    }

    /**
     * Метод вызывает методы Service в соответствии с пользовательским вводом
     * @param response - команда + аргументы, которые ввел пользователь
     * @return - Результат выполнения
     * @throws Exception - Некоторые методы бросают Exception,
     * необходимо передать их на более высокий уровень и обработать уже там (В методе start())
     */
    public String execute_command(String response) throws Exception {

        // Разделяем пользовательский ввод на команду и аргументы
        String[] array = response.split(" ");
        String command = array[0];

        // Аргументов должно быть либо 0, либо 1
        String param = array.length > 1 ? array[1] : null;

        switch (command) {
            case "help": return collectionService.help();
            case "info": return collectionService.info();
            case "show": return collectionService.show();
            case "add": return collectionService.add(userInput.enterElement());
            case "clear": return collectionService.clear();
            case "save": return collectionService.save();
            case "shuffle": return collectionService.shuffle();

            case "insert_at": return isNumeric(param)
                    ? collectionService.insert_at(Integer.parseInt(param), userInput.enterElement())
                    : "Некорректные параметры для команды insert_at";

            case "remove_at": return isNumeric(param)
                    ? collectionService.remove_at(Integer.parseInt(param))
                    : "Некорректные параметры для команды remove_at";

            case "remove_all_by_cave":
                return collectionService.remove_all_by_cave(userInput.enterCave());

            case "filter_less_than_character":
                return collectionService.filter_less_than_character(DragonCharacter.valueOf(param));

            case "filter_greater_than_color":
                return collectionService.filter_greater_than_color(Color.valueOf(param));

            // В каждом случае мы проверяем валидность аргументов и,
            // либо вызываем метод, либо возвращаем результат о некорректном вводе
            case "update_id": return isNumeric(param)
                    ? collectionService.update_id(Integer.parseInt(param), userInput.enterElement())
                    : "Некорректные параметры для команды update_id";
            case "remove_by_id": return isNumeric(param)
                    ? collectionService.remove_by_id(Integer.parseInt(param))
                    : "Некорректные параметры для команды remove_by_id";
            case "execute_script": return param != null
                    ? execute_script(param) :
                    "Для выполнения необходимо имя файла";

            default: return "Некорректный ввод. Пожалуйста, введите команду еще раз";
        }
    }

    /**
     * Метод проверяет, является ли строка числом
     * @return - true, если является
     */
    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            // Здесь мы преобразуем строку в Double, так как это самый большой тип,
            // А также потому что любой числовой тип можно неявно преобразовать в Double
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            // В случае, если строка не является числом, то мы просто возвращаем false
            return false;
        }
        return true;
    }

    /**
     * Метод выполняет чтения файла-скрипта
     * Построчно считывает команды с файла и посылает их execute_command()
     * @param fileName - имя файла скрипта
     * @return - Результат работы всего скрипта
     * @throws Exception - В случае отсутствия файла или ошибок в скрипте
     */
    public String execute_script(String fileName) throws Exception {

        if(!new File(fileName).exists()) throw new IOException("Файл не найден");

        try(Scanner scanner = new Scanner(new File(fileName))) {

            // Перед выполнением скрипта меняем источник ввода объектов на файловый
            setFileSource(scanner);
            StringBuilder result = new StringBuilder();

            String response;
            while (scanner.hasNextLine()) {

                response = scanner.nextLine();

                // Обязательно нужно проверить условие зацикливания, чтобы скрипт не вызвал сам себя
                if(response.equals("execute_script " + fileName)) {
                    result.append("Нельзя вызывать файл из самого себя!" + "\n");
                } else {
                    result.append(execute_command(response)).append("\n");
                }
            }

            return result.toString();

        } catch (IOException e) {
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            throw new Exception("Скрипт некорректный");
        } finally {
            // В конце снова меняем источник ввода объектов на консольный
            setConsoleSource();
        }
    }


    /**
     * Метод, переназначающий источник ввода на файловый
     * После вызова этого метода, элементы будут считываться с файла, а не с консоли
     * @param in - Поток ввода
     */
    void setFileSource(Scanner in) {
        userInput = new FileUserInput(in);
    }

    /**
     * Метод, переназначающий источник ввода на консольный
     * После вызова этого метода, элементы будут считываться с консоли
     */
    void setConsoleSource() {
        userInput = new ConsoleUserInput();
    }

    public static void main(String[] args) {
        CollectionService collectionService = null;
        try {
            collectionService = new DragonCollection(System.getenv("XML_FILE"));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        new Main(collectionService).start();
    }
}
