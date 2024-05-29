package com.zarphex;

import com.zarphex.Features.Clock;

import javax.swing.*;
import java.util.Properties;

public class SBAPrototype {
    private final Clock clock;
    private final JFrame FRAME;
    private final int FEATURE_COUNT = 3;
    private static String[] featureList;

    public SBAPrototype(Properties props) {
        FRAME = new JFrame();
        featureList = new String[FEATURE_COUNT];

        createGUI(props);

        // Create clock default feature.
        clock = new Clock(props);
        nextFeature(0);

        FRAME.setVisible(true);
    }

    public static void main(String[] args) {
        Properties app_props = IOUtils.readPropertiesFile("res/app_properties.properties");
        SBAPrototype prototype = new SBAPrototype(app_props);
    }

    public void createGUI(Properties props) {
        // Add default frame properties.
        FRAME.setSize(Integer.parseInt(props.getProperty("windowLength")),
                Integer.parseInt(props.getProperty("windowHeight")));
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void nextFeature(int index) {
        switch (featureList[index]) {
            case ("clock") :
                FRAME.add(clock.getPanel());
                break;
            case ("timer") :

        }
    }
}