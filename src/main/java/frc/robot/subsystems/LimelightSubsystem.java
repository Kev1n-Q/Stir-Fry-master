// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

  package frc.robot.subsystems;

  import edu.wpi.first.networktables.NetworkTable;
  import edu.wpi.first.networktables.NetworkTableInstance;
  import edu.wpi.first.wpilibj2.command.SubsystemBase;
  import frc.robot.Constants;

  public class LimelightSubsystem extends SubsystemBase {
  
    private static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    
    private static double tx;
    private static double ty;
    private static int validTargets;
    private static double targetArea;

    private static int currentPipeline = Constants.VisionConstants.Default_Pipeline; // April Tags
   
    /* private static double lastDriveCommand = 0.0;
    private static double lastRotateCommand = 0.0;
    private static double lastTargetArea = 0.0; */

    /* public static int validTargets = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getNumber(0).intValue();
    
    public static double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0.0);
    public static double targetArea = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0.0);
    */

    public void setPipeline(int pipeline) {
      currentPipeline = pipeline;
      table.getEntry("pipeline").setNumber(pipeline);
    }

    public int getCurrentPipeline() {
      return currentPipeline;
    }

    /* public static boolean m_LimelightHasValidTargets = false;
    public static double m_LimelightDriveCommand = 0.0;
    public static double m_LimelightRotateCommand = 0.0; */

     /* public static void Update_Limelight_Values() {
    
        double tx = NetworkTableInstance.getDefault().getTable("Limelight").getEntry("tx").getDouble(0.0); 
        double ty = NetworkTableInstance.getDefault().getTable("Limelight").getEntry("ty").getDouble(0.0);
        double targetArea = NetworkTableInstance.getDefault().getTable("Limelight").getEntry("ta").getDouble(0.0);
        double validTargets = NetworkTableInstance.getDefault().getTable("Limelight").getEntry("tv").getDouble(0.0);
        

        if (validTargets < 1) {
          m_LimelightHasValidTargets = false;
          m_LimelightDriveCommand = 0.0;
          m_LimelightRotateCommand = 0.0;
          return;
        }
        
        m_LimelightHasValidTargets = true;

        double rotateError = tx; // horizontally off 
        double driveError = Constants.AutoAlignConstants.desiredTargetArea - targetArea;

        double rotateCommand = rotateError * Constants.AutoAlignConstants.rotateSpeed;
        double driveCommand = driveError * Constants.AutoAlignConstants.driveSpeed;
        
        if (driveCommand > Constants.AutoAlignConstants.maxDriveSpeed) {
          driveCommand = Constants.AutoAlignConstants.maxDriveSpeed;
        } 

        m_LimelightDriveCommand = driveCommand;
        m_LimelightRotateCommand = rotateCommand;

    } */

   /* public static double m_driveCommand = 0.0;
    public static double m_rotateCommand = 0.0;

    public static boolean m_hasValidTargets; */


    public void updateLimelightValues() {
      tx = table.getEntry("tx").getDouble(0.0);
      ty = table.getEntry("ty").getDouble(0.0);
      targetArea = table.getEntry("ta").getDouble(0.0);
      validTargets = table.getEntry("tv").getNumber(0).intValue();
    } 

      /* double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0.0);
      double targetArea = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0.0);
      int validTargets = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getNumber(0).intValue();

      if (validTargets == 0) {
        m_driveCommand = 0.0;
        m_rotateCommand = 0.0;
        m_hasValidTargets = false;
      } else {
        m_hasValidTargets = true;
      } */
      

      /* double rotateCommand = -tx * Constants.AutoAlignConstants.rotateSpeed;
      double driveError = Constants.AutoAlignConstants.desiredTargetArea - targetArea;

      double driveCommand = driveError * Constants.AutoAlignConstants.driveSpeed;

      if (driveCommand > Constants.AutoAlignConstants.maxDriveSpeed) {
        driveCommand = Constants.AutoAlignConstants.maxDriveSpeed;
      } else if (driveCommand < -Constants.AutoAlignConstants.maxDriveSpeed) {
        driveCommand = -Constants.AutoAlignConstants.maxDriveSpeed;
      }

      m_driveCommand = driveCommand;
      m_rotateCommand = rotateCommand; */


    // ____________________________________________
   
    /* public static double getDriveCommand(double desiredTargetArea, double maxDriveSpeed, double driveSpeed) {
      double targetArea = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0.0);
      double driveError = Constants.AutoAlignConstants.desiredTargetArea - targetArea;
      double driveCommand = driveError * Constants.AutoAlignConstants.driveSpeed;

      if (driveCommand > Constants.AutoAlignConstants.maxDriveSpeed) {
          driveCommand = Constants.AutoAlignConstants.maxDriveSpeed;
      } else if (driveCommand < -Constants.AutoAlignConstants.maxDriveSpeed) {
          driveCommand = -Constants.AutoAlignConstants.maxDriveSpeed;
      }

      lastDriveCommand = driveCommand; 
      lastTargetArea = targetArea; 

      return lastDriveCommand;
    } */

    /* public static double getRotateCommand(double rotateSpeed) {
      double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0.0); 
      double rotateError = -tx; // Negative because we want opp direction
      double rotateCommand = rotateError * Constants.AutoAlignConstants.rotateSpeed;

      lastRotateCommand = rotateCommand;

      return lastRotateCommand;
    } */

    /* public static double getDriveCommand(double driveSpeed, double desiredTargetArea) {
      return m_driveCommand;
    }

    public static double getRotateCommand(double rotateCommand) {
      return m_rotateCommand;
    } */

    public double getTX() {
      return tx;
    }

    public double getTargetArea() {
      return targetArea;
    }

    public double getTY() {
      return ty;
    }

    public int hasValidTargets() {
      return validTargets;
    }


    /* public double getDriveCommand(double drivespeed, double desiredtargetarea) {
      return m_LimelightDriveCommand;
    }

    public double getRotateCommand(double rotatespeed) {
      return m_LimelightRotateCommand;
    } */

    
    /** Creates a new LimelightSubsystem. */
    public LimelightSubsystem() {}

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
      updateLimelightValues();
    }

}
