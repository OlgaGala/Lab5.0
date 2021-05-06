package com.olga;

import com.olga.command.manager.CommandManager;
import com.olga.i18n.MessengerImpl;
import com.olga.io.XMLParser;
import com.olga.print.implementation.FormatterImpl;
import com.olga.print.implementation.PrinterImpl;
import com.olga.util.Executor;
import com.olga.util.ExecutorBuilder;

public class Main {
    public static void main(String[] args) throws Exception {

        Executor executor = new ExecutorBuilder()
                .setDataSet(XMLParser.read(System.getenv("XML_FILE")))
                .setCommandManager(new CommandManager())
                .setFormatter(new FormatterImpl())
                .setPrinter(new PrinterImpl())
                .setMessenger(new MessengerImpl())
                .build();

        executor.start();

    }
}
