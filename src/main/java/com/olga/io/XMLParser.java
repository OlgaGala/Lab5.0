package com.olga.io;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.olga.dragon.Dragon;
import com.olga.dragon.DragonWrapper;

import java.io.*;
import java.util.Stack;

/**
 * Класс, реализующий логику преобразования коллекции в XML файл
 */
public class XMLParser {

    /**
     * Чтение из файла
     * @return - коллекция объектов предметной области
     * @throws Exception - в случае некорректного файла и/или его отсутствия
     */
    public static Stack<Dragon> read(String fileName) throws Exception {

        File file = new File(fileName);

        try(FileReader reader = new FileReader(file)) {
            // Уведомляем пользователя, если файл еще пустой
            if(file.length() == 0) {
                System.out.println("Файл пустой. Вы должны заполнить его, прежде чем использовать.");
                return new Stack<>(); //
            } else {
                XmlMapper xmlMapper = new XmlMapper();
                DragonWrapper wrapper = xmlMapper.readValue(reader, DragonWrapper.class);
                Stack<Dragon> dataSet = wrapper.getDragon();

                settleIds(dataSet);

                return dataSet;
            }
        } catch (FileNotFoundException e) {
            throw new Exception("Файл не найден", e);
        } catch (IOException e) {
            throw new Exception("Проблемы с чтением файла", e);
        }
    }

    /**
     * Запись в файл
     * @param mDataSet - коллекция, которую необходимо записать
     */
    public static void write(Stack<Dragon> mDataSet, String fileName) {
        XmlMapper mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try(OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(fileName))) {
            if(mDataSet.size() == 0) {
                output.write("");
            } else {
                DragonWrapper wrapper = new DragonWrapper();
                wrapper.setDragon(mDataSet);
                mapper.writeValue(output, wrapper);
            }
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Ошибка сохранения");
    }

    /**
     * Метод который переназначает id
     * Здесь мы просто присваиваем элементам id от 1 до бесконечности в порядке их добавления
     */
    private static void settleIds(Stack<Dragon> dragons) {
        int id = 0;
        for (Dragon vehicles : dragons) {
            vehicles.setId(++id);
        }
    }
}