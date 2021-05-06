package com.olga.command;

import com.olga.dragon.Dragon;
import com.olga.io.ConsoleUserInput;
import com.olga.io.UserInput;
import lombok.Getter;
import lombok.Setter;

import java.util.Stack;

@Getter @Setter
public class UpdateId extends Command {

    private UserInput userInput;

    public UpdateId(Stack<Dragon> dragonList) {
        super(dragonList);

        userInput = new ConsoleUserInput();
    }

    @Override
    public String execute(String id) {

        getDragonList().forEach(d -> {
            if(d.getId().equals(Integer.parseInt(id))) {
                try {
                    d = userInput.enterElement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return null;
    }

    @Override
    public String toString() {
        return super.toString() + ": " + "Обновить значение элемента с заданным ID";
    }
}