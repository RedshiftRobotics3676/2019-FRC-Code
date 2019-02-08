package frc.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.OI;
import frc.robot.Robot;
import frc.subsytems.DriveTrain;

public class TurnToAngle extends PIDCommand
{
  private static final double kP = 0.02;
	private static final double kI = 0.001;
  private static final double kD = 0.015;
  private static final double kF = 0.00;
  private static final double TOLERANCE = 5.0;

  private static final double SETPOINT = 90.0;
  
  AHRS navX;
  double targetDegrees;

    public TurnToAngle(double angle) {
      super(kP, kI, kD, kF, Robot.kDriveTrain);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.kDriveTrain);
        targetDegrees = angle;
      }
    
      // Called just before this Command runs the first time
      @Override
      protected void initialize() {
        navX = Robot.getNavX();
        getPIDController().setAbsoluteTolerance(TOLERANCE);
        navX.zeroYaw();
        setSetpoint(targetDegrees);
      }
    
      // Called repeatedly when this Command is scheduled to run
      @Override
      protected void execute() {
        //Robot.kDriveTrain.turn(targetDegrees);
      }
    
      // Make this return true when this Command no longer needs to run execute()
      @Override
      protected boolean isFinished() {
        return getPIDController().onTarget();
      }
    
      // Called once after isFinished returns true
      @Override
      protected void end() {
        
      }
    
      // Called when another command which requires one or more of the same
      // subsystems is scheduled to run
      @Override
      protected void interrupted() {
        end();
      }

      protected double returnPIDInput()
      {
        return navX.getYaw();
      }

      protected void usePIDOutput(double output)
      {
          Robot.kDriveTrain.turn(output);
      }
}