// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

  public class AutoAlignCommand extends CommandBase {

    private final DrivetrainSubsystem drivetrainSubsystem;
    private final LimelightSubsystem limelight;
    

  /** Creates a new AutoAlignCommand. */
 public AutoAlignCommand(DrivetrainSubsystem drivetrainSubsystem, LimelightSubsystem limelight) {
    this.drivetrainSubsystem = drivetrainSubsystem;
    this.limelight = limelight;
    addRequirements(drivetrainSubsystem, limelight);
    
  }

 

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    limelight.setPipeline(Constants.VisionConstants.Default_Pipeline); // pipeline 0 (ATags)
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    LimelightSubsystem.Update_Limelight_Values();
    
    double driveCommand = LimelightSubsystem.m_LimelightDriveCommand;
    double rotateCommand = LimelightSubsystem.m_LimelightRotateCommand;
    
    if (LimelightSubsystem.m_LimelightHasValidTargets ==  true) { // try == true
      drivetrainSubsystem.setArcadeSpeed(driveCommand, rotateCommand); 
    } else {
      drivetrainSubsystem.setArcadeSpeed(0, 0);
    }

    /* double driveCommand = limelight.getDriveCommand(Constants.AutoAlignConstants.driveSpeed, Constants.AutoAlignConstants.desiredtargetArea);
    double rotateCommand = limelight.getRotateCommand(Constants.AutoAlignConstants.rotateSpeed);

    drivetrainSubsystem.setArcadeSpeed(driveCommand, rotateCommand); */

  }

  // Called once the command endsr is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrainSubsystem.setArcadeSpeed(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return limelight.hasValidTargets() == false;
  }
}
