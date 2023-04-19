// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LEDSubsystem extends SubsystemBase {

  private final AddressableLED ledLights;
  private final AddressableLEDBuffer ledLightsBuffer; // buffer = length of led lights

  /* Stages */
  // setDisabled: gold and red flow
  // autonomous: blue white red
  // pipeline 0: burgundy **DEFAULT** - ***LEFT BUMPER
  // pipeline 1: gold - ***RIGHT BUMPER
  // READY TO SCORE: Green
  // charge station period: rainbow


  /** Creates a new LEDSubsystem. */
  public LEDSubsystem() {
    ledLights = new AddressableLED(Constants.LEDConstants.ledLightsPort); // PWM Port **CHANGE**
    ledLightsBuffer = new AddressableLEDBuffer(Constants.LEDConstants.ledBuffer); // length
    ledLights.setLength(ledLightsBuffer.getLength());
    ledLights.setData(ledLightsBuffer);
    ledLights.start();
  }

  public void setDisabled() { // when robot is turned on (not enabled)
    for (int i = 0; i < ledLightsBuffer.getLength(); i++) {
      if((i % 8) == 0) {
        ledLightsBuffer.setRGB(0, 255, 209, 0);
      } else {
        ledLightsBuffer.setRGB(0, 255, 0, 0);
      }
    }
  }

  public void setAuton() { // when the robot is in auton
    int blueCount = 0;
    int whiteCount = 0;
    int redCount = 0;
    for (int i = 0; i < ledLightsBuffer.getLength(); i++) {
      if (blueCount == whiteCount && whiteCount == redCount) {
        // if all colors have been used the same number of times, reset the counts
        blueCount = 0;
        whiteCount = 0;
        redCount = 0;
      }
      
      if (blueCount == whiteCount && whiteCount < redCount) {
        ledLightsBuffer.setRGB(i, 0, 0, 255); // blue
        blueCount++;
      } else if (blueCount < whiteCount && whiteCount == redCount) {
        ledLightsBuffer.setRGB(i, 255, 255, 255); // white
        whiteCount++;
      } else {
        ledLightsBuffer.setRGB(i, 255, 0, 0); // red
        redCount++;
      }
    }
    ledLights.setData(ledLightsBuffer);

  }

  public void setBurgundy() { // when in pipeline 0: April tags
    ledLightsBuffer.setRGB(0, 128, 0, 32);
    ledLights.setData(ledLightsBuffer);
  }

  public void setGold() { // when in pipeline 1: retroreflective tape
    ledLightsBuffer.setRGB(0, 255, 209, 0);
    ledLights.setData(ledLightsBuffer);
  }

  public void setGreen() {
    ledLightsBuffer.setRGB(0, 0, 255, 0);
    ledLights.setData(ledLightsBuffer);
  }

   public void setRainbow() { // charge station period
    for (int i = 0; i < ledLightsBuffer.getLength(); i++) {
      int hue = (i / (int) ledLightsBuffer.getLength()) * 360; // hue: 0 - 360 degrees
      Color color = Color.fromHSV(hue, 1, 1); // if too bright, change v to 0.5
      ledLightsBuffer.setLED(i, color);
    }
    ledLights.setData(ledLightsBuffer); 
  } 

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // LED Arduino Code:
  /* #include <FastLED.h>
  #include <Arduino.h>

  #define LED_PIN 6
  #define NUM_LEDS ____
  #define BRIGHTNESS 255 (max)
  #define LED_TYPE WS2812B 
  CRGB leds[NUM_LEDS]; */

}
