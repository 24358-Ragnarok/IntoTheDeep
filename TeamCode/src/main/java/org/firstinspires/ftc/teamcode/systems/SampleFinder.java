package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.BaseRobot;

import java.util.Objects;

/** @noinspection FieldCanBeLocal, unused, RedundantSuppression */
public class SampleFinder {

    private final ColorSensor colorSensor;
    private final String color;
    private final BaseRobot baseRobot;

    public SampleFinder(BaseRobot baseRobot, String color) {
        this.color = color.toLowerCase();
        this.baseRobot = baseRobot;
        colorSensor = baseRobot.hardwareMap.get(ColorSensor.class, "color");
    }

    public boolean find() {
        int colorAmount = 0;
        int nextColor;
        // how many times to check before averaging score and returning answer
        double checks = 30;
        for (int i = 0; i < checks; i++) { // check a couple times for an accurate count
            if (Objects.equals(color, "red")) {
                nextColor = colorSensor.red();
            } else {
                nextColor = colorSensor.blue();
            }

            if (nextColor > colorAmount) {
                colorAmount = nextColor;
            }

        }

        baseRobot.logger.update("Color amount found", colorAmount + "/500");
        return colorAmount > 500;
    }
}