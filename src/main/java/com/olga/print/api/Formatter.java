package com.olga.print.api;

import com.olga.i18n.Messenger;
import java.util.Collection;

public interface Formatter {
    String formatCollection(Collection<?> collection);
    String formatBooleanOperation(boolean bool, Messenger messenger);
}
