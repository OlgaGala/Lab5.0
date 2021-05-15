package com.olga.client;

import com.olga.dragon.Dragon;
import com.olga.io.ConsoleUserInput;
import com.olga.io.UserInput;

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
