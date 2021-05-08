package com.olga.dragon;

import com.olga.i18n.Messenger;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Objects;

@Getter @Setter
@NoArgsConstructor
public class Dragon implements Comparable<Dragon>, Serializable {

    private static Integer static_id = 1;

    public static Messenger messenger;

    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null

    private String creationDate; // Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long age; //Значение поля должно быть больше 0
    private Color color; //Поле может быть null
    private DragonType type; //Поле не может быть null
    private DragonCharacter character; //Поле не может быть null
    private DragonCave cave; //Поле может быть null

    public Dragon(String name,
                  Coordinates coordinates,
                  long age,
                  Color color,
                  DragonType type,
                  DragonCharacter character,
                  DragonCave cave) {
        this.id = static_id++;
        this.name = name;
        this.coordinates = coordinates;
        this.age = age;
        this.color = color;
        this.type = type;
        this.character = character;
        this.cave = cave;

        // Форматируем дату создания в обычную строку, чтобы ее было удобно сереализовывать
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        this.creationDate = LocalDate.now().format(dateFormatter);

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dragon)) return false;
        Dragon dragon = (Dragon) o;
        return age == dragon.age
                && id.equals(dragon.id)
                && name.equals(dragon.name)
                && coordinates.equals(dragon.coordinates)
                && creationDate.equals(dragon.creationDate)
                && color == dragon.color
                && type == dragon.type
                && character == dragon.character
                && Objects.equals(cave, dragon.cave);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, age, color, type, character, cave);
    }

    @Override
    public String toString() {
        return messenger.getMessage("dragon") + "{" +
                messenger.getMessage("id") + " = " + id +
                messenger.getMessage("name") + " = '" + name + '\'' +
                messenger.getMessage("coordinates") + " = " + coordinates +
                messenger.getMessage("creationDate") + " = " + creationDate +
                messenger.getMessage("age") + " = " + age +
                messenger.getMessage("color") + " = " + color +
                messenger.getMessage("type") + " = " + type +
                messenger.getMessage("character") + " = " + character +
                messenger.getMessage("cave") + " = " + cave +
                "}\n";
    }

    @Override
    public int compareTo(Dragon o) {
        return Comparator
                .comparing(Dragon::getName)
                .thenComparing(Dragon::getAge)
                .compare(this, o); // Здесь мы сортируем объекты в алфавитном порядке, а после в порядке возраста
    }
}