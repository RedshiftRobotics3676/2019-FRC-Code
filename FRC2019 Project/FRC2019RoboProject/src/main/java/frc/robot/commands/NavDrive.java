/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/*package frc.robot.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.OI;
import frc.robot.Robot;
import com.kauailabs.navx.frc.*;

public class NavDrive extends PIDCommand {

  private AHRS navX;

  public NavDrive() {
    super(0.02, 0.001, 0.015, 0.00);

    requires(Robot.kDriveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    navX = Robot.getNavX();
        navX = Robot.getNavX();
        getPIDController().setAbsoluteTolerance(1.0);
        navX.zeroYaw();
        setSetpoint(0.0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.kDriveTrain.drive(OI.getJoystick());
    //Robot.logNumber("Drive Value", OI.getJoystick().getRawAxis(1));

    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
   
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }

  protected double returnPIDInput()
  {
    return navX.getYaw();
  }

  protected void usePIDOutput(double output)
  {
    Robot.kDriveTrain.turn(output);
  }
}*/