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
  //Tuned PID Values
  //CURRENTLY NOT STABLE
  private static final double kP = 0.02;
	private static final double kI = 0.001;
  private static final double kD = 0.015;
  private static final double kF = 0.00;
  private static final double TOLERANCE = 5.0;
  
  AHRS navX;
  double targetDegrees;

    public TurnToAngle(double angle) {
      //Creates the PIDController using the values passed
      super(kP, kI, kD, kF, Robot.kDriveTrain);

        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.kDriveTrain);
        //Target Angle for calculations passed from constructor
        targetDegrees = angle;
      }
    
      // Called just before this Command runs the first time
      @Override
      protected void initialize() {
        //Retrieves navX Object from the robot
        navX = Robot.getNavX();
        //Use getPIDController() to retrieve the command's PIDController Object
        //Sets tolerance of angle
        getPIDController().setAbsoluteTolerance(TOLERANCE);
        //Sets the current yaw rotation (z-axis) of the robot to 0 degrees
        navX.zeroYaw();
        //Sets the PIDControllers target angle to the value passed from constructor
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
        //PIDController's internal turn completion method
        //Returns true if the PIDController's setpoint is equal to the written value below (navX.getYaw())
        //done when finished turning
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
        //PID gets relative angle value
        return navX.getYaw();
      }

      protected void usePIDOutput(double output)
      {
          //PID outputs calculated turn speed
          //Used to output turn command to drivetrain
          Robot.kDriveTrain.turn(output);
      }
}