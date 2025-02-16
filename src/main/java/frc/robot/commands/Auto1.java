// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;


public class Auto1 extends CommandBase { // L file 

  private final DrivetrainSubsystem drivetrainSubsystem;
  private final DoubleSupplier moveSpeed;
  private final DoubleSupplier rotateSpeed; 

  /** Creates a new Autos1. */
  public Auto1(DrivetrainSubsystem drivetrainSubsystem, DoubleSupplier moveSpeed, DoubleSupplier rotateSpeed) {
    this.drivetrainSubsystem = drivetrainSubsystem;
    this.moveSpeed = moveSpeed;
    this.rotateSpeed = rotateSpeed;
    

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrainSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double realMoveSpeed = moveSpeed.getAsDouble();
    double realRotateSpeed = rotateSpeed.getAsDouble();

    drivetrainSubsystem.setArcadeSpeed(realMoveSpeed, realRotateSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
