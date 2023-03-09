// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorPID extends CommandBase {
  private final ElevatorSubsystem elevatorSubsystem;
  private final PIDController ePIDController;
  /** Creates a new ElevatorPID. */
  public ElevatorPID(ElevatorSubsystem elevatorSubsystem, double esetpoint) {
    this.elevatorSubsystem = elevatorSubsystem;
    this.ePIDController = new PIDController(ElevatorConstants.kEP, ElevatorConstants.kEI, ElevatorConstants.kED);
    ePIDController.setSetpoint(esetpoint);
    addRequirements(elevatorSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    ePIDController.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double eSpeed = ePIDController.calculate(elevatorSubsystem.getMeasurement());
    elevatorSubsystem.setEMotor(eSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    elevatorSubsystem.setEMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
