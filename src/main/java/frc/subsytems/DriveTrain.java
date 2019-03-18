package frc.subsytems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.commands.Drive;
import frc.commands.DriveMM;
//import frc.commands.TurnToAngle;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class DriveTrain extends Subsystem
{
    DifferentialDrive d;
    WPI_TalonSRX leftMaster, rightMaster;
    WPI_VictorSPX leftFollower, rightFollower;
    final double speed = .8;//.8

  public DriveTrain(WPI_TalonSRX left, WPI_TalonSRX right, WPI_VictorSPX lv, WPI_VictorSPX rv)
  {

    leftMaster = left;
    rightMaster = right;
    leftFollower = lv;
    rightFollower = rv;

    d = new DifferentialDrive(leftMaster, rightMaster);

    //rightMaster.setInverted(true);

    leftFollower.follow(leftMaster);
    rightFollower.follow(rightMaster);

    d.setSafetyEnabled(false);
    //d.setExpiration(.1);

    leftMaster.setSelectedSensorPosition(0);
    rightMaster.setSelectedSensorPosition(0);
  }

  public void drive(Joystick stick)
  {
    //System.out.println("Wow it drove");
    d.arcadeDrive(stick.getRawAxis(1)*-1*speed, stick.getRawAxis(2)*speed, true);
    //leftMaster.set(ControlMode.PercentOutput, stick.getRawAxis(1));
  }

  public void drive2(double left, double right){
    leftMaster.set(0.3*-left);
    rightMaster.set(0.3*right);
  }

  public void driveMM(double dist)
  {
    leftMaster.set(ControlMode.MotionMagic, dist);
    rightMaster.set(ControlMode.MotionMagic, dist);
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
    //rightMaster.setSelectedSensorPosition(0);
    rightMaster.setSensorPhase(true);
    //rightMaster.setInverted(true);
    rightFollower.set(ControlMode.Follower,RobotMap.R_TALON);
    //rightFollower.setInverted(true);
    rightMaster.config_kD(0, 10.4); //128
    rightMaster.config_kI(0, 0); //0
    rightMaster.config_kP(0, 1.04);//12.8
    rightMaster.config_kF(0, 0.26318497555);//.37818853974122

    //leftMaster.setSelectedSensorPosition(0);
    leftMaster.setSensorPhase(true);
    //leftMaster.setInverted(true);
    leftFollower.set(ControlMode.Follower,RobotMap.L_TALON);
    //leftFollower.setInverted(true);
    leftMaster.config_kD(0, 10.4); //128
    leftMaster.config_kI(0, 0); //0
    leftMaster.config_kP(0, 1.04);//12.8
    leftMaster.config_kF(0, 0.26318497555);//.37818853974122
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Drive());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}