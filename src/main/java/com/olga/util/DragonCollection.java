package com.olga.util;

import com.olga.dragon.Color;
import com.olga.dragon.Dragon;
import com.olga.dragon.DragonCave;
import com.olga.dragon.DragonCharacter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Реализация Service
 * Класс предоставляет бизнес-логику для работы с коллекцией
 */
public class DragonCollection implements CollectionService {

    /**
     * CSV конвертер
     * Из этого класса мы получим коллекцию, которую мы модифицируем и передадим в этот же класс для сохранения
     */
    private final XMLParser converter;

    /**
     * Коллекция, которой мы управляем
     */
    private final Stack<Dragon> mDataSet;

    public DragonCollection(String fileName) throws Exception {
        this.converter = new XMLParser(fileName);

        // Получаем коллекцию из CSV конвертера
        this.mDataSet = converter.read();

        // После загрузки данных из файла, необходимо переназначить ID для всех элементов
        // Это защитит нас от ошибки, если пользователь самостоятельно изменил ID в файле
        // и некоторые ID повторяются
        settleIds();
    }

    @Override
    public String help() {
        return "help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "insert_at index {element} : добавить новый элемент в заданную позицию\n" +
                "remove_at index : удалить элемент, находящийся в заданной позиции коллекции (index)\n" +
                "shuffle : перемешать элементы коллекции в случайном порядке\n" +
                "remove_all_by_cave cave : удалить из коллекции все элементы, значение поля cave которого эквивалентно заданному\n" +
                "filter_less_than_character character : вывести элементы, значение поля character которых меньше заданного\n" +
                "filter_greater_than_color color : вывести элементы, значение поля color которых больше заданного";
    }

    @Override
    public String info() {
        String fileCreationDate = null;
        String elementType = null;

        try {
            BasicFileAttributes attr = Files.readAttributes(converter.getFile().toPath(), BasicFileAttributes.class);
            fileCreationDate = String.valueOf(attr.creationTime());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Field hashSet = this.getClass().getDeclaredField("mDataSet");
            elementType = String.valueOf(hashSet.getGenericType());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return new Formatter()
                .format("Type: %s\n" +
                        "Size: %d\n" +
                        "Creation Date: %s\n",
                        elementType, mDataSet.toArray().length, fileCreationDate)
                .toString();
    }

    @Override
    public String show() {
        if(mDataSet.size() == 0) {
            return "Коллекция пуста";
        } else {
            return formatOutput(mDataSet);
        }
    }

    @Override
    public String add(Dragon dragon) {
        mDataSet.add(dragon);
        return "Элемент был добавлен";
    }

    @Override
    public String update_id(Integer id, Dragon dragon) {
        Dragon src = null;
        for (Dragon p : mDataSet) {
            if (p.getId().equals(id)) {
                src = p;
            }
        }

        if (src != null) {
            dragon.setId(id);
            mDataSet.remove(src);
            mDataSet.add(dragon);
            return "Элемент с id " + id + " был обновлен";
        } else {
            return "Элемента с id " + id + " не существует";
        }
    }

    @Override
    public String remove_by_id(Integer id) {
        Iterator<Dragon> iterator = mDataSet.iterator();
        while (iterator.hasNext()) {
            if(iterator.next().getId() == id) {
                iterator.remove();
                return "Элемент удален";
            }
        }
        return "Элемента с таким id не существует";
    }

    @Override
    public String clear() {
        if(mDataSet.size() == 0) {
            return "Коллекция уже пуста";
        } else {
            mDataSet.clear();
            return "Коллекция была очищена";
        }
    }

    @Override
    public String save() {
        // Сохраняем коллекцию
        return converter.write(mDataSet);
    }

    @Override
    public void exit() {
        System.out.println("Выход...");
        System.exit(0);
    }

    @Override
    public String insert_at(Integer id, Dragon dragon) {
        try {
            mDataSet.insertElementAt(dragon, id);
            return "Элемент с индексом " + id + " успешно удален";
        } catch (Exception e) {
            return "Индекс лежит за пределами коллекции";
        }
    }

    @Override
    public String remove_at(Integer id) {
        try {
            mDataSet.remove(id);
            return "Элемент с индексом " + id + " успешно удален";
        } catch (Exception e) {
            return "Индекс лежит за пределами коллекции";
        }
    }

    @Override
    public String shuffle() {
        Collections.shuffle(mDataSet);
        return "Коллекция была перемешана";
    }

    @Override
    public String remove_all_by_cave(DragonCave cave) {
        mDataSet.removeIf(v -> v.getCave().equals(cave));
        return "Все подходящие объекты были удалены";
    }

    @Override
    public String filter_less_than_character(DragonCharacter character) {
        return formatOutput(
                mDataSet
                        .stream()
                        .filter(v -> v.getCharacter().compareTo(character) < 0)
                        .collect(Collectors.toCollection(Stack::new))
        );
    }

    @Override
    public String filter_greater_than_color(Color color) {
        return formatOutput(
                mDataSet
                        .stream()
                        .filter(v -> v.getColor().compareTo(color) > 0)
                        .collect(Collectors.toCollection(Stack::new))
        );
    }

    /**
     * Метод который переназначает id
     * Здесь мы просто присваиваем элементам id от 1 до бесконечности в порядке их добавления
     */
    private void settleIds() {
        int id = 0;
        for (Dragon vehicles : mDataSet) {
            vehicles.setId(++id);
        }
    }

    /**
     * Метод, который выводит коллекцию в более красивом виде
     */
    private String formatOutput(Collection<?> collection) {
        return collection
                .toString()
                .substring(1, collection.toString().length()-1)
                .replaceAll(", ", "\n");
    }
}
