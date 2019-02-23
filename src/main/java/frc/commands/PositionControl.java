/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class PositionControl extends Command {

  private int mode;

  public PositionControl(int kMode) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.kArm);
    requires(Robot.kElevator);

    mode = kMode;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.kArm.setPosition(mode);
    Robot.kElevator.setPosition(mode);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.kArm.goPosition();
    Robot.kElevator.goPosition();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.kArm.done() && Robot.kElevator.done();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.kArm.hold();
    Robot.kElevator.hold();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
