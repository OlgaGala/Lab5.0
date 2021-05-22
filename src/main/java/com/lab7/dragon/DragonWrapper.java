package com.lab7.dragon;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.io.Serializable;
import java.util.Stack;

/**
 * Класс-обертка для коллекции объектов.
 * Нужен чтобы корректно записать коллекцию в XML файл
 */
public class DragonWrapper implements Serializable {

    private Stack<Dragon> dragon;

    @JacksonXmlElementWrapper(useWrapping = false)
    public void setDragon(Stack<Dragon> mDataSet) {
        this.dragon = mDataSet;
    }

    public Stack<Dragon> getDragon() {
        return dragon;
    }
}