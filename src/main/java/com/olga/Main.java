package com.olga;

import com.olga.util.Executor;

public class Main {
    public static void main(String[] args) {
        CollectionService collectionService = null;
        try {
            collectionService = new DragonCollection(System.getenv("XML_FILE"));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        new Executor(collectionService).start();
    }
}
