// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;
import frc.robot.Constants.ElevatorConstants;

public class ElevatorSubsystem extends PIDSubsystem {
  private TalonFX leftElevatorFalcon = new TalonFX(ElevatorConstants.kLeftElevatorDriveID);
  private TalonFX rightElevatorFalcon = new TalonFX(ElevatorConstants.kRightElevatorDriveID);
  /** Creates a new ElevatorPID. */
  
  public ElevatorSubsystem() {
    super(
      // The PIDController used by the subsystem
      new PIDController(ElevatorConstants.kEP, ElevatorConstants.kEI, ElevatorConstants.kED));
  }

  @Override
  public void useOutput(double output, double setpoint) {
    leftElevatorFalcon.set(TalonFXControlMode.Position, setpoint);
    //rightElevatorFalcon.set(TalonFXControlMode.Follower, setpoint);
    rightElevatorFalcon.follow(leftElevatorFalcon);
    // Use the output here
  }

  public void setEMotor(double eSpeed) {
    leftElevatorFalcon.set(TalonFXControlMode.PercentOutput, eSpeed);
  }

  @Override
  public double getMeasurement() {
  return leftElevatorFalcon.getActiveTrajectoryPosition() * Constants.kEncoderTick2Meter;
  }


  public void elevatorLog() {
    //takes measurement acquired in getMeasurement and turns into string for elevatorLog

  SmartDashboard.putNumber("Elevator", getMeasurement());
}

  @Override
  public void periodic() {
    elevatorLog();
    //getMeasurement();
  }
}
