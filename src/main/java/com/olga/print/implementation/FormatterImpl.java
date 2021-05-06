package com.olga.print.implementation;

import com.olga.print.api.Formatter;

import java.util.Collection;

public class FormatterImpl implements Formatter {
    /**
     * Метод, который выводит коллекцию в более красивом виде
     */
    public String formatCollection(Collection<?> collection) {
        return collection
                .toString()
                .substring(1, collection.toString().length()-1)
                .replaceAll(", ", "\n");
    }

    public String formatSingleElement(Object object) {
        return "\n" + object.toString() + "\n";
    }

    public String formatBooleanOperation(boolean bool) {
        if(bool) {
            return "Операция выполнилась успешно";
        } else {
            return "Операция не выполнилась";
        }
    }
}
