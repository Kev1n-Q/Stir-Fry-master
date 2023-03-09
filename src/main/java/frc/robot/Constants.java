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
    public static final int mechanismController_ID = 1; // mechanisms + vision command


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

    public static final double kEncoderTick2Meter = 1 /2048.0 * 0.1 * Math.PI;

    public static final class AutoAlignConstants{ // TUNE
        public static final double rotateSpeed = 0.25;
        public static final double driveSpeed = 0.25;
       //  public static final double maxDriveSpeed = 0.30; // max speed will not surpass X% full speed

        public static final double minTargetArea = 0.25;
        public static final double maxTargetArea = 3; 
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

    public final static class PinchNTwistConstants {
        public static final int kSolenoidFoward = 0;
        public static final int kSolenoidReverse = 1;
    }

    public final static class SpearConstants {
        public static final int kSpearDriveID = 15;
        public static final double kSpearExtend = .2;
        public static final double kSpearRetract = .2;
        
       
        public static final double kPSpear = .2;
        public static final double kISpear = .002;
        public static final double kDSpear = 2;

        public static final double kSpearRatio = 1/10;
        public static final double kElevatorRatio = 1 / 262.5;
    }

    public final static class ElevatorConstants {
        public static final int kLeftElevatorDriveID = 6;
        public static final int kRightElevatorDriveID = 9;

        //PID magic
        public static final double kEP = .5;
        public static final double kEI = .005;
        public static final double kED = 5;

        //Motion magic
        public static final double kEVelo = 0.5;
        public static final double kEAccel = Math.sqrt(kEVelo);

        //Setpoints for elevator
        public static final double kElevatorFloor = 2;
        public static final double kElevatorMid = 5;
        public static final double kElevatorHigh = 8;

        public static final double kElevatorManualFoward = .5;
        public static final double kElevatorManualReverse = -.5;
    }
  
}
