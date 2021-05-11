package com.olga.client;

import com.olga.dragon.Dragon;
import com.olga.i18n.Messenger;
import com.olga.i18n.MessengerImpl;
import com.olga.io.ConsoleUserInput;
import com.olga.io.UserInput;

import java.rmi.NoSuchObjectException;
import java.util.ResourceBundle;

public class AttachedObjFactory {

    private static final UserInput input = new ConsoleUserInput(
            new MessengerImpl(ResourceBundle.getBundle("text", Messenger.initLang())));

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
