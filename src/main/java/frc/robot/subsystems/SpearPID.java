// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants.SpearConstants;

public class SpearPID extends PIDSubsystem {
  private CANSparkMax m_SpearPID = new CANSparkMax(SpearConstants.kSpearDriveID, MotorType.kBrushless);
  private RelativeEncoder m_SpearPot = m_SpearPID.getEncoder();
 // private RelativeEncoder m_Spear2Pot = m_SpearPID.getEncoder(null, 0)



  /** Creates a new ExpandDongPID. */
  public SpearPID() {
    super(
        // The PIDController used by the subsystem
        new PIDController(1, 0.07, 0));
  }

  @Override
  public void useOutput(double output, double setpoint) {
    m_SpearPID.set(setpoint);
  }

  public void encoderLog(){
    SmartDashboard.putString("Chopstick", m_SpearPot.toString());
  }
   public void setSpearMotorSpeed (double spearSpeed){
    m_SpearPID.set(spearSpeed);
  } 

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return m_SpearPot.getPosition();

  }
  @Override
  public void periodic() {
    encoderLog();
  }
}
