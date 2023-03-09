// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SpearPID;

public class SetSpearPosition extends CommandBase {
  
  private final SpearPID m_spearPID;
  private final double m_spearSetPoint;

  public SetSpearPosition(double spearSetPoint, SpearPID SpearPID) {
    m_spearPID = SpearPID;
    m_spearSetPoint = spearSetPoint;

    addRequirements(m_spearPID);

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_spearPID.setSetpoint(m_spearSetPoint);
    //m_spearPID.setSpearMotorSpeed(m_spearSetPoint);
    m_spearPID.enable();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_spearPID.getController().atSetpoint();
  }
}
