package com.api.command.annotation;

import com.api.entity.Dragon;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AttachedObj {
    Class<?> type() default Dragon.class;
}
