// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final int driverController_ID = 0; // drive + vision pipeline
    public static final int mechanismController_Id = 1; // mechanisms + vision command


    public static final int kDPadUp = 0;
    public static final int kDPadDown = 180;
    public static final int kDPadRight = 90;
    public static final int kDPadLeft = 270;
  
    public static final int kLeft_Y = 1;
    public static final int kLeft_X = 0;
    public static final int kRight_X = 4;
    public static final int kRight_Y = 5;
    public static final int kLeft_Trigger = 2;
    public static final int kRight_Trigger = 3;
  
    public static final int kA_Button = 1;
    public static final int kLeft_Bumper = 5;
    public static final int kRight_Bumper = 6;
    public static final int kTwoWindowsButton = 7;
    public static final int kDriverFieldOrientedButtonIdx = 8;
    public static final int kYbutton = 4;
    public static final int kB_Button = 2;
    public static final int kX_Button = 3;
    public static final double kDeadband = 0.05;

    // AutoAlign constants // **EDIT**
    public static final class AutoAlignConstants{
        // TUNE
        public static final double rotateSpeed = 0.02;
        public static final double driveSpeed = 0.25;
        public static final double maxDriveSpeed = 0.65; // max speed will not surpass 65% full speed

        public static final double desiredtargetArea = 4; 
    }

    public static final class VisionConstants{
        public static final int Cube_Pipeline = 0; 
        public static final int Cone_Pipeline = 1;
        public static final int Default_Pipeline = 0;
    }

    public static final class DrivetrainConstants {
        public static final int frontLeftMotor_ID = 1;
        public static final int frontRightMotor_ID = 2;
        public static final int backLeftMotor_ID = 3;
        public static final int backRightMotor_ID = 4;
    }

    public static final class LEDConstants {
        public static final int ledBuffer = 60; // 60 lights
        public static final int ledLightsPort = 1; // PWM Port
    }
  
}
