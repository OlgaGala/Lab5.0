package com.olga.command;

import com.olga.dragon.Dragon;
import com.olga.i18n.Messenger;
import com.olga.io.XMLParser;
import com.olga.message.Message;
import lombok.Getter;
import lombok.Setter;

import java.util.Stack;

@Getter @Setter
public class Save extends Command {

    public Save(Stack<Dragon> dragonList) {
        super(dragonList);
    }

    @Override
    public String execute(Message ignore) {

        XMLParser.write(getDragonList(), System.getenv("XML_FILE"));

        return getFormatter().formatBooleanOperation(true, getMessenger());
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoSave");
    }
}
