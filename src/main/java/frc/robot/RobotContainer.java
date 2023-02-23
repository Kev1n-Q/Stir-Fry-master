// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.AutoAlignCommand;
import frc.robot.commands.Autos;
import frc.robot.commands.DefaultDriveCommand;
import frc.robot.commands.LEDChargeStationCommand;
import frc.robot.commands.Pipeline0Command;
import frc.robot.commands.Pipeline1Command;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.LEDSubsystem; 
import frc.robot.subsystems.LimelightSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
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
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();


  private final DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
  private final LimelightSubsystem limelightSubsystem = new LimelightSubsystem();
  private final LEDSubsystem ledSubsystem = new LEDSubsystem();
 
  private final XboxController driverController = new XboxController(Constants.driverController_ID);
  private final CommandXboxController drive1Controller = new CommandXboxController(Constants.driverController_ID);
  


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    drivetrainSubsystem.setDefaultCommand(new DefaultDriveCommand(drivetrainSubsystem,
    () -> drive1Controller.getRawAxis(Constants.kRight_X),
    () -> drive1Controller.getRawAxis(Constants.kLeft_Y)));  
    
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
        

        XboxController leftBumper = new XboxController(Constants.kLeft_Bumper);
        XboxController rightBumper = new XboxController( Constants.kRight_Bumper);
        XboxController xButton = new XboxController(Constants.kX_Button);
    
        // Switch to pipeline 'X' when the 'X' bumper is pressed 
        drive1Controller.leftBumper().onTrue(new Pipeline0Command(limelightSubsystem, ledSubsystem));
        drive1Controller.rightBumper().onTrue(new Pipeline1Command(limelightSubsystem, ledSubsystem));
        // run AutoAlignCommand when the Xbutton is pressed
        drive1Controller.x().onTrue(new AutoAlignCommand(drivetrainSubsystem, limelightSubsystem));

        drive1Controller.b().onTrue(new LEDChargeStationCommand(ledSubsystem)); // rainbow: When going to platform
   
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }

}
