package com.olga.dragon;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Objects;

public class Dragon implements Comparable<Dragon>, Serializable {

    private static Integer static_id = 1;

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

    public Dragon() {
    }

    public static Integer getStatic_id() {
        return static_id;
    }

    public static void setStatic_id(Integer static_id) {
        Dragon.static_id = static_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public DragonType getType() {
        return type;
    }

    public void setType(DragonType type) {
        this.type = type;
    }

    public DragonCharacter getCharacter() {
        return character;
    }

    public void setCharacter(DragonCharacter character) {
        this.character = character;
    }

    public DragonCave getCave() {
        return cave;
    }

    public void setCave(DragonCave cave) {
        this.cave = cave;
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
        return "Dragon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", age=" + age +
                ", color=" + color +
                ", type=" + type +
                ", character=" + character +
                ", cave=" + cave +
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