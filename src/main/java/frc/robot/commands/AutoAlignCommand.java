// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DrivetrainSubsystem;
// import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.LimelightSubsystem;


public class AutoAlignCommand extends CommandBase {

    private final DrivetrainSubsystem drivetrainSubsystem;
    private final LimelightSubsystem limelightSubsystem;

    /* 
    private final double desiredTargetArea;
    private final double maxDriveSpeed;
    private final double driveSpeed;
    private final double rotateSpeed;
    */
    
  /** Creates a new AutoAlignCommand. */
 /* public AutoAlignCommand(DrivetrainSubsystem drivetrainSubsystem, LimelightSubsystem limelightSubsystem) {
    this.drivetrainSubsystem = drivetrainSubsystem;
    this.limelightSubsystem = limelightSubsystem;
    addRequirements(drivetrainSubsystem, limelightSubsystem);
  } */

  public AutoAlignCommand(DrivetrainSubsystem drivetrainSubsystem, LimelightSubsystem limelightSubsystem) {
    this.limelightSubsystem = limelightSubsystem;
    this.drivetrainSubsystem = drivetrainSubsystem;
    addRequirements(drivetrainSubsystem, limelightSubsystem);  
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // limelightSubsystem.reset();
    limelightSubsystem.setPipeline(Constants.VisionConstants.Default_Pipeline); // pipeline 0 (ATags)
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    // this will run periodically 
    limelightSubsystem.updateLimelightValues(); 

    double tx = limelightSubsystem.getTX(); 
    double targetArea = limelightSubsystem.getTargetArea(); 
    
    // if has valid targets, run this command || else, setDriveSpeed and setRotateSpeed to 0
    if (limelightSubsystem.hasValidTargets() == 1) {
      // targetArea == 0.5 && targetArea == 3
      if (targetArea >= Constants.AutoAlignConstants.minTargetArea && targetArea <= Constants.AutoAlignConstants.maxTargetArea) { // if within this range, run this
        if (Math.abs(tx) <= 0.5 && targetArea >= 2) {
          drivetrainSubsystem.stop(); // if tx is less than or equal to 0.5, it means we're really close -> stop
          System.out.println("You're good to go! - READY"); 
        } else if (tx > 0.5) {  // if tx is > 0.5, run this 
            if (targetArea < 2) {
              drivetrainSubsystem.setArcadeSpeed(Constants.AutoAlignConstants.driveSpeed, -Constants.AutoAlignConstants.rotateSpeed);
              System.out.println("NOT GOOD DISTANCE - NOT ALIGNED"); 
            } else if (targetArea >= 2) {
              drivetrainSubsystem.setArcadeSpeed(0, -Constants.AutoAlignConstants.rotateSpeed);
              System.out.println("GOOD DISTANCE - NOT ALIGNED)"); 
            }  
        } else if (tx < -0.5) { // if tx is < -0.5 (left) ** more to the left than 0.5, ** then run this
            if (targetArea < 2) { // figure out where negative and positive rotations go****
            drivetrainSubsystem.setArcadeSpeed(Constants.AutoAlignConstants.driveSpeed, Constants.AutoAlignConstants.rotateSpeed);
            System.out.println("NOT GOOD DISTANCE - NOT ALIGNED");
          } else if (targetArea >= 2) {
            drivetrainSubsystem.setArcadeSpeed(0, Constants.AutoAlignConstants.rotateSpeed);
            System.out.println("GOOD DISTANCE - NOT ALIGNED"); 
          }
        } 
      } 

      else { // else, we're too far, don't do anything (switch to DefaultDriveCommand!)
        drivetrainSubsystem.stop(); 
        System.out.println("Not in range!!! GET CLOSER"); 
      }  
    } else {
      drivetrainSubsystem.setArcadeSpeed(0,0);
    }
  }

    /* double driveCommand = LimelightSubsystem.getDriveCommand(Constants.AutoAlignConstants.driveSpeed, Constants.AutoAlignConstants.desiredTargetArea);
    
    double rotateCommand = LimelightSubsystem.getRotateCommand(Constants.AutoAlignConstants.rotateSpeed);
    
    drivetrainSubsystem.setArcadeSpeed(driveCommand, rotateCommand); */

    /* if (LimelightSubsystem.m_LimelightHasValidTargets == true) { 
      drivetrainSubsystem.setArcadeSpeed(driveCommand, rotateCommand); 
    } else {
      drivetrainSubsystem.setArcadeSpeed(0, 0);
    } */

  // ______________________________________________________________

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrainSubsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    /* if (limelightSubsystem.getValidTargets() == 1) {
      return false;
    } 
    else{
      return true;
    } */
  
    return false; 
  }

}
