package com.zarphex;

import com.zarphex.Features.Clock;

import java.util.Properties;

public class SBAPrototype {
    private final Clock clock;

    public SBAPrototype(Properties props) {
        // Create clock default feature.
        clock = new Clock(props);
    }
    public static void main(String[] args) {
        Properties app_props = IOUtils.readPropertiesFile("res/app_properties.properties");
        SBAPrototype prototype = new SBAPrototype(app_props);
    }
}