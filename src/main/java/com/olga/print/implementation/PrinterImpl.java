package com.olga.print.implementation;

import com.olga.print.api.Printer;

public class PrinterImpl implements Printer {
    public void printResult(String result) {
        if(result != null) {
            System.out.println(result);
        }
    }
}