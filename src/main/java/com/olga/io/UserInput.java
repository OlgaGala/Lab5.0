package com.olga.io;

import com.olga.dragon.*;
import com.olga.i18n.Messenger;
import lombok.AllArgsConstructor;

import java.util.Scanner;

/**
 * Класс, который отвечает за источник ввода элементов
 * Хранит логику ввода в зависимости от источника
 */
@AllArgsConstructor
public abstract class UserInput {

    private final Scanner in;
    public final Messenger messenger;

    /**
     * Логика ввода элемента. Метод нужно переопределить в суб-классах
     * @return - Введенный элемент
     * @throws Exception - исключение, возникающее в случае некорректного ввода
     */
    public abstract Dragon enterElement() throws Exception;

    /**
     * Ввод составного типа DragonCave. Метод нужно переопределить разным образом в зависимости от источника ввода (Файл, консоль)
     * @return - нужный объект
     * @throws Exception - в случае некорректного ввода
     */
    public abstract DragonCave enterCave() throws Exception;

    // Все последующие методы предоставляют логику ввода отдельных полей
    // Пользователь вводит строку, а метод преобразует ее в нужный тип
    // Методы также хранят логику ограничений значений
    // (Метод не допустит ввод значения -10, если поле должно быть больше нуля)
    public String readName() throws Exception {
        try {
            String name = in.nextLine();
            if(name.equals("")) throw new IllegalArgumentException();
            return name;
        } catch (Exception e) {
            throw new Exception(messenger.getMessage("invalidInput"));
        }
    }

    public Coordinates readCoordinates() throws Exception {
        try {
            String[] arr = (in.nextLine()).split(" ");
            Double x = Double.parseDouble(arr[0]);
            int y = Integer.parseInt(arr[1]);
            return new Coordinates(x,y);
        } catch (Exception e) {
            throw new Exception(messenger.getMessage("invalidInput"));
        }
    }

    public long readAge() throws Exception {
        try {
            long result = Long.parseLong(in.nextLine());
            if(result <= 0) throw new IllegalArgumentException(messenger.getMessage("inputGreaterZero"));
            return result;
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            throw new Exception(messenger.getMessage("invalidInput"));
        }
    }

    public Color readColor() throws Exception {
        try {
            String response = in.nextLine();
            Color organizationType = null;
            if(!response.equals("")) {
                organizationType = Color.valueOf(response);
            }
            return organizationType;
        } catch (Exception e) {
            throw new Exception(messenger.getMessage("invalidInput"));
        }
    }

    public DragonType readDragonType() throws Exception {
        try {
            String response = in.nextLine();
            DragonType organizationType;
            if(!response.equals("")) {
                organizationType = DragonType.valueOf(response);
            } else {
                throw new Exception(messenger.getMessage("notNullField"));
            }
            return organizationType;
        } catch (Exception e) {
            throw new Exception(messenger.getMessage("invalidInput"));
        }
    }

    public DragonCharacter readDragonCharacter() throws Exception {
        try {
            String response = in.nextLine();
            DragonCharacter organizationType;
            if(!response.equals("")) {
                organizationType = DragonCharacter.valueOf(response);
            } else {
                throw new Exception(messenger.getMessage("notNullField"));
            }
            return organizationType;
        } catch (Exception e) {
            throw new Exception(messenger.getMessage("invalidInput"));
        }
    }

    public long readDepth() throws Exception {
        try {
            return Long.parseLong(in.nextLine());
        } catch (Exception e) {
            throw new Exception(messenger.getMessage("invalidInput"));
        }
    }

    public Long readNumberOfTreasures() throws Exception {
        try {
            Long result = Long.parseLong(in.nextLine());
            if(result <= 0) {
                throw new IllegalArgumentException(messenger.getMessage("inputGreaterZero"));
            }
            return result;
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            throw new Exception(messenger.getMessage("invalidInput"));
        }
    }
}
