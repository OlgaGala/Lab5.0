package com.olga.io;

import com.olga.dragon.*;

import java.util.Scanner;

/**
 * Класс, предоставляющий логику чтения элементов с консоли
 */
public class ConsoleUserInput extends UserInput {

    public ConsoleUserInput() {
        super(new Scanner(System.in));
    }

    /**
     * Вводя элемент с консоли, мы должны вводить каждое поле в цикле до тех пор, пока ввод не будет корректным.
     * В случае некорректного ввода - бросается исключение и программа снова просит нас ввести значение
     * @return - введенный объект
     */
    @Override
    public Dragon enterElement() throws Exception {

        String name;
        Coordinates coordinates;
        long age;
        Color color;
        DragonType type;
        DragonCharacter character;

        while (true) {
            System.out.println("Введите имя:");
            try {
                name = readName();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            System.out.println("Введите координаты (Double, int через пробел):");
            try {
                coordinates = readCoordinates();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            System.out.println("Введите возраст:");
            try {
                age = readAge();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            System.out.println("Введите цвет (RED, BLACK, BLUE, ORANGE):");
            try {
                color = readColor();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            System.out.println("Введите тип (WATER, AIR, FIRE):");
            try {
                type = readDragonType();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            System.out.println("Введите характер (CUNNING, CHAOTIC, FICKLE):");
            try {
                character = readDragonCharacter();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Введите NULL, чтобы не вводить характеристики пещеры (Или любое другое значение, чтобы продолжить):");
        String result = new Scanner(System.in).nextLine();

        return new Dragon(name, coordinates, age, color, type, character, result.equals("NULL") ? null : enterCave());
    }

    @Override
    public DragonCave enterCave() throws Exception {
        DragonCave cave = new DragonCave();

        while (true) {
            System.out.println("Введите глубину пещеры:");
            try {
                cave.setDepth(readDepth());
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            System.out.println("Введите количество богатства:");
            try {
                cave.setNumberOfTreasures(readNumberOfTreasures());
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return cave;
    }
}

