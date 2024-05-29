package com.zarphex;

import com.zarphex.Features.*;
import com.zarphex.Features.Timer;

import javax.swing.*;
import java.util.Properties;

public class SBAPrototype {
    private final Clock clock;
    private final Timer timer;
    private final JFrame FRAME;
    private static final Feature[] featureList = new Feature[2];

    public SBAPrototype(Properties props) {
        FRAME = new JFrame();

        createGUI(props);

        // Create default features.
        clock = new Clock(props);
        timer = new Timer(props);

        featureList[0] = clock;
        featureList[1] = timer;
        FRAME.add(clock.getPanel());
        addFeatureSwapButton(0);
        FRAME.setVisible(true);
    }

    public static void main(String[] args) {
        Properties app_props = IOUtils.readPropertiesFile("res/app_properties.properties");
        new SBAPrototype(app_props);
    }

    public void createGUI(Properties props) {
        // Add default frame properties.
        FRAME.setSize(Integer.parseInt(props.getProperty("windowLength")),
                Integer.parseInt(props.getProperty("windowHeight")));
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addFeatureSwapButton(int index) {
        int n = featureList.length;
        JButton leftArrow = featureList[index].getLEFT_ARROW();
        JButton rightArrow = featureList[index].getRIGHT_ARROW();

        // Add action listeners to left and right arrow buttons.
        leftArrow.addActionListener(e -> {
            int prevIndex = (index - 1 + n) % n;
            featureList[index].getPanel().setVisible(false);
            FRAME.remove(featureList[index].getPanel());
            featureList[prevIndex].getPanel().setVisible(true);
            FRAME.add(featureList[prevIndex].getPanel());
            addFeatureSwapButton(prevIndex);
        });

        rightArrow.addActionListener(e -> {
            int nextIndex = (index + 1) % n;
            featureList[index].getPanel().setVisible(false);
            FRAME.remove(featureList[index].getPanel());
            featureList[nextIndex].getPanel().setVisible(true);
            FRAME.add(featureList[nextIndex].getPanel());
            addFeatureSwapButton(nextIndex);
        });
    }
}