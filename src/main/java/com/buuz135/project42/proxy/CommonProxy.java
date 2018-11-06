/*
 * This file is part of Industrial Foregoing.
 *
 * Copyright 2018, Buuz135
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in the
 * Software without restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the
 * following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.buuz135.project42.proxy;

import com.buuz135.project42.api.annotation.ProjectManual;
import com.buuz135.project42.api.manual.IManual;
import com.buuz135.project42.manual.ManualInfo;
import com.buuz135.project42.util.AnnotationHelper;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static com.buuz135.project42.Project42.LOGGER;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) throws Exception {
        LOGGER.info("Searching for manuals");
        for (Class<?> aClass : AnnotationHelper.getAnnotatedClasses(event.getAsmData(), ProjectManual.class)) {
            String id = aClass.getAnnotation(ProjectManual.class).value();
            if (!IManual.class.isAssignableFrom(aClass)) {
                throw new Exception("Manual with id " + id + " doesn't extend IManual");
            }
            if (!ManualInfo.MANUALS.containsKey(id)) {
                ManualInfo.MANUALS.put(id, new ManualInfo(id, aClass.getAnnotation(ProjectManual.class).modName(), (Class<? extends IManual>) aClass));
                LOGGER.info("Registered Manual with id " + id + " successfully");
            } else {
                LOGGER.warn("Duplicate manual id " + id + ". IGNORED!");
            }
        }
    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
