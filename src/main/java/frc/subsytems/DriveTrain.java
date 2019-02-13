package frc.subsytems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.commands.Drive;
import frc.commands.DriveMM;
//import frc.commands.TurnToAngle;
import frc.robot.OI;
import frc.robot.Robot;

public class DriveTrain extends Subsystem
{
    DifferentialDrive d;
    WPI_TalonSRX leftMaster, rightMaster;
    WPI_VictorSPX leftFollower, rightFollower;
    AHRS navX;

  public DriveTrain(WPI_TalonSRX left, WPI_TalonSRX right, WPI_VictorSPX lv, WPI_VictorSPX rv)
  {
    navX = Robot.getNavX();

    leftMaster = left;
    rightMaster = right;
    leftFollower = lv;
    rightFollower = rv;

    d = new DifferentialDrive(leftMaster, rightMaster);

    leftFollower.follow(leftMaster);
    rightFollower.follow(rightMaster);

    d.setSafetyEnabled(false);

    //Robot.logNumber("DriveTrain", 1);
  }

  public void drive(Joystick stick)
  {
    d.arcadeDrive(stick.getRawAxis(1)*-1, stick.getRawAxis(4), true);
  }

  public void driveMM(Joystick stick)
  {
    leftMaster.set(ControlMode.MotionMagic, 80000);
  }

  public void stop()
  {
    leftMaster.set(ControlMode.PercentOutput, 0);
    rightMaster.set(ControlMode.PercentOutput, 0);
  }

  //Command Called by TurnToAngle usePIDOutput(double output)
  //Runs Motors in opposite directions at PID written speed
  public void turn(double input)
  {
    leftMaster.set(ControlMode.PercentOutput, input);
    rightMaster.set(ControlMode.PercentOutput, input);
  }

  public WPI_TalonSRX getTalonLeft()
  {
    return this.leftMaster;
  } 

  public WPI_TalonSRX getTalonRight()
  {
    return this.rightMaster;
  } 

  public void MMInit()
  {
    leftMaster.configFactoryDefault();
    leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    leftMaster.setSensorPhase(true);
    leftMaster.setSelectedSensorPosition(0, 0, 200);
    leftMaster.configMotionCruiseVelocity(1960);
    leftMaster.configMotionAcceleration(1960);

    leftMaster.config_kD(0, 0);
    leftMaster.config_kI(0, 0);
    leftMaster.config_kP(0, .785);    
    leftMaster.config_kF(0, .261);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveMM());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}