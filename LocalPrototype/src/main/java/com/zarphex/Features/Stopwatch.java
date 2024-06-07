package com.zarphex.Features;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

/**
 * The stopwatch feature class with time tracking capabilities in hours, minutes and seconds.
 */
public class Stopwatch extends TimerMeasurer {
    private final JPanel lapsPanel;
    private final JButton LAP_STOPWATCH;
    private JLabel lapsLabel;

    /**
     * Default constructor for the stopwatch.
     * @param props: Properties file.
     */
    public Stopwatch(Properties props) {
        super(props);
        getTimerLabel().setEditable(false);

        // Instantiate the components.
        lapsPanel = new JPanel();
        lapsPanel.setLayout(new MigLayout("al center"));
        lapsPanel.setBackground(null);
        lapsPanel.setPreferredSize(new Dimension(100, 10));
        lapsLabel = new JLabel("LAPS");
        lapsLabel.setForeground(getCREAM_COLOR());
        lapsPanel.add(lapsLabel, "al center top, span, wrap");
        adjustLabelColour(TimerStatus.start);

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
        getSTART_TIMER().addActionListener(e -> {
            if (getTimerStatus()) {
                getTimerControl().stop();
                getSTART_TIMER().setText(props.getProperty("startLabel"));
            } else {
                getTimerControl().start();
                getSTART_TIMER().setText(props.getProperty("pauseLabel"));
            }
            setTimerStatus(!getTimerStatus());
        });
        LAP_STOPWATCH.addActionListener(e -> {
            drawLap(getTimerLabel().getText());
        });
        getRESET_TIMER().addActionListener(e -> {
            getTimerControl().stop();
            setSetTimer(LocalTime.MIN);
            setCurrentTimer(getSetTimer());
            deleteLaps();
            drawTimer(props);
            getSTART_TIMER().setText(props.getProperty("startLabel"));
            setTimerStatus(false);
        });

        // Set default stopwatch value.
        setCurrentTimer(LocalTime.MIN);
        drawTimer(props);
    }

    /**
     * Add all components to the screen.
     * @param props: Properties file.
     */
    @Override
    public void addLabels(Properties props) {
        // Add labels to the panel.
        getFeaturePanel().add(getTimerLabel(), "pos 0.5al 0.4al");
        getFeaturePanel().add(getSTART_TIMER(), "pos 0.2al 0.85al");
        getFeaturePanel().add(LAP_STOPWATCH, "pos 0.5al 0.85al");
        getFeaturePanel().add(getRESET_TIMER(), "pos 0.8al 0.85al");
        getBACKGROUND_PANEL().add(lapsPanel, "pos 0.5al 65%");
        getBACKGROUND_PANEL().add(getFeaturePanel());
    }

    /**
     * Add a second to the stopwatch and draw it.
     */
    public void updateTimer(Properties props) {
        setCurrentTimer(getCurrentTimer().plusSeconds(1));
        drawTimer(props);
    }

    /**
     * Create a new lap based on the current time.
     * @param lapTime: The current lap time.
     */
    public void drawLap(String lapTime) {
        // Create new lap label component and add to the panel.
        JLabel newLap = new JLabel();
        newLap.setText(lapTime);
        newLap.setFont(new Font(getFont(), Font.PLAIN, 12));
        newLap.setForeground(getCREAM_COLOR());
        lapsPanel.add(newLap, "al center, span, wrap");
        lapsPanel.revalidate();
        lapsPanel.repaint();
    }

    /**
     * Remove every lap recorded and redraw the entire panel.
     */
    public void deleteLaps() {
        lapsPanel.removeAll();
        lapsPanel.add(lapsLabel, "al center top, span, wrap");
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
