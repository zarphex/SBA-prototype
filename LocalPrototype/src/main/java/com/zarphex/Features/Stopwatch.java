package com.zarphex.Features;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;

/**
 * The stopwatch feature class with time tracking capabilities in hours, minutes and seconds.
 */
public class Stopwatch extends TimerMeasurer {
    private JPanel lapsPanel;
    private final JButton LAP_STOPWATCH;
    private ArrayList<JLabel> stopwatchLapsLabel;

    /**
     * Default constructor for the stopwatch.
     * @param props: Properties file.
     */
    public Stopwatch(Properties props) {
        super(props);

        // Instantiate the components.
        lapsPanel = new JPanel();
        lapsPanel.setBackground(null);
        lapsPanel.setLayout(new BoxLayout(lapsPanel, BoxLayout.Y_AXIS));

        stopwatchLapsLabel = new ArrayList<>();
        LAP_STOPWATCH = new JButton(props.getProperty("lapLabel"));
        formatButton(LAP_STOPWATCH);

        createGUI(props);
        initialiseTimer(props);
        addLabels(props);
        addArrowComponents();
    }

    /**
     * Create the stopwatch with all button functions.
     * @param props: Properties file.
     */
    @Override
    public void initialiseTimer(Properties props) {
        // Call to update the stopwatch every second.
        setTimerControl(new Timer(1000, e -> updateTimer(props)));

        // Button action listeners.
        getSTART_TIMER().addActionListener(e -> getTimerControl().start());
        getPAUSE_TIMER().addActionListener(e -> getTimerControl().stop());
        getRESET_TIMER().addActionListener(e -> {
            getTimerControl().stop();
            setSetTimer(LocalTime.MIN);
            setCurrentTimer(getSetTimer());
            deleteLaps();
            drawTimer(props);
        });

        // Set default stopwatch value.
        setCurrentTimer(LocalTime.MIN);
        drawTimer(props);

        LAP_STOPWATCH.addActionListener(e -> drawLap(getCurrentTimer()));
    }

    /**
     * Add all components to the screen.
     * @param props: Properties file.
     */
    @Override
    public void addLabels(Properties props) {
        // Add labels to the panel.
        getPanel().add(getTimerLabel(), "al center bottom, span, push, wrap");
        getPanel().add(getSTART_TIMER(), "al center, split 4, span");
        getPanel().add(getPAUSE_TIMER(), "al center, span");
        getPanel().add(LAP_STOPWATCH, "al center, span");
        getPanel().add(getRESET_TIMER(), "al center, span, wrap");
        getPanel().add(lapsPanel, "pos 0.5al 55%");
    }

    /**
     * Add a second to the stopwatch and draw it.
     * @param props
     */
    public void updateTimer(Properties props) {
        setCurrentTimer(getCurrentTimer().plusSeconds(1));
        drawTimer(props);
    }

    /**
     * Create a new lap based on the current time.
     * @param time: The current time.
     */
    public void drawLap(LocalTime time) {
        // Create new Label component.
        JLabel newLap = new JLabel();
        newLap.setFont(new Font(getFont(), Font.PLAIN, 12));
        newLap.setText(time.format(DateTimeFormatter.ISO_LOCAL_TIME));
        // Add the label to the list of labels.
        stopwatchLapsLabel.add(newLap);
        lapsPanel.add(newLap);
    }

    /**
     * Remove every lap recorded and redraw the entire panel.
     */
    public void deleteLaps() {
        lapsPanel.removeAll();
        stopwatchLapsLabel.clear();
        lapsPanel.revalidate();
        lapsPanel.repaint();
    }

    /**
     * Display the stopwatch in the valid format.
     * @param props: Properties file.
     */
    public void drawTimer(Properties props) {
        getTimerLabel().setText(getCurrentTimer().format(DateTimeFormatter.ISO_LOCAL_TIME));
    }
}
