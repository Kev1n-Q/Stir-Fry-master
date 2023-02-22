// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DrivetrainSubsystem extends SubsystemBase {
  private CANSparkMax frontLeftMotor = new CANSparkMax(Constants.DrivetrainConstants.frontLeftMotor_ID, MotorType.kBrushless);
  private CANSparkMax frontRightMotor = new CANSparkMax(Constants.DrivetrainConstants.frontRightMotor_ID, MotorType.kBrushless);

  private CANSparkMax backLeftMotor = new CANSparkMax(Constants.DrivetrainConstants.backLeftMotor_ID, MotorType.kBrushless);
  private CANSparkMax backRightMotor = new CANSparkMax(Constants.DrivetrainConstants.backRightMotor_ID, MotorType.kBrushless);

  private MotorControllerGroup leftSideMotors = new MotorControllerGroup(frontLeftMotor, backLeftMotor);
  private MotorControllerGroup rightSideMotors = new MotorControllerGroup(frontRightMotor, backRightMotor);

  private DifferentialDrive arcDrive = new DifferentialDrive(leftSideMotors, rightSideMotors);

   

  public void setArcadeSpeed (double moveSpeed, double rotateSpeed){
    arcDrive.arcadeDrive(moveSpeed, rotateSpeed);
    System.out.println("Drive speed: " + speed + ", Rotation: " + rotation); // Debug statement
  } 

  public void stop() {
    setArcadeSpeed(0, 0); 
  }

  /** Creates a new DrivetrainSubsystem. */
  public DrivetrainSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
