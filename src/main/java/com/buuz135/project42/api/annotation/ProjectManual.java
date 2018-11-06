package com.buuz135.project42.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to allow Project 42 to find the {@link com.buuz135.project42.api.manual.IManual} classes
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ProjectManual {

    /**
     * A unique value id of the manual
     *
     * @return a id of the manual
     */
    String value();

    /**
     * The mod name that adds the manual
     *
     * @return a mod name
     */
    String modName();
}
