package com.olga.command;

import com.olga.dragon.Dragon;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Formatter;
import java.util.Stack;

@Getter @Setter
public class Info extends Command<String> {

    private String fileName;

    public Info(Stack<Dragon> dragonList, String fileName) {
        super(dragonList);

        this.fileName = fileName;
    }

    @Override
    public String execute() {
        String fileCreationDate = null;
        String elementType = null;

        try {
            BasicFileAttributes attr = Files.readAttributes(new File(fileName).toPath(), BasicFileAttributes.class);
            fileCreationDate = String.valueOf(attr.creationTime());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Field hashSet = this.getClass().getDeclaredField("mDataSet");
            elementType = String.valueOf(hashSet.getGenericType());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return new Formatter()
                .format("Type: %s\n" +
                                "Size: %d\n" +
                                "Creation Date: %s\n",
                        elementType, getDragonList().toArray().length, fileCreationDate)
                .toString();
    }

    @Override
    public String toString() {
        return super.toString() + ": " + "Вывести информацию о коллекции";
    }
}
