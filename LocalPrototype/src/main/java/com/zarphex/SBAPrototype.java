package com.zarphex;

import com.zarphex.Features.*;
import javax.swing.*;
import java.util.Properties;

/**
 * The main SBA class.
 */
public class SBAPrototype {
    private final JFrame FRAME;
    private static Feature[] featureList;

    /**
     * The constructor.
     * @param props: Properties file.
     */
    public SBAPrototype(Properties props) {
        FRAME = new JFrame();
        featureList = new Feature[Integer.parseInt(props.getProperty("featuresNumber"))];

        createGUI(props);

        // Create default features.
        featureList[0] = new Clock(props);
        featureList[1] = new TimerFeature(props);
        featureList[2] = new Stopwatch(props);
        featureList[3] = new PomodoroTimer(props);
        featureList[4] = new TodoList(props);

        // Default display of the clock.
        FRAME.add(featureList[0].getBACKGROUND_PANEL());
        addFeatureSwapButton(0);
        FRAME.setVisible(true);
    }

    /**
     * The main function.
     * @param args: Arguments.
     */
    public static void main(String[] args) {
        Properties app_props = IOUtils.readPropertiesFile("res/app_properties.properties");
        new SBAPrototype(app_props);
    }

    /**
     * Set the screen dimensions and exit command.
     * @param props: Properties file.
     */
    public void createGUI(Properties props) {
        // Add default frame properties.
        FRAME.setSize(Integer.parseInt(props.getProperty("windowLength")),
                Integer.parseInt(props.getProperty("windowHeight")));
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Add action listener logic to the left and right button icons.
     * @param index: Index of the feature in the features list.
     */
    public void addFeatureSwapButton(int index) {
        // Length of the features list and getters for the arrow icons.
        int n = featureList.length;
        JButton leftArrow = featureList[index].getLEFT_ARROW();
        JButton rightArrow = featureList[index].getRIGHT_ARROW();

        // Left arrow button accounting for edge case minimum index.
        leftArrow.addActionListener(e -> {
            int prevIndex = (index - 1 + n) % n;
            featureList[index].getBACKGROUND_PANEL().setVisible(false);
            FRAME.remove(featureList[index].getBACKGROUND_PANEL());
            featureList[prevIndex].getBACKGROUND_PANEL().setVisible(true);
            FRAME.add(featureList[prevIndex].getBACKGROUND_PANEL());
            addFeatureSwapButton(prevIndex);
        });

        // Right arrow button accounting for edge case maximum index.
        rightArrow.addActionListener(e -> {
            int nextIndex = (index + 1) % n;
            featureList[index].getBACKGROUND_PANEL().setVisible(false);
            FRAME.remove(featureList[index].getBACKGROUND_PANEL());
            featureList[nextIndex].getBACKGROUND_PANEL().setVisible(true);
            FRAME.add(featureList[nextIndex].getBACKGROUND_PANEL());
            addFeatureSwapButton(nextIndex);
        });
    }
}