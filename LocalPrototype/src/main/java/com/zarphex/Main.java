package com.zarphex;

import com.zarphex.Features.Clock;

import javax.swing.*;
import java.util.Properties;

public class Main {

    private final JPanel PANEL = new JPanel();
    private final JFrame FRAME = new JFrame();
    private final Clock clock;

    public Main(Properties props) {

        FRAME.setSize(Integer.parseInt(props.getProperty("windowLength")),
                Integer.parseInt(props.getProperty("windowHeight")));
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FRAME.add(PANEL);

        clock = new Clock(FRAME, props);
        FRAME.setVisible(true);
    }
    public static void main(String[] args) {
        Properties app_props = IOUtils.readPropertiesFile("res/app_properties.properties");
        Main main = new Main(app_props);
    }
}