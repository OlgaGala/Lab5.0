package com.olga.util;

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

    private final File file;

    public XMLParser(String fileName) {
        this.file = new File(fileName);
    }

    public File getFile() {
        return file;
    }

    /**
     * Чтение из файла
     * @return - коллекция объектов предметной области
     * @throws Exception - в случае некорректного файла и/или его отсутствия
     */
    public Stack<Dragon> read() throws Exception {
        try(FileReader reader = new FileReader(file)) {
            // Уведомляем пользователя, если файл еще пустой
            if(file.length() == 0) {
                System.out.println("Файл пустой. Вы должны заполнить его, прежде чем использовать.");
                return new Stack<>(); //
            } else {
                XmlMapper xmlMapper = new XmlMapper();
                DragonWrapper wrapper = xmlMapper.readValue(reader, DragonWrapper.class);
                Stack<Dragon> dataSet = new Stack<>();
                dataSet = wrapper.getDragon();
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
     * @return - Результат работы выполнения
     */
    public String write(Stack<Dragon> mDataSet) {
        XmlMapper mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try(OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(file))) {
            if(mDataSet.size() == 0) {
                output.write("");
            } else {
                DragonWrapper wrapper = new DragonWrapper();
                wrapper.setDragon(mDataSet);
                mapper.writeValue(output, wrapper);
            }
            return "Коллекция была сохранена";
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Ошибка сохранения");
    }
}