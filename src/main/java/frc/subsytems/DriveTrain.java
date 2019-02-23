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

public class DriveTrain extends Subsystem
{
    DifferentialDrive d;
    WPI_TalonSRX leftMaster, rightMaster;
    WPI_VictorSPX leftFollower, rightFollower;
    final double speed = .7;

  public DriveTrain(WPI_TalonSRX left, WPI_TalonSRX right, WPI_VictorSPX lv, WPI_VictorSPX rv)
  {

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
    d.arcadeDrive(stick.getRawAxis(1)*-1*speed, stick.getRawAxis(2)*speed, true);
  }

  public void drive2(double left, double right){
    leftMaster.set(left);
    rightMaster.set(right);
  }

  public void driveMM(Joystick stick, double dist)
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
    leftMaster.configFactoryDefault();
    leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    leftMaster.setSelectedSensorPosition(0, 0, 200);
    leftMaster.configNominalOutputForward(0);
    leftMaster.configNominalOutputReverse(0);
    leftMaster.configPeakOutputForward(1);
    leftMaster.configPeakOutputReverse(-1);
    leftMaster.configNeutralDeadband(.05);
    //leftMaster.configVoltageCompSaturation(0.5);
    //leftMaster.enableVoltageCompensation(true);
    leftMaster.configMotionCruiseVelocity(1960);
    leftMaster.configMotionAcceleration(1960);
    //leftMaster.configAllowableClosedloopError(slotIdx, allowableCloseLoopError, timeoutMs)

    leftMaster.config_kD(0, .003); //.003
    //leftMaster.config_IntegralZone(0, 50);
    leftMaster.config_kI(0, .00033); //.00033
    leftMaster.config_kP(0, .3);//.3
    leftMaster.config_kF(0, .261);//.261
    /*
    rightMaster.configFactoryDefault();
    rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    
    rightMaster.setSelectedSensorPosition(0, 1, 200);
    rightMaster.configNominalOutputForward(0);
    rightMaster.configNominalOutputReverse(0);
    rightMaster.configPeakOutputForward(1);
    rightMaster.configPeakOutputReverse(-1);
    rightMaster.configNeutralDeadband(.05);
    //rightMaster.configVoltageCompSaturation(0.5);
    //rightMaster.enableVoltageCompensation(true);
    rightMaster.configMotionCruiseVelocity(1960);
    rightMaster.configMotionAcceleration(1960);
    //rightMaster.configAllowableClosedloopError(slotIdx, allowableCloseLoopError, timeoutMs)

    rightMaster.config_kD(1, .003); //.003
    //rightMaster.config_IntegralZone(0, 50);
    rightMaster.config_kI(1, .00033); //.00033
    rightMaster.config_kP(1, .3);//.3
    rightMaster.config_kF(1, .261);//.261
    */
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Drive());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}