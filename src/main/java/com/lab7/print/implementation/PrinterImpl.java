package com.lab7.print.implementation;

import com.lab7.print.api.Printer;

public class PrinterImpl implements Printer {
    public void printResult(String result) {
        if(result != null) {
            System.out.println(result);
        }
    }
}