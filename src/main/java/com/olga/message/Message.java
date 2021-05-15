package com.olga.message;

import com.olga.dragon.Dragon;
import com.olga.dragon.DragonCave;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Formatter;

@Getter @Setter
public class Message implements Serializable {

    private String command;
    private Dragon dragon;
    private String result;

    public Message(String command, Dragon dragon) {
        this.command = command;
        this.dragon = dragon;
    }

    public Message(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return new Formatter()
                .format("Command: %s\nAttached object: %s\nResult: %s",
                        command, dragon, result)
                .toString();
    }
}