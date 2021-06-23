package com.api.command.annotation;

import com.api.entity.Dragon;
import com.api.entity.User;
import com.api.io.ConsoleUserInput;
import com.api.io.UserInput;

import java.rmi.NoSuchObjectException;

public class AttachedObjFactory {

    private static final UserInput input = new ConsoleUserInput();

    public static Dragon newInstance(Class<?> c, User user) throws Exception {

        switch (c.getSimpleName().toLowerCase()) {
            case "dragon": return input.enterElement(user);
            case "dragoncave": {

                Dragon dragon = new Dragon();
                dragon.setCave(input.enterCave());

                return dragon;
            }
            default: throw new NoSuchObjectException("Введенные данные некорректны");
        }
    }
}
