package com.olga.print.api;

import com.olga.dragon.Dragon;

import java.util.Collection;
import java.util.Stack;

public interface Formatter {
    String formatCollection(Collection<?> collection);

    String formatSingleElement(Object object);

    String formatBooleanOperation(boolean bool);
}
