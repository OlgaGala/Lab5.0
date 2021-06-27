package com.client;

import com.api.command.Command;
import com.api.command.annotation.AttachedObj;
import com.api.command.annotation.AttachedObjFactory;
import com.api.entity.Dragon;
import com.api.entity.User;
import com.api.message.MessageReq;
import com.api.message.MessageResp;
import com.api.print.api.Printer;
import com.api.print.implementation.PrinterImpl;
import lombok.AllArgsConstructor;

import java.util.Scanner;

@AllArgsConstructor
public class ClientExecutor {

    private Dragon validateAnnotation(Class<? extends Command> c, User user) throws Exception {

        if(c.isAnnotationPresent(AttachedObj.class)) {
            AttachedObj attachedObj = c.getAnnotation(AttachedObj.class);

            return AttachedObjFactory.newInstance(attachedObj.type(), user);
        }

        return null;
    }
}
