package com.lab7.api.message;

import com.lab7.api.entity.Dragon;
import com.lab7.api.entity.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {

    private User user;

    private String command;
    private Dragon dragon;
    private String result;

    public Message(String command, Dragon dragon) {
        this.command = command;
        this.dragon = dragon;
    }

    public Message(String command, User user) {
        this(command, (Dragon) null);
        this.user = user;
    }

    public Message(String command) {
        this(command, (Dragon) null);
    }
}