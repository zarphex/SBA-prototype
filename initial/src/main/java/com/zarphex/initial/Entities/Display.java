package com.zarphex.initial.Entities;

public class Display {
    private boolean isLightModeActive;

    public Display() {
        this.isLightModeActive = true;
    }

    public void changeLightOrDark() {
        isLightModeActive = !isLightModeActive;
    }
}
