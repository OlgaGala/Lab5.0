package com.lab7.command.annotation;

import com.lab7.dragon.Dragon;
import com.lab7.io.ConsoleUserInput;
import com.lab7.io.UserInput;

import java.rmi.NoSuchObjectException;

public class AttachedObjFactory {

    private static final UserInput input = new ConsoleUserInput();

    public static Dragon newInstance(Class<?> c) throws Exception {

        switch (c.getSimpleName().toLowerCase()) {
            case "dragon": return input.enterElement();
            case "dragoncave": {

                Dragon dragon = new Dragon();
                dragon.setCave(input.enterCave());

                return dragon;
            }
            default: throw new NoSuchObjectException("Введенные данные некорректны");
        }
    }
}
