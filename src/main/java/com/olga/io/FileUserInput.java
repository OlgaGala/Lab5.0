package com.olga.io;

import com.olga.dragon.Dragon;
import com.olga.dragon.DragonCave;
import com.olga.i18n.Messenger;

import java.util.Scanner;

/**
 * Класс, предоставляющий логику чтения элементов из файла
 */
public class FileUserInput extends UserInput {

    public FileUserInput(Scanner in, Messenger messenger) {
        super(in, messenger);
    }

    /**
     * Ввод элемента из файла.
     * Здесь логика немного отличается - нам не нужно пытаться вводить каждое поле до тех пор,
     * пока ввод не будет корректным, а просто начать читать каждое поле, до первого брошенного исключения.
     * После исключения прекратить ввод и вывести сообщение об ошибке.
     * @return - Введенный элемент (в случае корректных данных в файле)
     * @throws Exception - исключение, брошенное в случае некорректных данных в файле
     */
    @Override
    public Dragon enterElement() throws Exception {
        return new Dragon(readName(),
                readCoordinates(),
                readAge(),
                readColor(),
                readDragonType(),
                readDragonCharacter(),
                new DragonCave(
                        readDepth(),
                        readNumberOfTreasures()
                )
        );
    }

    @Override
    public DragonCave enterCave() throws Exception {
        System.out.println(messenger.getMessage("askForNull"));
        String result = new Scanner(System.in).nextLine();
        return result.equals("NULL") ? null : new DragonCave(readDepth(), readNumberOfTreasures());
    }
}

