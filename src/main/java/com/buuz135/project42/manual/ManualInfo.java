package com.buuz135.project42.manual;

import com.buuz135.project42.api.manual.IManual;

import java.util.HashMap;

public class ManualInfo {

    public static HashMap<String, ManualInfo> MANUALS = new HashMap<>();

    private final String id;
    private final Class<? extends IManual> manualClass;

    public ManualInfo(String id, Class<? extends IManual> manualClass) {
        this.id = id;
        this.manualClass = manualClass;
    }
}
