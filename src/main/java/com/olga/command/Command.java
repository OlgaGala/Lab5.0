package com.olga.command;

import com.olga.dragon.Dragon;
import com.olga.i18n.Messenger;
import com.olga.print.api.Formatter;
import com.olga.print.implementation.FormatterImpl;

import lombok.*;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Stack;

@AllArgsConstructor
@NoArgsConstructor
@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
public abstract class Command {

    private Stack<Dragon> dragonList;

    private Formatter formatter;

    private Messenger messenger;

    private Validator validator;

    public Command(Stack<Dragon> dragonList, Messenger messenger) {
        this.dragonList = dragonList;
        this.messenger = messenger;
        this.formatter = new FormatterImpl();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

    }

    public abstract String execute(String args) throws Exception;

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
