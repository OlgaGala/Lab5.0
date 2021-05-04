package com.olga.print;

import java.util.Collection;

public class Formatter {
    /**
     * Метод, который выводит коллекцию в более красивом виде
     */
    public static String formatOutput(Collection<?> collection) {
        return collection
                .toString()
                .substring(1, collection.toString().length()-1)
                .replaceAll(", ", "\n");
    }
}
