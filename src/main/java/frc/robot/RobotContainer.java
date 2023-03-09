// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.SpearConstants;
import frc.robot.commands.Auto1;
import frc.robot.commands.AutoAlignCommand;
import frc.robot.commands.DefaultDriveCommand;
// import frc.robot.commands.LEDChargeStationCommand;
import frc.robot.commands.Pipeline0Command;
import frc.robot.commands.Pipeline1Command;
import frc.robot.commands.SetSpearPosition;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LEDSubsystem; 
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.SpearPID;
import frc.robot.subsystems.The_Pinch_n_Twist;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
  private final LimelightSubsystem limelightSubsystem = new LimelightSubsystem();
  private final LEDSubsystem ledSubsystem = new LEDSubsystem();

  private final The_Pinch_n_Twist the_Pinch_n_Twist = new The_Pinch_n_Twist();
  private final SpearPID spearPID = new SpearPID();
 
  private final CommandXboxController driveController = new CommandXboxController(Constants.driverController_ID);
  private final CommandXboxController mechanismController = new CommandXboxController(Constants.mechanismController_ID);


  private final Command auto1 = new Auto1(drivetrainSubsystem, null, null);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    drivetrainSubsystem.setDefaultCommand(new DefaultDriveCommand(drivetrainSubsystem,
     () -> driveController.getRawAxis(Constants.kLeft_Y),
     () -> -driveController.getRawAxis(Constants.kRight_X)));

    configureBindings(); 
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    //Two button pinch (claw) control
    
    mechanismController.button(Constants.kA_Button).toggleOnTrue(Commands.startEnd(the_Pinch_n_Twist::pinchExtend, the_Pinch_n_Twist::pinchRetract, the_Pinch_n_Twist));

    driveController.leftBumper().onTrue(new DefaultDriveCommand(drivetrainSubsystem, 
    () -> .05, () -> 0));

    mechanismController.leftBumper().onTrue(new SetSpearPosition(
      SpearConstants.kSpearRetract, spearPID));

    mechanismController.rightBumper().onTrue(new SetSpearPosition(
      SpearConstants.kSpearExtend, spearPID));

    // Switch pipelines 
    driveController.leftTrigger().onTrue(new Pipeline0Command(limelightSubsystem, ledSubsystem));
    driveController.rightTrigger().onTrue(new Pipeline1Command(limelightSubsystem, ledSubsystem));

    // run AutoAlignCommand
    driveController.x().onTrue(new AutoAlignCommand(drivetrainSubsystem,limelightSubsystem));

    // driveController.y().onTrue(new LEDChargeStationCommand(ledSubsystem)); // rainbow: When going to platform
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return auto1; 
  }

}
