package com.olga.util;

import com.olga.dragon.Color;
import com.olga.dragon.Dragon;
import com.olga.dragon.DragonCave;
import com.olga.dragon.DragonCharacter;

/**
 * Интерфейс, описывающий поддерживаемый команды для управления коллекцией
 */
public interface CollectionService {

    /**
     * Сохранить коллекцию в файл
     * @return - строковый результат
     */
    String save();

    /**
     * Очистить коллекцию
     * @return - строковый результат
     */
    String clear();

    /**
     * Добавить новый элемент в коллекцию
     * @return - строковый результат
     */
    String add(Dragon dragon);

    /**
     * Обновить значение элемента коллекции, id которого равен заданному
     * @return - строковый результат
     */
    String update_id(Integer id, Dragon dragon);

    /**
     * Удалить элемент из коллекции по его id
     * @return - строковый результат
     */
    String remove_by_id(Integer id);


    /**
     * Вывести справку по доступным командам
     * @return - строковый результат
     */
    String help();

    /**
     * Вывести в стандартный поток вывода информацию о коллекции
     * @return - строковый результат
     */
    String info();

    /**
     * Вывести в стандартный поток вывода все элементы коллекции в строковом представлении
     * @return - строковый результат
     */
    String show();

    /**
     * Добавить новый элемент в заданную позицию
     * @param id - индекс
     * @return - результат работы метода
     */
    String insert_at(Integer id, Dragon dragon);

    /**
     * Удалить элемент, находящийся в заданной позиции коллекции (index)
     * @param id - индекс
     * @return - Результат работы метода
     */
    String remove_at(Integer id);

    /**
     * Перемешать элементы коллекции в случайном порядке
     * @return - результат работы метода
     */
    String shuffle();

    /**
     * Удалить из коллекции все элементы, значение поля cave которого эквивалентно заданному
     */
    String remove_all_by_cave(DragonCave cave);

    /**
     * Вывести элементы, значение поля character которых меньше заданного
     */
    String filter_less_than_character(DragonCharacter character);


    /**
     * Вывести элементы, значение поля color которых больше заданного
     */
    String filter_greater_than_color(Color color);

    /**
     * Закончить работу с коллекцией без сохранения в файл
     */
    void exit();
}
